package com.fpoly.techcam.repo;

import com.fpoly.techcam.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:37 AM
 * Project_name : tech-cam
 */

public interface ProductRepo extends JpaRepository<ProductEntity, String> {
}
