package com.techcam.repo;

import com.techcam.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:36 AM
 * Project_name : tech-cam
 */

@Repository
public interface ICategoryRepo extends JpaRepository<CategoryEntity, String> {

    List<CategoryEntity> findAllByDeleteFlagIsFalse();

    CategoryEntity getByIdAndDeleteFlagIsFalse(String categoryId);

    @Query("select o from CategoryEntity o where o.deleteFlag = false and o.parentId is null order by o.createDate asc ")
    List<CategoryEntity> findCategoryParent();

    @Query("select o from CategoryEntity o where o.deleteFlag = false and o.parentId = ?1 order by o.createDate asc ")
    List<CategoryEntity> findCategoryByParent(String parentId);
}
