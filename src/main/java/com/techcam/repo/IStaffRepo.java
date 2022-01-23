package com.techcam.repo;

import com.techcam.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:38 AM
 * Project_name : tech-cam
 */

@Repository
public interface IStaffRepo extends JpaRepository<StaffEntity, String> {
}