package com.techcam.repo;

import com.techcam.entity.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/2/2022 2:59 PM
 * Project_name : tech-cam
 */

public interface IImageRepo extends JpaRepository<ImagesEntity, String> {
}
