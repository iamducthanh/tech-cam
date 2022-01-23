package com.techcam.techcam.repo;

import com.techcam.techcam.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:39 AM
 * Project_name : tech-cam
 */

public interface VoucherRepo extends JpaRepository<VoucherEntity, String> {
}
