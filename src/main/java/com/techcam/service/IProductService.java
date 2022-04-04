package com.techcam.service;

import com.techcam.dto.request.product.ProductAddRequest;
import com.techcam.dto.request.product.ProductEditRequest;
import com.techcam.dto.response.product.ProductPropertyResponse;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.dto.response.product.ProductResponseDTO;
import ognl.OgnlOps;

import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
public interface IProductService {

    List<ProductPropertyResponse> findAllPropertyByProductId(String productId);

    String createProduct(ProductAddRequest productRequest);

    String updateProduct(ProductEditRequest productRequest);

    String deleteProduct(String productId);

    List<ProductResponse> getAllProduct();

    ProductResponse getById(String productId);

    List<String> findAllImagesByProductId(String productId);

    List<ProductResponse> findAllByCategoryId(String categoryId);

    List<ProductResponseDTO> getAll();
}
