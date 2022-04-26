package com.techcam.service.impl;

import com.techcam.dto.request.invoice.InvoiceDetailRequest;
import com.techcam.dto.request.invoice.InvoiceRequest;
import com.techcam.dto.response.invoice.InvoiceDetailResponse;
import com.techcam.dto.response.invoice.InvoiceResponse;
import com.techcam.entity.*;
import com.techcam.exception.TechCamExp;
import com.techcam.repo.*;
import com.techcam.service.IGoodsreceiptService;
import com.techcam.service.IProductService;
import com.techcam.util.ConvertDateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.techcam.constants.ConstantsErrorCode.INVOICE_INVENTORY;
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

    private final IProductService productService;

    private final IOrderDetailsRepo orderDetailsRepo;

    @Override
    public List<InvoiceResponse> findAllByInvoiceCode(String invoiceCode) {
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
                    .mapToLong(e -> (long) (e.getQuantityActual() * e.getPrice())).sum();
            goodsreceiptEntity.setTotalAmount(Math.toIntExact(totalMoney));
            goodsreceiptEntity.setId(UUID.randomUUID().toString());
            goodsreceiptEntity.setStatus(ON.name());
            goodsreceiptEntity.setReceiptId(ConvertDateUtil.generationCode("NH"));
            List<GoodsreceiptdetailEntity> lstDetails = new ArrayList<>();
            for (InvoiceDetailRequest x : invoiceRequest.getDetails()) {
                if (x.getQuantityActual() < 1) return FAILED.name();
                totalMoney += x.getPrice() * x.getQuantity();
                lstDetails.add(GoodsreceiptdetailEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .goodsReceiptId(goodsreceiptEntity.getId())
                        .productId(x.getProductId())
                        .quantity(x.getQuantity())
                        .quantityActual(x.getQuantityActual())
                        .price(x.getPrice())
                        .status(ON.name())
                        .note("import invoice")
                        .build());
            }

            goodsreceiptRepo.save(goodsreceiptEntity);

            int sumQuantity;
            ProductEntity productEntity;
            int importPriceToProduct;
            List<ProductEntity> lstProductEntity = new ArrayList<>();
            for (GoodsreceiptdetailEntity x : lstDetails) {
                productEntity = productRepo.getByIdAndDeleteFlagIsFalse(x.getProductId());
                importPriceToProduct = (int) ((((productEntity.getImportPrice() * productEntity.getQuantity())) + (x.getPrice() * x.getQuantityActual())) / (productEntity.getQuantity() + x.getQuantityActual()));
                productEntity.setImportPrice(importPriceToProduct);
                productEntity.setQuantity(productEntity.getQuantity() + x.getQuantityActual());
                lstProductEntity.add(productEntity);
            }
            productRepo.saveAll(lstProductEntity);
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
                    .mapToLong(e -> (long) (e.getQuantityActual() * e.getPrice())).sum();
            goodsreceiptEntity.setTotalAmount(Math.toIntExact(totalMoney));
            List<GoodsreceiptdetailEntity> lstDetails = new ArrayList<>();
            for (InvoiceDetailRequest x : invoiceRequest.getDetails()) {
                if (x.getQuantityActual() < 1) return FAILED.name();
                GoodsreceiptdetailEntity goodsreceiptdetailEntity = goodsreceiptdetailRepo.findAllByGoodsReceiptIdAndProductIdAndDeleteFlagIsFalse(
                        goodsreceiptEntity.getReceiptId(),
                        x.getProductId()
                ).stream().findFirst().orElse(null);
                if (Objects.nonNull(goodsreceiptdetailEntity)) {
                    goodsreceiptdetailEntity.setQuantity(x.getQuantity());
                    goodsreceiptdetailEntity.setPrice(x.getPrice());
                    lstDetails.add(goodsreceiptdetailEntity);
                    continue;
                }
                totalMoney += x.getPrice() * x.getQuantity();
                lstDetails.add(GoodsreceiptdetailEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .goodsReceiptId(goodsreceiptEntity.getId())
                        .productId(x.getProductId())
                        .quantity(x.getQuantity())
                        .quantityActual(x.getQuantityActual())
                        .price(x.getPrice())
                        .status(ON.name())
                        .note("import invoice")
                        .build());
            }
            goodsreceiptRepo.save(goodsreceiptEntity);

            List<GoodsreceiptdetailEntity> lstFind = goodsreceiptdetailRepo.findAllByGoodsReceiptIdAndDeleteFlagIsFalse(invoiceRequest.getInvoiceId());

            ProductEntity productEntity;
            int importPriceToProduct;
            List<ProductEntity> lstProductEntity = new ArrayList<>();
            for (GoodsreceiptdetailEntity x : lstFind) {
                productEntity = productRepo.getByIdAndDeleteFlagIsFalse(x.getProductId());
                importPriceToProduct = (int) (((productEntity.getQuantity() * productEntity.getImportPrice()) + x.getQuantityActual() * (productEntity.getImportPrice() - x.getPrice())) / x.getQuantityActual());
                productEntity.setImportPrice(importPriceToProduct);
                productEntity.setQuantity(productEntity.getQuantity() - x.getQuantityActual());
                lstProductEntity.add(productEntity);
            }
            productRepo.saveAll(lstProductEntity);

            lstProductEntity = new ArrayList<>();
            for (GoodsreceiptdetailEntity x : lstDetails) {
                productEntity = productRepo.getByIdAndDeleteFlagIsFalse(x.getProductId());
                importPriceToProduct = (int) ((((productEntity.getImportPrice() * productEntity.getQuantity())) + (x.getPrice() * x.getQuantityActual())) / (productEntity.getQuantity() + x.getQuantityActual()));
                productEntity.setImportPrice(importPriceToProduct);
                productEntity.setQuantity(productEntity.getQuantity() + x.getQuantityActual());
                lstProductEntity.add(productEntity);
            }
            productRepo.saveAll(lstProductEntity);

            List<String> lstInvoiceDetailId = lstDetails.stream().map(GoodsreceiptdetailEntity::getId).collect(Collectors.toList());
            lstFind = lstFind.stream().filter(e -> !lstInvoiceDetailId.contains(e.getId())).collect(Collectors.toList());
            lstFind.forEach(e -> e.setDeleteFlag(true));
            goodsreceiptdetailRepo.saveAll(lstDetails);
            goodsreceiptdetailRepo.saveAll(lstFind);
            // kiểm tra số lượng tồn khi nếu như số lượng < 0 (tương ứng số bán nhiều hơn số nhập) sẽ báo lỗi không được phép sửa
            Set<String> lstProductId = lstDetails.stream().map(e -> e.getProductId()).collect(Collectors.toSet());
            for (String x : lstProductId) {
                int inventory = productService.getInventoryByProductId(x);
                if (inventory < 0) {
                    productEntity = productRepo.getByIdAndDeleteFlagIsFalse(x);
                    throw new TechCamExp(INVOICE_INVENTORY, productEntity.getName());
                }
            }
            return SUCCESS.name();
        } catch (TechCamExp e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED.name();
        }
    }

    private GoodsreceiptEntity mapToInvoiceEntity(InvoiceRequest x) {
        if (Objects.isNull(x)) return null;
        GoodsreceiptEntity goodsOrderEntity = goodsreceiptRepo.getByIdAndDeleteFlagIsFalse(x.getInvoiceId());
        if (Objects.isNull(goodsOrderEntity)) return null;
        GoodsreceiptEntity goodsreceiptEntity = goodsOrderEntity.toBuilder()
                .id(x.getInvoiceId())
                .supplierId(x.getSupplierId())
                .status(Objects.nonNull(x.getStatus()) && x.getStatus() ? ON.name() : OFF.name())
                .note(x.getNote())
                .orderId(Objects.isNull(x.getInvoiceOrderId()) || x.getInvoiceOrderId().isEmpty() ? null : x.getInvoiceOrderId())
//                .receiptId(x.getInvoiceCode().toUpperCase())
                .discount(BigDecimal.valueOf(x.getDiscount()))
                .deliverier(x.getShipper())
                .receiptStatus(ON.name())
                .build();
        return goodsOrderEntity;
    }

    private InvoiceDetailResponse mapToInvoiceDetailReponse(GoodsreceiptdetailEntity x) {
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
                .quantityActual(x.getQuantityActual())
                .price(x.getPrice())
                .build();
    }


    private InvoiceResponse mapToInvoiceResponse(GoodsreceiptEntity x) {
        if (Objects.isNull(x)) return new InvoiceResponse();
        SupplierEntity supplierEntity = supplierRepo.getByIdAndDeleteFlagIsFalse(x.getSupplierId());
        Long paid = Long.parseLong("0"); // số tiền đã thanh toán cho nhà cung cấp = tổng tiền hoá đơn chi
        GoodsOrderEntity goodsOrderEntity = goodsOrderRepo.getByIdAndDeleteFlagIsFalse(x.getOrderId());
        return InvoiceResponse.builder()
                .invoiceId(x.getId())
                .invoiceCode(x.getReceiptId().toUpperCase())// mã hoá đơn nhập hàng
                .supplierId(x.getSupplierId())
                .supplierName(Objects.nonNull(supplierEntity) ? supplierEntity.getName() : "")
                .orderInvoiceId(Objects.isNull(goodsOrderEntity) ? "" : goodsOrderEntity.getId())
                .orderInvoiceCode(Objects.isNull(goodsOrderEntity) ? "" : goodsOrderEntity.getOrderId())// mã đặt hàng
                .status(x.getStatus())
                .totalMoney((long) x.getTotalAmount())
                .discount(x.getDiscount().longValue()) // giá giảm
                .paid(paid)
                .shipper(x.getDeliverier())
                .note(x.getNote())
                .createDate(x.getCreateDate())
                .build();
    }

}
