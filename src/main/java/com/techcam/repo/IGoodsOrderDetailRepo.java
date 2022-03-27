package com.techcam.repo;

import com.techcam.dto.response.invoiceOrder.InvoiceOrderResponse;
import com.techcam.entity.GoodsOrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/27/2022 8:33 PM
 * Project_name : tech-cam
 */

public interface IGoodsOrderDetailRepo extends JpaRepository<GoodsOrderDetailEntity, String> {

    List<GoodsOrderDetailEntity> findAllByGoodsOrdersIdAndDeleteFlagIsFalse(String orderId);

}
