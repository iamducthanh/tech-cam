package com.techcam.repo;

import com.techcam.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDV
 * @version: 1.0
 * @since: 2/27/2022
 * Project_name: Tech-cam
 */
@Repository
public interface ICustomerRepo extends JpaRepository<CustomerEntity, String> {

    List<CustomerEntity> findAllByStatus(String status);

    @Query(value = "select o.* from Customer o where o.status=:status and month(o.DATE_OF_BIRTH)= :month and day (o.DATE_OF_BIRTH)= :day ", nativeQuery = true)
    List<CustomerEntity> findAllBySendBirthDay(@Param("status") String status, @Param("month") String month, @Param("day") String day);

//    List<CustomerEntity> findAllBy(String status, int dateOfBirth_day);

    List<CustomerEntity> findAllByPhoneNumberStartsWithAndStatus(String phoneNumber, String status);

    List<CustomerEntity> findAllByFullNameStartingWithAndStatus(String fullName, String status);

    @Query("select o from CustomerEntity o where o.createDate >= ?1 and o.createDate <= ?2 and o.status = ?3")
    List<CustomerEntity> findAllByCreateDateBetweenAndStatus(Date startDate, Date endDate, String status);

    CustomerEntity findByEmailAndStatus(String email, String status);

    CustomerEntity findByPhoneNumberAndStatus(String phoneNumber, String status);

    CustomerEntity findByIdAndStatus(String id, String status);

    @Query("SELECT o FROM CustomerEntity o WHERE o.fullName LIKE %:keyword% OR o.phoneNumber LIKE %:keyword% OR o.email LIKE %:keyword% OR o.address LIKE %:keyword%")
    List<CustomerEntity> findByKeyword(@Param("keyword") String keyword);

}
