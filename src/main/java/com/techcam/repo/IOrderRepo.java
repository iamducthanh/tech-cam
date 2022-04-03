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

public interface IOrderRepo extends JpaRepository<OrdersEntity,Integer> {
    List<OrdersEntity> findAllByOrderByCreateDateDesc();
    List<OrdersEntity> findAllByStatusAndDeleteFlagFalseOrderByCreateDateDesc(String status);
    List<OrdersEntity> findAllByDeleteFlagFalseOrderByCreateDateDesc();
    OrdersEntity findByIdAndDeleteFlagFalse(Integer id);
    OrdersEntity findByIdAndTransactionStatusAndDeleteFlagFalse(Integer id,String status);
    @Query("select count(o) from OrdersEntity o where o.orderDate=?1 and o.customer.phoneNumber=?2 and o.ipAddress=?3")
    int countByPhoneNumberCustomer(Date date, String phoneNumber, String ipAddress);
    OrdersEntity findByBankTransaction(String bankTransaction);

    List<OrdersEntity> findAllByVoucherIdAndDeleteFlagIsFalse(String voucherId);
    OrdersEntity findFirstByVoucherCodeAndCustomerPhoneNumber(String code, String phoneNumber);
}
