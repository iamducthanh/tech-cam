package com.techcam.repo;

import com.techcam.entity.OrderdetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/20/2022
 * Project_name: Tech-cam
 */

@Repository
public interface IOrderDetailsRepo extends JpaRepository<OrderdetailEntity, String> {
    //    @Query("select o from OrderdetailEntity o where o.orders.id=?1 and o.deleteFlag=?2")
    List<OrderdetailEntity> findAllByOrdersIdAndDeleteFlag(String id, boolean flagDelete);

    List<OrderdetailEntity> findAllByIdNotInAndOrdersIdAndDeleteFlagFalse(List<String> ids, String id);

    List<OrderdetailEntity> findAllByIdInAndOrdersIdAndDeleteFlagFalse(List<String> ids, String id);

    OrderdetailEntity findByOrdersIdAndProductId(String orderId, String productId);
}
