package com.techcam.service.impl;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.product.ProductAddRequest;
import com.techcam.dto.request.product.ProductEditRequest;
import com.techcam.dto.request.product.ProductPropertyRequest;
import com.techcam.dto.response.product.ProductPropertyResponse;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.entity.*;
import com.techcam.repo.*;
import com.techcam.entity.ProductEntity;
import com.techcam.repo.IAttributeRepo;
import com.techcam.repo.ICategoryRepo;
import com.techcam.repo.IProductPropertyRepo;
import com.techcam.repo.IProductRepo;
import com.techcam.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.techcam.constants.ConstantsErrorCode.PRODUCT_NOT_EXISTS;
import static com.techcam.constants.ConstantsErrorCode.SUCCESS;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepo productRepo;

    private final ICategoryRepo categoryRepo;

    private final IProductPropertyRepo productPropertyRepo;

    private final IAttributeRepo attributeRepo;

    private final IAttributeFixedValueRepo attributeFixedValueRepo;

    private final IImageRepo imageRepo;

    private final IBrandRepo brandRepo;


    @Override
    public List<ProductPropertyResponse> findAllPropertyByProductId(String productId) {
        // TODO trả về list thuộc tính sản phẩm
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public String createProduct(ProductAddRequest productRequest) {
        if (Objects.isNull(productRequest)) {
            return ConstantsErrorCode.ERROR_DATA_REQUEST;
        }
        if (!productRepo.findALlByProductCodeAndDeleteFlagIsFalse(productRequest.getProductCode()).isEmpty()) {
            return ConstantsErrorCode.PRODUCT_CODE_DUPLICATE;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity = mapToEntity(productRequest, productEntity);
        productEntity.setId(UUID.randomUUID().toString());
        List<ImagesEntity> lstImage = new ArrayList<>();
        for (String x : productRequest.getProductImages()) {
            ImagesEntity imagesEntity = ImagesEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .productId(productEntity.getId())
                    .imagesName(productEntity.getName())
                    .imagesLink(x)
                    .status("ON")
                    .note("")
                    .build();
            lstImage.add(imagesEntity);
        }
        List<ProductPropertyEntity> lstProductPropertyEntities = new ArrayList<>();
        for (ProductPropertyRequest x : productRequest.getProductProperties()) {
            AttributeFixedValueEntity attributeFixedValueEntity = attributeFixedValueRepo.getByIdAndDeleteFlagIsFalse(x.getFixedValueId());
            ProductPropertyEntity productPropertyEntity = ProductPropertyEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .attributeId(x.getPropertyId())
                    .productId(productEntity.getId())
                    .attributeFixedId(Objects.isNull(attributeFixedValueEntity) ? null : attributeFixedValueEntity.getId())
                    .attributeValue(Objects.isNull(attributeFixedValueEntity) ? x.getInputValue() : null)
                    .status("ON")
                    .build();
            lstProductPropertyEntities.add(productPropertyEntity);
        }
        try {
            productRepo.save(productEntity);
            productPropertyRepo.saveAll(lstProductPropertyEntities);
            for (ImagesEntity x : lstImage) {
                Thread.sleep(500);
                imageRepo.save(x);
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ConstantsErrorCode.ERROR;
    }

    private ProductEntity mapToEntity(ProductAddRequest x, ProductEntity s) {
        if (Objects.isNull(x)) return null;
        CategoryEntity categoryEntity = categoryRepo.getByIdAndDeleteFlagIsFalse(x.getProductCategory());
        BrandEntity brandEntity = brandRepo.getByIdAndDeleteFlagIsFalse(x.getProductBrand());
        return s.toBuilder()
                .name(x.getProductName())
                .categoryId(Objects.isNull(categoryEntity) ? null : categoryEntity.getId())
                .brandId(Objects.isNull(brandEntity) ? null : brandEntity.getId())
                .productCode(x.getProductCode())
                .quantity(0)
                .price(Long.parseLong(x.getProductPrice()))
                .detail("")
                .description(x.getProductDescription())
                .status(x.getProductStatus().equals("true") ? "ON" : "OFF")
                .build();
    }

    @Override
    public String updateProduct(ProductEditRequest productRequest) {
        if (Objects.isNull(productRequest)) {
            return ConstantsErrorCode.ERROR_DATA_REQUEST;
        }
        if (productRepo.findALlByProductCodeAndDeleteFlagIsFalse(productRequest.getProductCode())
                .stream().anyMatch(e -> !e.getId().equals(productRequest.getProductId()))) {
            return ConstantsErrorCode.PRODUCT_CODE_DUPLICATE;
        }
        // TODO thay đổi trạng thái hình ảnh (xoá)

        return null;
    }

    @Override
    public String deleteProduct(String productId) {
        ProductEntity x = productRepo.getByIdAndDeleteFlagIsFalse(productId);
        if (Objects.isNull(x)) {
            return PRODUCT_NOT_EXISTS;
        }
        return SUCCESS;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToResponse).collect(Collectors.toList());
    }

    private <R> ProductResponse mapToResponse(ProductEntity x) {
        if (Objects.isNull(x)) return new ProductResponse();
        ProductResponse s = new ProductResponse();
        s.setProductId(x.getId());
        s.setProductCode(x.getProductCode());
        s.setProductName(x.getName());
        s.setProductPrice(x.getPrice() + "");
        s.setProductBrand(x.getBrandId());
        s.setProductStatus(x.getStatus());
        s.setProductCategory(x.getCategoryId());
        s.setProductDescription(x.getDescription());
        return s;
    }
}
