package com.techcam.repo;

import com.techcam.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:39 AM
 * Project_name : tech-cam
 */

@Repository
public interface IVoucherRepo extends JpaRepository<VoucherEntity, String> {

    List<VoucherEntity> findAllByCodeAndDeleteFlagIsFalse(String voucherCode);

    @Override
    @Query("select o from VoucherEntity o where o.deleteFlag = false")
    VoucherEntity getById(String s);

    List<VoucherEntity> findAllByDeleteFlagIsFalse();
}
