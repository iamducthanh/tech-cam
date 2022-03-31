package com.techcam.service;

import com.techcam.dto.request.order.*;
import com.techcam.dto.request.staff.StaffEditRequestDTO;
import com.techcam.dto.response.order.GetInfoOrder;
import com.techcam.dto.response.order.GetInfoOrderDetails;
import com.techcam.dto.response.order.OrderResponse;
import com.techcam.dto.response.voucher.VoucherUseByOrderResponse;

import javax.servlet.http.HttpServletRequest;
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

    GetInfoOrder getInfoOrderByBankTransaction(String bankTransaction);

    OrderResponse checkOutBank(String bankStatus, String bankTransaction);

    List<GetInfoOrder> getAllOrderByStatus(String status);

    List<GetInfoOrder> getAllOrderByDeleteFlag();

    GetInfoOrder findOrderById(String id);

    List<GetInfoOrderDetails> findAllOrdersDetailsById(String id);

    OrderResponse resgistrationOrder(OrderRequest request, HttpServletRequest httpServletRequest);

    OrderResponse editOrderDetails(EditOrderDetailRequest requests);

    OrderResponse confirmOrderSalePerson(ConfirmSalePersonRequest request);

    OrderResponse confirmExportOrder(ConfirmExportOrderRequest request);

    OrderResponse payTheBill(CustomerPayTheBillRequest request);

    OrderResponse editOrderDetailsConfirm(EditOrderDetailsConfirmRequest request);

    OrderResponse cancelOrder(String id);

    List<VoucherUseByOrderResponse> findAllByVoucherId(String voucherId);

    // Edit Order
    String editOrder(OrderRequest staff);
}
