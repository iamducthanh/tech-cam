package com.fpoly.techcam.repo;

import com.fpoly.techcam.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:36 AM
 * Project_name : tech-cam
 */

public interface BrandRepo extends JpaRepository<BrandEntity, String> {
}
