package com.techcam.repo;

import com.techcam.entity.ReceiptVoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/23/2022
 * Project_name: Tech-cam
 */
@Repository
public interface IReceiptVoucherRepo extends JpaRepository<ReceiptVoucherEntity,Integer> {
    ReceiptVoucherEntity findFirstByIdAndDeleteFlagFalse(Integer id);

    @Query("select o from ReceiptVoucherEntity o ORDER BY o.createDate desc ")
    List<ReceiptVoucherEntity> findAllOderBy();
}
