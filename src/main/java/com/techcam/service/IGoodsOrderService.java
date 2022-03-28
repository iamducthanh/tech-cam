package com.techcam.service;

import com.techcam.dto.response.invoiceOrder.InvoiceOrderDetailResponse;
import com.techcam.dto.response.invoiceOrder.InvoiceOrderResponse;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/27/2022 8:22 PM
 * Project_name : tech-cam
 */

public interface IGoodsOrderService {

    List<InvoiceOrderDetailResponse> findAllOrderDetailByOrderId(String orderId);

    List<InvoiceOrderResponse> getAllInvoiceOrder();
}
