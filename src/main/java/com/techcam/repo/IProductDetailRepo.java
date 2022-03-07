package com.techcam.repo;

import com.techcam.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/2/2022 3:00 PM
 * Project_name : tech-cam
 */

public interface IProductDetailRepo extends JpaRepository<ProductDetailEntity, String> {
}
