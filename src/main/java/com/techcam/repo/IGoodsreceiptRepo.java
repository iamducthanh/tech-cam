package com.techcam.repo;

import com.techcam.entity.GoodsreceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 10:06 PM
 * Project_name : tech-cam
 */

public interface IGoodsreceiptRepo extends JpaRepository<GoodsreceiptEntity, String> {

    List<GoodsreceiptEntity> findAllByDeleteFlagIsFalse();

}
