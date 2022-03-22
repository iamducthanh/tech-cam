package com.techcam.repo;

import com.techcam.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/20/2022
 * Project_name: Tech-cam
 */

public interface IOrderRepo extends JpaRepository<OrdersEntity,String> {
    List<OrdersEntity> findAllByStatusAndDeleteFlagFalse(String status);
    List<OrdersEntity> findAllByDeleteFlagFalse();
    OrdersEntity findByIdAndDeleteFlagFalse(String id);
    @Query("select count(o) from OrdersEntity o where o.orderDate=?1 and o.customer.phoneNumber=?2 and o.ipAddress=?3")
    int countByPhoneNumberCustomer(Date date, String phoneNumber, String ipAddress);
}
