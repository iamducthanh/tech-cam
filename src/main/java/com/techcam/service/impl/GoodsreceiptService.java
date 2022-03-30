package com.techcam.service.impl;

import com.techcam.dto.request.invoice.InvoiceDetailRequest;
import com.techcam.dto.request.invoice.InvoiceRequest;
import com.techcam.dto.response.invoice.InvoiceDetailResponse;
import com.techcam.dto.response.invoice.InvoiceResponse;
import com.techcam.entity.*;
import com.techcam.repo.*;
import com.techcam.service.IGoodsreceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.techcam.type.CustomerStatus.*;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 10:05 PM
 * Project_name : tech-cam
 */

@Service
@RequiredArgsConstructor
public class GoodsreceiptService implements IGoodsreceiptService {

    private final IGoodsreceiptRepo goodsreceiptRepo;

    private final IGoodsreceiptdetailRepo goodsreceiptdetailRepo;

    private final IGoodsOrderRepo goodsOrderRepo;

    private final ISupplierRepo supplierRepo;

    private final IProductRepo productRepo;

    private final ICategoryRepo categoryRepo;

    private final IBrandRepo brandRepo;

    @Override
    public List<InvoiceResponse> findAllByInvoiceCode(String invoiceCode) {
        // TODO chưa có trường CODE
//        return goodsreceiptRepo.findAllByInvoiceCodeDeleteFlagIsFalse(invoiceCode)
//                .stream().map(this::mapToInvoiceResponse)
//                .collect(Collectors.toList());
        return goodsreceiptRepo.findAllByReceiptIdAndDeleteFlagIsFalse(invoiceCode)
                .stream().map(this::mapToInvoiceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceResponse getByInvoiceId(String id) {
        return mapToInvoiceResponse(goodsreceiptRepo.getByIdAndDeleteFlagIsFalse(id));
    }

    @Override
    public String deleteById(String id) {
        try {
            goodsreceiptRepo.deleteById(id);
            return SUCCESS.name();
        } catch (Exception e) {
            return FAILED.name();
        }
    }

    @Override
    public List<InvoiceResponse> findAllInvoice() {
        return goodsreceiptRepo.findAllByDeleteFlagIsFalse()
                .stream().map(this::mapToInvoiceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDetailResponse> findAllInvoiceDetailByInvoiceId(String invoiceId) {
        return goodsreceiptdetailRepo.findAllByGoodsReceiptIdAndDeleteFlagIsFalse(invoiceId)
                .stream().map(this::mapToInvoiceDetailReponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String createInvoice(InvoiceRequest invoiceRequest) {
        try {
            GoodsreceiptEntity goodsreceiptEntity = mapToInvoiceEntity(invoiceRequest);
            if (Objects.isNull(goodsreceiptEntity)) return FAILED.name();
            SupplierEntity supplierEntity = supplierRepo.getByIdAndDeleteFlagIsFalse(invoiceRequest.getSupplierId());
            if (Objects.isNull(supplierEntity)) return FAILED.name();
            long totalMoney = invoiceRequest.getDetails().stream()
                    .mapToLong(e -> e.getQuantity() * e.getPrice()).sum();
            goodsreceiptEntity.setTotalAmount(Math.toIntExact(totalMoney));
            goodsreceiptEntity.setId(UUID.randomUUID().toString());
            List<GoodsreceiptdetailEntity> lstDetails = new ArrayList<>();
            for (InvoiceDetailRequest x : invoiceRequest.getDetails()) {
                if (x.getQuantity() < 1) return FAILED.name();
                totalMoney += x.getPrice() * x.getQuantity();
                lstDetails.add(GoodsreceiptdetailEntity.builder()
                        .id(goodsreceiptEntity.getId())
                        .goodsReceiptId(goodsreceiptEntity.getId())
                        .productId(x.getProductId())
                        .quantity(x.getQuantity())
                        .price(BigDecimal.valueOf(x.getPrice()))
                        .status(ON.name())
                        .note("import invoice")
                        .build());
            }
            goodsreceiptRepo.save(goodsreceiptEntity);
            goodsreceiptdetailRepo.saveAll(lstDetails);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED.name();
        }
    }

    @Override
    @Transactional
    public String updateInvoice(InvoiceRequest invoiceRequest) {
        try {
            GoodsreceiptEntity goodsreceiptEntity = mapToInvoiceEntity(invoiceRequest);
            if (Objects.isNull(goodsreceiptEntity)) return FAILED.name();
            SupplierEntity supplierEntity = supplierRepo.getByIdAndDeleteFlagIsFalse(invoiceRequest.getSupplierId());
            if (Objects.isNull(supplierEntity)) return FAILED.name();
            long totalMoney = invoiceRequest.getDetails().stream()
                    .mapToLong(e -> e.getQuantity() * e.getPrice()).sum();
            goodsreceiptEntity.setTotalAmount(Math.toIntExact(totalMoney));
            List<GoodsreceiptdetailEntity> lstDetails = new ArrayList<>();
            for (InvoiceDetailRequest x : invoiceRequest.getDetails()) {
                if (x.getQuantity() < 1) return FAILED.name();
                GoodsreceiptdetailEntity goodsreceiptdetailEntity = goodsreceiptdetailRepo.findAllByGoodsReceiptIdAndProductIdAndDeleteFlagIsFalse(
                        invoiceRequest.getInvoiceCode(),
                        x.getProductId()
                ).stream().findFirst().orElse(null);
                if (Objects.nonNull(goodsreceiptdetailEntity)) {
                    goodsreceiptdetailEntity.setQuantity(x.getQuantity());
                    goodsreceiptdetailEntity.setPrice(BigDecimal.valueOf(x.getPrice()));
                    lstDetails.add(goodsreceiptdetailEntity);
                    continue;
                }
                totalMoney += x.getPrice() * x.getQuantity();
                lstDetails.add(GoodsreceiptdetailEntity.builder()
                        .id(goodsreceiptEntity.getId())
                        .goodsReceiptId(goodsreceiptEntity.getId())
                        .productId(x.getProductId())
                        .quantity(x.getQuantity())
                        .price(BigDecimal.valueOf(x.getPrice()))
                        .status(ON.name())
                        .note("import invoice")
                        .build());
            }
            goodsreceiptRepo.save(goodsreceiptEntity);
            List<GoodsreceiptdetailEntity> lstFind = goodsreceiptdetailRepo.findAllByGoodsReceiptIdAndDeleteFlagIsFalse(invoiceRequest.getInvoiceId());
            List<String> lstInvoiceDetailId = lstDetails.stream().map(GoodsreceiptdetailEntity::getId).collect(Collectors.toList());
            lstFind = lstFind.stream().filter(e -> !lstInvoiceDetailId.contains(e.getId())).collect(Collectors.toList());
            lstFind.forEach(e -> e.setDeleteFlag(true));
            goodsreceiptdetailRepo.saveAll(lstDetails);
            goodsreceiptdetailRepo.saveAll(lstFind);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED.name();
        }
    }

    private GoodsreceiptEntity mapToInvoiceEntity(InvoiceRequest x) {
        if (Objects.isNull(x)) return null;
        return GoodsreceiptEntity.builder()
                .id(x.getInvoiceId())
                .supplierId(x.getSupplierId())
                .status(x.getStatus() ? ON.name() : OFF.name())
                .note(x.getNote())
                .orderId(Objects.isNull(x.getInvoiceOrderId()) || x.getInvoiceOrderId().isEmpty() ? null : x.getInvoiceOrderId())
                .receiptId(x.getInvoiceCode())
                .discount(BigDecimal.valueOf(x.getDiscount()))
                .totalQuantity(0)
                .receiptStatus(ON.name())
                .build();
    }

    private <R> InvoiceDetailResponse mapToInvoiceDetailReponse(GoodsreceiptdetailEntity x) {
        if (Objects.isNull(x)) return new InvoiceDetailResponse();
        ProductEntity productEntity = productRepo.getByIdAndDeleteFlagIsFalse(x.getProductId());
        if (Objects.isNull(productEntity)) {
            return new InvoiceDetailResponse();
        }
        CategoryEntity categoryEntity = categoryRepo.getByIdAndDeleteFlagIsFalse(productEntity.getCategoryId());
        BrandEntity brandEntity = brandRepo.getByIdAndDeleteFlagIsFalse(productEntity.getBrandId());
        return InvoiceDetailResponse.builder()
                .idDetail(x.getId())
                .productId(x.getProductId())
                .productName(productEntity.getName())
                .productCode(productEntity.getProductCode())
                .categoryName(Objects.nonNull(categoryEntity) ? categoryEntity.getName() : "")
                .brandName(Objects.nonNull(brandEntity) ? brandEntity.getName() : "")
                .quantity(x.getQuantity())
                .price(Long.parseLong(String.valueOf(x.getPrice())))
                .build();
    }


    private <R> InvoiceResponse mapToInvoiceResponse(GoodsreceiptEntity x) {
        if (Objects.isNull(x)) return new InvoiceResponse();
        SupplierEntity supplierEntity = supplierRepo.getByIdAndDeleteFlagIsFalse(x.getSupplierId());
        Long paid = Long.parseLong("0"); // số tiền đã thanh toán cho nhà cung cấp = tổng tiền hoá đơn chi
        GoodsOrderEntity goodsOrderEntity = goodsOrderRepo.getByIdAndDeleteFlagIsFalse(x.getOrderId());
        return InvoiceResponse.builder()
                .invoiceId(x.getId())
                .invoiceCode(x.getReceiptId())// mã hoá đơn nhập hàng
                .supplierId(x.getSupplierId())
                .supplierName(Objects.nonNull(supplierEntity) ? supplierEntity.getName() : "")
                .orderInvoiceId(Objects.isNull(goodsOrderEntity) ? "" : goodsOrderEntity.getId())
                .orderInvoiceCode(Objects.isNull(goodsOrderEntity) ? "" : goodsOrderEntity.getOrderId())// mã đặt hàng
                .status(x.getStatus())
                .totalMoney((long) x.getTotalAmount())
                .discount(x.getDiscount().longValue()) // giá giảm
                .paid(paid)
                .note(x.getNote())
                .build();
    }

}
