package com.techcam.repo;

import com.techcam.dto.response.product.ProductResponse;
import com.techcam.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:37 AM
 * Project_name : tech-cam
 */

@Repository
public interface IProductRepo extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findAllByDeleteFlagIsFalse();

    List<ProductEntity> findALlByProductCodeAndDeleteFlagIsFalse(String productCode);

    ProductEntity getByIdAndDeleteFlagIsFalse(String id);
}
