package com.techcam.repo;

import com.techcam.entity.CategoryEntity;
import org.codehaus.groovy.vmplugin.v8.PluginDefaultGroovyMethods;
import org.springframework.data.jpa.repository.JpaRepository;
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

}
