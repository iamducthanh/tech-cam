package com.techcam.repo;

import com.techcam.entity.AttributeEntity;
import com.techcam.entity.AttributeFixedValueEntity;
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

public interface IAttributeFixedValueRepo extends JpaRepository<AttributeFixedValueEntity, String> {

    AttributeFixedValueEntity getByIdAndDeleteFlagIsFalse(String fixedValueId);

    List<AttributeFixedValueEntity> findAllByAttributeIdAndDeleteFlagIsFalse(String propertyId);

    @Query("select o from AttributeFixedValueEntity o where o.attributeId = ?1 and o.deleteFlag = false ")
    AttributeFixedValueEntity findByAttributeId(String attributeId);
}
