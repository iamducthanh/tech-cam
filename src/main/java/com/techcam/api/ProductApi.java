package com.techcam.api;

import com.techcam.dto.request.product.ProductAddRequest;
import com.techcam.dto.request.product.ProductEditRequest;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.techcam.constants.ConstantsErrorCode.*;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/3/2022 4:58 PM
 * Project_name : tech-cam
 */

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductApi {

    private final IProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Validated ProductAddRequest productRequest, Errors errors) {
        validateInputProduct(errors, productRequest.getProductName(), productRequest.getProductImages());
        String message = productService.createProduct(productRequest);
        if (Objects.nonNull(message) && message.equals(SUCCESS)) {
            return ResponseEntity.ok(SUCCESS);
        }
        if (Objects.isNull(message)) {
            message = INTERNAL_SERVER_ERROR;
        }
        throw new TechCamExp(message);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody @Validated ProductEditRequest productRequest, Errors errors) {
        validateInputProduct(errors, productRequest.getProductName(), productRequest.getProductImages());
        String message = productService.updateProduct(productRequest);
        if (Objects.nonNull(message) && message.equals(SUCCESS)) {
            return ResponseEntity.ok(SUCCESS);
        }
        if (Objects.isNull(message)) {
            message = INTERNAL_SERVER_ERROR;
        }
        throw new TechCamExp(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String productId) {
        String message = productService.deleteProduct(productId);
        if (Objects.nonNull(message) && message.equals(SUCCESS)) {
            return ResponseEntity.ok(SUCCESS);
        }
        if (Objects.isNull(message)) {
            message = INTERNAL_SERVER_ERROR;
        }
        throw new TechCamExp(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    private void validateInputProduct(Errors errors, String productName, List<String> productImages) {
        if (errors.hasErrors()) {
            throw new TechCamExp(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if (!checkLengthStr(productName, 10, 100)) {
            throw new TechCamExp(ERROR_LENGTH, 10, 100);
        }
        if (Objects.isNull(productImages) || productImages.isEmpty()) {
            throw new TechCamExp(PRODUCT_IMAGE_ERROR);
        }
        // số lượng ảnh tối đa có thể tải lên
        final int maxImage = 20;
        if (productImages.size() > maxImage) {
            throw new TechCamExp(PRODUCT_IMAGE_MAX, maxImage);
        }
    }

    private boolean checkLengthStr(String str, int min, int max) {
        return checkLengthMin(str, min) && checkLengthMax(str, max);
    }

    private boolean checkLengthMin(String str, int min) {
        return str.length() >= min;
    }

    private boolean checkLengthMax(String str, int max) {
        return str.length() <= max;
    }

}
