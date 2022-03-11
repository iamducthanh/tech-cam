package com.techcam.repo;

import com.techcam.entity.VoucherCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/10/2022 3:25 PM
 * Project_name : tech-cam
 */

public interface VoucherCustomerRepo extends JpaRepository<VoucherCustomerEntity, String> {
    List<VoucherCustomerEntity> findAllByVoucherId(String voucherId);
}
