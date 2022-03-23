package com.techcam.service;

import com.techcam.dto.request.order.*;
import com.techcam.dto.response.order.GetInfoOrder;
import com.techcam.dto.response.order.GetInfoOrderDetails;
import com.techcam.dto.response.order.OrderResponse;

import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/20/2022
 * Project_name: Tech-cam
 */

public interface IOrderService {
    List<GetInfoOrder> getAllOrder();

    List<GetInfoOrder> getAllOrderByStatus(String status);

    List<GetInfoOrder> getAllOrderByDeleteFlag();

    List<GetInfoOrderDetails> findAllOrdersDetailsById(String id);

    OrderResponse resgistrationOrder(OrderRequest request);

    OrderResponse editOrderDetails(EditOrderDetailRequest requests);

    OrderResponse confirmOrderSalePerson(ConfirmSalePersonRequest request);

    OrderResponse confirmExportOrder(ConfirmExportOrderRequest request);

    OrderResponse editOrderDetailsConfirm(EditOrderDetailsConfirmRequest request);

    OrderResponse cancelOrder(String id);
}
