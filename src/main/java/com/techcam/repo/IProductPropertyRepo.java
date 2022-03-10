package com.techcam.repo;

import com.techcam.entity.AttributeEntity;
import com.techcam.entity.ProductPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/2/2022 3:00 PM
 * Project_name : tech-cam
 */

public interface IProductPropertyRepo extends JpaRepository<ProductPropertyEntity, String> {
    List<ProductPropertyEntity> findAllByProductDetailIdAndDeleteFlagIsFalse(String productDetailId);
}
