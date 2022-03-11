package com.techcam.repo;

import com.techcam.dto.response.brand.BrandResponse;
import com.techcam.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:36 AM
 * Project_name : tech-cam
 */

@Repository
public interface IBrandRepo extends JpaRepository<BrandEntity, String> {
    List<BrandEntity> findAllByDeleteFlagIsFalse();

    BrandEntity getByIdAndDeleteFlagIsFalse(String productBrand);
}
