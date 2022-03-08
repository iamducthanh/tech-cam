package com.techcam.service.impl;

import com.techcam.dto.request.product.ProductAddRequest;
import com.techcam.dto.request.product.ProductEditRequest;
import com.techcam.dto.response.product.ProductPropertyResponse;
import com.techcam.entity.ProductEntity;
import com.techcam.repo.*;
import com.techcam.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.techcam.util.ConstantsErrorCode.PRODUCT_NOT_EXISTS;
import static com.techcam.util.ConstantsErrorCode.SUCCESS;

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

    private final IProductDetailRepo productDetailRepo;

    private final IProductPropertyRepo productPropertyRepo;

    private final IAttributeRepo attributeRepo;


    @Override
    public List<ProductPropertyResponse> findAllPropertyByProductId(String productId) {
        // TODO trả về list thuộc tính sản phẩm
        return new ArrayList<>();
    }

    @Override
    public String createProduct(ProductAddRequest productRequest) {
        return null;
    }

    @Override
    public String updateProduct(ProductEditRequest productRequest) {
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
}
