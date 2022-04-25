package com.techcam.service.impl;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.product.ProductAddRequest;
import com.techcam.dto.request.product.ProductEditRequest;
import com.techcam.dto.request.product.ProductPropertyRequest;
import com.techcam.dto.response.product.ProductPropertyResponse;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.dto.response.product.ProductResponseDTO;
import com.techcam.entity.*;
import com.techcam.mapper.ProductMapper;
import com.techcam.repo.*;
import com.techcam.service.IProductService;
import com.techcam.util.ConvertDateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static com.techcam.constants.ConstantsErrorCode.PRODUCT_NOT_EXISTS;
import static com.techcam.type.CustomerStatus.FAILED;
import static com.techcam.type.CustomerStatus.SUCCESS;

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

    @Autowired
    ProductMapper productMapper;

    private final IGoodsreceiptdetailRepo goodsreceiptdetailRepo;

    private final IOrderDetailsRepo orderDetailsRepo;

    @Override
    public List<ProductPropertyResponse> findAllPropertyByProductId(String productId) {
        // TODO trả về list thuộc tính sản phẩm
        return productPropertyRepo.findAllByProductIdAndDeleteFlagIsFalse(productId).stream()
                .map(this::mapToProductPropertyResponse).collect(Collectors.toList());
    }

    private ProductPropertyResponse mapToProductPropertyResponse(ProductPropertyEntity x) {
        if (Objects.isNull(x)) return new ProductPropertyResponse();
        AttributeEntity a = attributeRepo.getByIdAndDeleteFlagIsFalse(x.getAttributeId());
        if (Objects.isNull(a)) return new ProductPropertyResponse();
        ProductPropertyResponse s = new ProductPropertyResponse();
        s.setPropertyId(x.getAttributeId());
        s.setPropertyName(a.getAttributeName());
        s.setProductPropertyId(x.getId());
        s.setFixedValueId(x.getAttributeFixedId());
        s.setInputValue(x.getAttributeValue());

        return s;
    }

    @Override
    @Transactional
    public String createProduct(ProductAddRequest productRequest) {
        if (Objects.isNull(productRequest)) {
            return ConstantsErrorCode.ERROR_DATA_REQUEST;
        }
//        if (!productRepo.findALlByProductCodeAndDeleteFlagIsFalse(productRequest.getProductCode()).isEmpty()) {
//            return ConstantsErrorCode.PRODUCT_CODE_DUPLICATE;
//        }
        ProductEntity productEntity = new ProductEntity();
        productEntity = mapToEntity(productRequest, productEntity);
        if (Objects.isNull(productEntity)) {
            return ConstantsErrorCode.ERROR_DATA_REQUEST;
        }
        productEntity.setId(UUID.randomUUID().toString());
        productEntity.setProductCode(ConvertDateUtil.generationCode("SP"));
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
            if (Objects.isNull(productEntity.getThumbnail())) {
                productEntity.setThumbnail(x);
            }
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
            productEntity.setImportPrice(0.0);
            productRepo.save(productEntity);
            productPropertyRepo.saveAll(lstProductPropertyEntities);
            for (ImagesEntity x : lstImage) {
                Thread.sleep(500);
                imageRepo.save(x);
            }
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FAILED.name();
    }

    @Override
    public String updateProduct(ProductEditRequest productRequest) {
        if (Objects.isNull(productRequest)) {
            return ConstantsErrorCode.ERROR_DATA_REQUEST;
        }
//        if (productRepo.findALlByProductCodeAndDeleteFlagIsFalse(productRequest.getProductCode())
//                .stream().anyMatch(e -> !e.getId().equals(productRequest.getProductId()))) {
//            return ConstantsErrorCode.PRODUCT_CODE_DUPLICATE;
//        }
        ProductEntity productEntity = productRepo.getByIdAndDeleteFlagIsFalse(productRequest.getProductId());
        if (Objects.isNull(productEntity)) {
            return ConstantsErrorCode.ERROR_DATA_REQUEST;
        }
        Timestamp createDate = productEntity.getCreateDate();
        productEntity = mapToEntity(productRequest, productEntity);
        if (Objects.isNull(productEntity)) {
            return ConstantsErrorCode.ERROR_DATA_REQUEST;
        }
        productEntity.setId(productRequest.getProductId());
        productEntity.setCreateDate(createDate);
        List<ImagesEntity> lstImage = new ArrayList<>();
        List<ImagesEntity> lstImageDelete = imageRepo.findAllByProductIdAndDeleteFlagIsFalse(productEntity.getId());
        for (String x : productRequest.getProductImages()) {
            ImagesEntity imagesEntity = ImagesEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .productId(productEntity.getId())
                    .imagesName(productEntity.getName())
                    .imagesLink(x)
                    .status("ON")
                    .note("")
                    .build();
            if (Objects.isNull(productEntity.getThumbnail())) {
                productEntity.setThumbnail(x);
            }
            imagesEntity.setCreateDate(new Timestamp(new Date().getTime()));
            lstImage.add(imagesEntity);
        }
        List<ProductPropertyEntity> findAllByProductId = productPropertyRepo.findAllByProductIdAndDeleteFlagIsFalse(productEntity.getId());
        List<ProductPropertyEntity> lstProductPropertyEntities = new ArrayList<>();
        List<String> lstIdDuplicate = new ArrayList<>();
        for (ProductPropertyRequest x : productRequest.getProductProperties()) {
            List<ProductPropertyEntity> findByProductIdAndPropertyId = findAllByProductId.stream()
                    .filter(e -> e.getAttributeId().equals(x.getPropertyId())).collect(Collectors.toList());
            String id;
            if (findByProductIdAndPropertyId.isEmpty()) {
                id = UUID.randomUUID().toString();
            } else {
                id = findByProductIdAndPropertyId.get(0).getId();
                lstIdDuplicate.add(id);
            }
            AttributeFixedValueEntity attributeFixedValueEntity = attributeFixedValueRepo.getByIdAndDeleteFlagIsFalse(x.getFixedValueId());
            ProductPropertyEntity productPropertyEntity = ProductPropertyEntity.builder()
                    .id(id)
                    .attributeId(x.getPropertyId())
                    .productId(productEntity.getId())
                    .attributeFixedId(Objects.isNull(attributeFixedValueEntity) ? null : attributeFixedValueEntity.getId())
                    .attributeValue(Objects.isNull(attributeFixedValueEntity) ? x.getInputValue() : null)
                    .status("ON")
                    .build();
            productPropertyEntity.setCreateDate(new Timestamp(new Date().getTime()));
            lstProductPropertyEntities.add(productPropertyEntity);
        }
        findAllByProductId.stream().filter(e -> !lstIdDuplicate.contains(e.getId())).forEach(e -> e.setDeleteFlag(true));
        try {
            productRepo.save(productEntity);
            productPropertyRepo.saveAll(lstProductPropertyEntities);
            productPropertyRepo.saveAll(findAllByProductId);
            imageRepo.saveAll(lstImage);
            imageRepo.deleteAll(lstImageDelete);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FAILED.name();
    }

    @Override
    public String deleteProduct(String productId) {
        ProductEntity x = productRepo.getByIdAndDeleteFlagIsFalse(productId);
        if (Objects.isNull(x)) {
            return PRODUCT_NOT_EXISTS;
        }
        return SUCCESS.name();
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getById(String productId) {
        return mapToResponse(productRepo.getByIdAndDeleteFlagIsFalse(productId));
    }

    @Override
    public List<String> findAllImagesByProductId(String productId) {
        return imageRepo.findAllByProductIdAndDeleteFlagIsFalse(productId).stream()
                .map(ImagesEntity::getImagesLink).collect(Collectors.toList());
    }

    @Override
    public int getInventoryByProductId(String productId) {
        List<GoodsreceiptdetailEntity> lstInvoiceDetail = goodsreceiptdetailRepo.findAllByProductIdAndDeleteFlagIsFalse(productId);
        List<OrderdetailEntity> lstOrderDetail = orderDetailsRepo.findAllByProductIdAndDeleteFlagIsFalse(productId);
        Long sumInvoice = lstInvoiceDetail.stream().mapToLong(GoodsreceiptdetailEntity::getQuantityActual).sum();
        Long sumOrder = lstOrderDetail.stream().mapToLong(OrderdetailEntity::getQuantity).sum();
        return (int) (sumInvoice - sumOrder);
    }

    @Override
    public List<ProductResponse> findAllByCategoryId(String categoryId) {
        return productRepo.findAllByCategoryIdAndDeleteFlagFalse(categoryId).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> getAll() {
        return productMapper.toProductResponseDTOs(productRepo.findAllByDeleteFlagIsFalse());
    }

    @Override
    public List<ProductResponse> findAllByKeyWords(String keyword) {
        return productRepo.findAllByKeyWords(keyword)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private ProductEntity mapToEntity(Object obj, ProductEntity s) {
        if (Objects.isNull(obj)) return null;
        if (obj instanceof ProductAddRequest) {
            ProductAddRequest x = (ProductAddRequest) obj;
            return mapToEntity(s, x.getProductCategory(), x.getProductBrand(), x.getProductName(), x.getProductPrice(), x.getProductDescription(), x.getProductStatus());
        } else if (obj instanceof ProductEditRequest) {
            ProductEditRequest x = (ProductEditRequest) obj;
            return mapToEntity(s, x.getProductCategory(), x.getProductBrand(), x.getProductName(), x.getProductPrice(), x.getProductDescription(), x.getProductStatus());
        }
        return null;
    }

    private ProductEntity mapToEntity(ProductEntity s, String productCategory, String productBrand, String productName, String productPrice, String productDescription, String productStatus) {
        CategoryEntity categoryEntity = categoryRepo.getByIdAndDeleteFlagIsFalse(productCategory);
        BrandEntity brandEntity = brandRepo.getByIdAndDeleteFlagIsFalse(productBrand);
        return s.toBuilder()
                .name(productName)
                .categoryId(Objects.isNull(categoryEntity) ? null : categoryEntity.getId())
                .brandId(Objects.isNull(brandEntity) ? null : brandEntity.getId())
                .quantity(0)
                .price(Long.parseLong(productPrice))
                .detail("")
                .description(productDescription)
                .status(productStatus.equals("true") ? "ON" : "OFF")
                .build();
    }

    private ProductResponse mapToResponse(ProductEntity x) {
        if (Objects.isNull(x)) return new ProductResponse();
        List<GoodsreceiptdetailEntity> lstInvoiceDetail = goodsreceiptdetailRepo.findAllByProductIdAndDeleteFlagIsFalse(x.getId());
        List<OrderdetailEntity> lstOrderDetail = orderDetailsRepo.findAllByProductIdAndDeleteFlagIsFalse(x.getId());
        Long sumInvoice = lstInvoiceDetail.stream().mapToLong(GoodsreceiptdetailEntity::getQuantityActual).sum();
        Long sumOrder = lstOrderDetail.stream().mapToLong(OrderdetailEntity::getQuantity).sum();
        int sumQuantity = (int) (sumInvoice - sumOrder);
        ProductResponse s = new ProductResponse();
        s.setProductId(x.getId());
        s.setProductCode(x.getProductCode());
        s.setProductName(x.getName());
        s.setProductPrice(x.getPrice() + "");
        s.setProductBrand(x.getBrandId());
        s.setProductStatus(x.getStatus());
        s.setProductCategory(x.getCategoryId());
        s.setProductDescription(x.getDescription());
        s.setCreateBy(x.getCreateBy());
        s.setCreateDate(x.getCreateDate());
        s.setModifierDate(x.getModifierDate());
        s.setThumbnail(x.getThumbnail());
        s.setPromotion(x.getPromotion() + "");
        s.setProductQuantity(sumQuantity);
        return s;
    }
}
