package com.techcam.repo;

import com.techcam.entity.ProductPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/2/2022 3:00 PM
 * Project_name : tech-cam
 */

public interface IProductPropertyRepo extends JpaRepository<ProductPropertyEntity, String> {
    List<ProductPropertyEntity> findAllByProductIdAndDeleteFlagIsFalse(String id);

    @Query("select p from ProductPropertyEntity p where p.productId = ?1 and p.attributeId = ?2 and (p.attributeFixedId = ?3 or p.attributeValue = ?4) and p.deleteFlag = false")
    List<ProductPropertyEntity> findAllByProductIdAndAttributeIdAndAttributeFixedIdOrValueAndDeleteFlagIsFalse(String id, String productPropertyId, String fixedId, String iValue);
}
