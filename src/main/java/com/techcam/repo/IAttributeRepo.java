package com.techcam.repo;

import com.techcam.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/2/2022 2:58 PM
 * Project_name : tech-cam
 */

public interface IAttributeRepo extends JpaRepository<AttributeEntity, String> {

    AttributeEntity getByIdAndDeleteFlagIsFalse(String propertyId);

    List<AttributeEntity> findAllByCategoryIdAndDeleteFlagIsFalse(String categoryId);

}
