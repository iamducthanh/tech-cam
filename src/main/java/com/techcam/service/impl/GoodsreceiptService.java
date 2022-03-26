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
        return goodsreceiptRepo.findAllByDeleteFlagIsFalse()
                .stream().map(this::mapToInvoiceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceResponse getByInvoiceId(String id) {
        return null;
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
    public String createInvoice(InvoiceRequest invoiceRequest) {
        GoodsreceiptEntity goodsreceiptEntity = mapToInvoiceEntity(invoiceRequest);
        if (Objects.isNull(goodsreceiptEntity)) return FAILED.name();
        SupplierEntity supplierEntity = supplierRepo.getByIdAndDeleteFlagIsFalse(invoiceRequest.getSupplierId());
        if (Objects.isNull(supplierEntity)) return FAILED.name();
        long totalMoney = Long.parseLong("0");
        goodsreceiptEntity.setTotalAmount(Math.toIntExact(totalMoney));
        goodsreceiptEntity.setId(UUID.randomUUID().toString());
        List<GoodsreceiptdetailEntity> lstDetails = new ArrayList<>();
        for (InvoiceDetailRequest x : invoiceRequest.getDetails()) {
            if (x.getQuantity() < 1) return FAILED.name();
            totalMoney += x.getPrice() * x.getQuantity();
            lstDetails.add(GoodsreceiptdetailEntity.builder()
                    .goodsReceiptId(goodsreceiptEntity.getId())
                    .productId(x.getProductId())
                    .quantity(x.getQuantity())
                    .price(Math.toIntExact(x.getPrice()))
                    .status(ON.name())
                    .note("import invoice")
                    .build());
        }
        try {
            goodsreceiptRepo.save(goodsreceiptEntity);
            goodsreceiptdetailRepo.saveAll(lstDetails);
            return SUCCESS.name();
        } catch (Exception e) {
            return FAILED.name();
        }
    }

    private GoodsreceiptEntity mapToInvoiceEntity(InvoiceRequest x) {
        if (Objects.isNull(x)) return null;
        return GoodsreceiptEntity.builder()
                // TODO
                .build();
    }

    @Override
    public String updateInvoice(InvoiceRequest invoiceRequest) {
        return null;
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
        return InvoiceResponse.builder()
                .invoiceId(x.getId())
                .invoiceCode("")// mã hoá đơn nhập hàng
                .supplierId(x.getSupplierId())
                .supplierName(Objects.nonNull(supplierEntity) ? supplierEntity.getName() : "")
                .orderInvoiceCode("")// mã đặt hàng
                .status(x.getStatus())
                .discount(Long.parseLong("0")) // giá giảm
                .paid(paid)
                .note(x.getNote())
                .build();
    }

}
