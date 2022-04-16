package com.techcam.service.impl;

import com.techcam.dto.request.invoiceOrder.InvoiceOrderDetailRequest;
import com.techcam.dto.request.invoiceOrder.InvoiceOrderRequest;
import com.techcam.dto.response.invoiceOrder.InvoiceOrderDetailResponse;
import com.techcam.dto.response.invoiceOrder.InvoiceOrderResponse;
import com.techcam.entity.*;
import com.techcam.exception.TechCamExp;
import com.techcam.repo.*;
import com.techcam.service.IGoodsOrderService;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.techcam.constants.ConstantsErrorCode.PRODUCT_NOT_EXISTS;
import static com.techcam.constants.ConstantsErrorCode.VOUCHER_DATE_NOT_PAST;
import static com.techcam.type.CustomerStatus.*;

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

    private final ISupplierRepo supplierRepo;

    private final IGoodsreceiptRepo goodsreceiptRepo;

    private final IGoodsreceiptdetailRepo goodsreceiptdetailRepo;

    private final IProductRepo productRepo;

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

    @Override
    public InvoiceOrderResponse getByOrderId(String orderId) {
        return mapToInvoiceOrderResponse(goodsOrderRepo.getByIdAndDeleteFlagIsFalse(orderId));
    }

    @Override
    public List<InvoiceOrderResponse> getAllInvoiceOrderForInvoice() {
        return getAllInvoiceOrder().stream()
                .filter(e -> goodsreceiptRepo.findAllByOrderIdAndDeleteFlagIsFalse(e.getInvoiceOrderId()).isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String createOrderInvoice(InvoiceOrderRequest request) {
        GoodsOrderEntity goodsOrderEntity = mapToInvoiceOrderEntity(request);
        if (Objects.isNull(goodsOrderEntity)) {
            return FAILED.name();
        }
        goodsOrderEntity.setId(UUID.randomUUID().toString());
        goodsOrderEntity.setStatus(ON.name());
        List<GoodsOrderDetailEntity> lst = new ArrayList<>();
        for (InvoiceOrderDetailRequest x : request.getDetails()) {
            ProductEntity productEntity = productRepo.getByIdAndDeleteFlagIsFalse(x.getProductId());
            if (Objects.isNull(productEntity)) {
                throw new TechCamExp(PRODUCT_NOT_EXISTS);
            }
            GoodsOrderDetailEntity s = GoodsOrderDetailEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .productId(x.getProductId())
                    .goodsOrdersId(goodsOrderEntity.getId())
                    .itemQuantity(x.getQuantity())
                    .status(ON.name())
                    .note("ADD ORDER INVOICE")
                    .build();
            lst.add(s);
        }
        try {
            goodsOrderRepo.save(goodsOrderEntity);
            goodsOrderDetailRepo.saveAll(lst);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FAILED.name();
    }

    @Override
    public String updateOrderInvoice(InvoiceOrderRequest request) {
        GoodsOrderEntity find = goodsOrderRepo.getByIdAndDeleteFlagIsFalse(request.getId());
        if (Objects.isNull(find)) {
            throw new TechCamExp("ERROR_NOT_EXISTS", "Hoá đơn đặt hàng");
        }
        GoodsOrderEntity goodsOrderEntity = mapToInvoiceOrderEntity(request);
        if (Objects.isNull(goodsOrderEntity)) {
            return FAILED.name();
        }
        if(goodsOrderEntity.getOrderDelivery().compareTo(find.getOrderDate()) < 0){
            throw new TechCamExp(VOUCHER_DATE_NOT_PAST);
        }
        List<GoodsOrderDetailEntity> lst = new ArrayList<>();
        for (InvoiceOrderDetailRequest x : request.getDetails()) {
            ProductEntity productEntity = productRepo.getByIdAndDeleteFlagIsFalse(x.getProductId());
            if (Objects.isNull(productEntity)) {
                return FAILED.name();
            }
            GoodsOrderDetailEntity f = goodsOrderDetailRepo.findAllByProductIdAndDeleteFlagIsFalse(x.getProductId())
                    .stream().findFirst().orElse(null);
            if (Objects.nonNull(f)) {
                f.setItemQuantity(x.getQuantity());
                f.setNote("EDIT QUANTITY ORDER INVOICE");
                lst.add(f);
                continue;
            }
            GoodsOrderDetailEntity s = GoodsOrderDetailEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .productId(x.getProductId())
                    .goodsOrdersId(goodsOrderEntity.getId())
                    .itemQuantity(x.getQuantity())
                    .status(ON.name())
                    .note("EDIT ORDER INVOICE")
                    .build();
            lst.add(s);
        }
        try {
            List<String> lstId = lst.stream().map(GoodsOrderDetailEntity::getId).collect(Collectors.toList());
            List<GoodsOrderDetailEntity> lstFind = goodsOrderDetailRepo.findAllByGoodsOrdersIdAndDeleteFlagIsFalse(goodsOrderEntity.getOrderId());
            lstFind = lstFind.stream().filter(e -> !lstId.contains(e.getId())).collect(Collectors.toList());
            lstFind.forEach(e -> e.setDeleteFlag(true));
            goodsOrderRepo.save(goodsOrderEntity);
            goodsOrderDetailRepo.saveAll(lst);
            goodsOrderDetailRepo.saveAll(lstFind);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FAILED.name();
    }

    private InvoiceOrderResponse mapToInvoiceOrderResponse(GoodsOrderEntity x) {
        if (Objects.isNull(x)) return InvoiceOrderResponse.builder().build();
        SupplierEntity supplierEntity = supplierRepo.getById(x.getSupplierId());
        List<GoodsreceiptEntity> lst = goodsreceiptRepo.findAllByOrderIdAndDeleteFlagIsFalse(x.getId());
//        Integer status = 0;
//        for (GoodsreceiptEntity s : lst) {
//            List<GoodsreceiptdetailEntity> lstDetail = goodsreceiptdetailRepo.findAllByProductIdAndDeleteFlagIsFalse(s.getId());
//            for (GoodsreceiptdetailEntity z : lstDetail) {
//                List<GoodsOrderDetailEntity> lstOrderDetail = goodsOrderDetailRepo.findAllByProductIdAndDeleteFlagIsFalse(z.getProductId());
//                int quantityInvoice = lstOrderDetail.stream().mapToInt(e -> e.getItemQuantity()).sum();
//                if (quantityInvoice > z.getQuantityActual()) {
//                    status = 1;
//                    break;
//                } else if (quantityInvoice < z.getQuantityActual()) {
//                    status = -1;
//                    break;
//                }
//            }
//            if (status != 0) {
//                break;
//            }
//        }
        Date dateInvoice = lst.stream().map(BaseEntity::getCreateDate).min(Timestamp::compareTo).orElse(null);
        return InvoiceOrderResponse.builder()
                .invoiceOrderId(x.getId())
                .invoiceOrderCode(x.getOrderId())
                .invoiceSupplierId(x.getSupplierId())
                .invoiceSupplierName(Objects.isNull(supplierEntity) ? "" : supplierEntity.getName())
                .orderDate(x.getOrderDate())
                .orderStaff(x.getOrderStaff())
                .orderDelivery(x.getOrderDelivery())
                .status(x.getStatus())
                .note(x.getNote())
                .dateInvoice(dateInvoice)
//                .statusInvoice(status)
                .build();
    }

    private InvoiceOrderDetailResponse mapToInvoiceOrderDetailResponse(GoodsOrderDetailEntity x) {
        if (Objects.isNull(x)) return InvoiceOrderDetailResponse.builder().build();
        return InvoiceOrderDetailResponse.builder()
                .productId(x.getProductId())
                .quantity(x.getItemQuantity())
                .quantityActual(0)
                .price(Long.parseLong("0"))
                .build();
    }

    private GoodsOrderEntity mapToInvoiceOrderEntity(InvoiceOrderRequest x) {
        if (Objects.isNull(x)) return null;
        SupplierEntity supplierEntity = supplierRepo.getByIdAndDeleteFlagIsFalse(x.getSupplierId());
        Date date = ConvertUtil.get().strToDate(x.getDate(), "dd-MM-yyyy");
        return GoodsOrderEntity.builder()
                .id(x.getId())
                .orderId(x.getCode())
                .supplierId(Objects.isNull(supplierEntity) ? null : x.getSupplierId())
                .orderDate(LocalDate.now())
                .orderDelivery(Instant.ofEpochMilli(date.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())
                // TODO user login
                .orderStaff("ADMIN")
                .note(x.getNote())
                .build();
    }

}
