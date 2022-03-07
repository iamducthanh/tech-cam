package com.techcam.repo;

import com.techcam.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/2/2022 2:58 PM
 * Project_name : tech-cam
 */

public interface IAttributeRepo extends JpaRepository<AttributeEntity, String> {
}
