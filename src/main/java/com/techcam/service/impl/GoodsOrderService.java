package com.techcam.service.impl;

import com.techcam.dto.response.invoiceOrder.InvoiceOrderDetailResponse;
import com.techcam.dto.response.invoiceOrder.InvoiceOrderResponse;
import com.techcam.entity.GoodsOrderDetailEntity;
import com.techcam.entity.GoodsOrderEntity;
import com.techcam.repo.IGoodsOrderDetailRepo;
import com.techcam.repo.IGoodsOrderRepo;
import com.techcam.service.IGoodsOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/27/2022 8:22 PM
 * Project_name : tech-cam
 */

@Service
@RequiredArgsConstructor
public class GoodsOrderService implements IGoodsOrderService {

    private final IGoodsOrderDetailRepo goodsOrderDetailRepo;

    private final IGoodsOrderRepo goodsOrderRepo;

    @Override
    public List<InvoiceOrderDetailResponse> findAllOrderDetailByOrderId(String orderId) {
        return goodsOrderDetailRepo.findAllByGoodsOrdersIdAndDeleteFlagIsFalse(orderId)
                .stream().map(this::mapToInvoiceOrderDetailResponse).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceOrderResponse> getAllInvoiceOrder() {
        return goodsOrderRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToInvoiceOrderResponse).collect(Collectors.toList());
    }

    private <R> InvoiceOrderResponse mapToInvoiceOrderResponse(GoodsOrderEntity x) {
        if (Objects.isNull(x)) return InvoiceOrderResponse.builder().build();
        return InvoiceOrderResponse.builder()
                .invoiceOrderId(x.getId())
                .invoiceOrderCode(x.getOrderId())
                .invoiceSupplierId(x.getSupplierId())
                .build();
    }

    private <R> InvoiceOrderDetailResponse mapToInvoiceOrderDetailResponse(GoodsOrderDetailEntity x) {
        if (Objects.isNull(x)) return InvoiceOrderDetailResponse.builder().build();
        return InvoiceOrderDetailResponse.builder()
                .productId(x.getProductId())
                .quantity(x.getItemQuantity())
                .quantityActual(0)
                .price(Long.parseLong("0"))
                .build();
    }

}
