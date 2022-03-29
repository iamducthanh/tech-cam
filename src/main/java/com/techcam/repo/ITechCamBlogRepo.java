package com.techcam.repo;

import com.techcam.entity.TechcamBlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/28/2022
 * Project_name: Tech-cam
 */

public interface ITechCamBlogRepo extends JpaRepository<TechcamBlogEntity,String> {
    List<TechcamBlogEntity> findAllByOrderByCreateDateDesc();
}
