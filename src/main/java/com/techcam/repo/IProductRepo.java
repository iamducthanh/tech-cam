package com.techcam.repo;

import com.techcam.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
