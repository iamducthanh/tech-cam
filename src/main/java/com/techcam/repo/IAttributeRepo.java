package com.techcam.repo;

import com.techcam.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query("select o from AttributeEntity o where o.attributeName = ?1 and o.categoryId = ?2 and o.deleteFlag = false ")
    List<AttributeEntity> findByNameAndCategory(String name, String categoryId);

}
