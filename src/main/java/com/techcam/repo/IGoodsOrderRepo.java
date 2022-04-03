package com.techcam.repo;

import com.techcam.dto.response.invoiceOrder.InvoiceOrderResponse;
import com.techcam.entity.GoodsOrderDetailEntity;
import com.techcam.entity.GoodsOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/27/2022 8:21 PM
 * Project_name : tech-cam
 */

public interface IGoodsOrderRepo extends JpaRepository<GoodsOrderEntity, String> {
    List<GoodsOrderEntity> findAllByDeleteFlagIsFalse();

    GoodsOrderEntity getByIdAndDeleteFlagIsFalse(String orderId);
}
