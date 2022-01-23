package com.fpoly.techcam.repo;

import com.fpoly.techcam.entity.VoucherCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:38 AM
 * Project_name : tech-cam
 */

public interface VoucherCustomerRepo extends JpaRepository<VoucherCustomerEntity, String> {
}
