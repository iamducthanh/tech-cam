package com.techcam.repo;

import com.techcam.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:38 AM
 * Project_name : tech-cam
 */

@Repository
public interface ISupplierRepo extends JpaRepository<SupplierEntity, String> {

    List<SupplierEntity> findAllByDeleteFlagFalse();

    Optional<SupplierEntity> findById(String id);

    Optional<SupplierEntity> findByName(String name);

    Optional<SupplierEntity> findByEmail(String email);

    Optional<SupplierEntity> findByPhoneNumber(String phoneNumber);

    Optional<SupplierEntity> findByIdAndDeleteFlagFalse(String id);
}
