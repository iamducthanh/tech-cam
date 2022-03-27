package com.techcam.service.impl;

import com.techcam.dto.request.voucher.VoucherRequest;
import com.techcam.dto.response.voucher.VoucherResponse;
import com.techcam.entity.CategoryEntity;
import com.techcam.entity.VoucherCustomerEntity;
import com.techcam.entity.VoucherEntity;
import com.techcam.repo.ICategoryRepo;
import com.techcam.repo.IVoucherRepo;
import com.techcam.repo.VoucherCustomerRepo;
import com.techcam.service.IVoucherService;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static com.techcam.type.CommonStatus.SUCCESS;
import static com.techcam.type.CustomerStatus.FAILED;
import static org.apache.commons.lang3.BooleanUtils.OFF;
import static org.apache.commons.lang3.BooleanUtils.ON;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 4:42 PM
 * Project_name : tech-cam
 */

@Service
@RequiredArgsConstructor
public class VoucherService implements IVoucherService {

    private final IVoucherRepo voucherRepo;

    private final ICategoryRepo categoryRepo;

    private final VoucherCustomerRepo voucherCustomerRepo;

    @Override
    public List<VoucherResponse> getAllVoucher() {
        return voucherRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToVoucherDto).collect(Collectors.toList());
    }

    @Override
    public String activeVoucher(String id) {
        VoucherEntity voucherEntity = voucherRepo.getByIdAndDeleteFlagIsFalse(id);
        if (Objects.isNull(voucherEntity)) {
            return FAILED.name();
        }
        voucherEntity.setStatus(ON.toUpperCase());
        voucherRepo.save(voucherEntity);
        return SUCCESS.name();
    }

    @Override
    public List<String> findAllIdCustomerByVoucherId(String voucherId) {
        return voucherCustomerRepo.findAllByVoucherId(voucherId).stream().map(VoucherCustomerEntity::getCustomerId).collect(Collectors.toList());
    }

    @Override
    public VoucherResponse getById(String voucherId) {
        return mapToVoucherDto(voucherRepo.getByIdAndDeleteFlagIsFalse(voucherId));
    }

    @Override
    public List<VoucherResponse> findAllByCode(String code) {
        return voucherRepo.findAllByCodeAndDeleteFlagIsFalse(code).stream()
                .map(this::mapToVoucherDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String createVoucher(VoucherRequest voucherRequest) {
        if (Objects.isNull(voucherRequest)) {
            return FAILED.name();
        }
        // check voucher đã tồnt ại hay chưa
        if (!voucherRepo.findAllByCodeAndDeleteFlagIsFalse(voucherRequest.getVoucherCode()).isEmpty()) {
            return FAILED.name();
        }
        VoucherEntity voucherEntity = mapToVoucherEntity(voucherRequest, new VoucherEntity());
        voucherEntity.setId(UUID.randomUUID().toString());
        voucherEntity.setStatus(ON.toUpperCase());
        List<VoucherCustomerEntity> lstVoucherCustomerEntities = new ArrayList<>();
        if (Objects.nonNull(voucherRequest.getTypeDiscountPerson())) {
            for (String x : voucherRequest.getTypeDiscountPerson()) {
                lstVoucherCustomerEntities.add(VoucherCustomerEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .customerId(x)
                        .voucherId(voucherEntity.getId())
                        .status(OFF.toUpperCase())
                        .discount(voucherEntity.getDiscount())
                        .startDatte(voucherEntity.getStartDate())
                        .endDate(voucherEntity.getEndDate())
                        .build());
            }
        }
        try {
            voucherRepo.save(voucherEntity);
            voucherCustomerRepo.saveAll(lstVoucherCustomerEntities);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED.name();
        }
    }

    @Override
    @Transactional
    public String updateVoucher(VoucherRequest voucherRequest) {
        if (Objects.isNull(voucherRequest)) {
            return FAILED.name();
        }
        VoucherEntity voucherEntity = voucherRepo.getByIdAndDeleteFlagIsFalse(voucherRequest.getVoucherId());
        if (Objects.isNull(voucherEntity)) {
            return FAILED.name();
        }
        String voucherId = voucherEntity.getId();
        List<VoucherEntity> lstFindAllByCode = voucherRepo.findAllByCodeAndDeleteFlagIsFalse(voucherRequest.getVoucherCode())
                .stream().filter(e -> !e.getId().equals(voucherId)).collect(Collectors.toList());
        if (!lstFindAllByCode.isEmpty()) {
            return FAILED.name();
        }
        Timestamp createDate = voucherEntity.getCreateDate();
        voucherEntity = mapToVoucherEntity(voucherRequest, voucherEntity);
        voucherEntity.setId(voucherId);
        voucherEntity.setCreateDate(createDate);
        List<VoucherCustomerEntity> lstVoucherCustomerEntities = new ArrayList<>();
        System.out.println(voucherRequest.getTypeDiscountPerson());
        if (Objects.nonNull(voucherRequest.getTypeDiscountPerson())) {
            for (String x : voucherRequest.getTypeDiscountPerson()) {
                lstVoucherCustomerEntities.add(VoucherCustomerEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .customerId(x)
                        .voucherId(voucherEntity.getId())
                        .status(OFF.toUpperCase())
                        .discount(voucherEntity.getDiscount())
                        .startDatte(voucherEntity.getStartDate())
                        .endDate(voucherEntity.getEndDate())
                        .build());
            }
        }
        List<VoucherCustomerEntity> lstVoucherCustomerDelete = voucherCustomerRepo.findAllByVoucherId(voucherId);
        lstVoucherCustomerDelete.forEach(e -> e.setDeleteFlag(true));
        lstVoucherCustomerEntities.addAll(lstVoucherCustomerDelete);
        try {
            voucherRepo.save(voucherEntity);
            voucherCustomerRepo.saveAll(lstVoucherCustomerEntities);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED.name();
        }
    }

    @Override
    public String deleteVoucher(String id) {
        VoucherEntity voucherEntity = voucherRepo.getByIdAndDeleteFlagIsFalse(id);
        if (Objects.isNull(voucherEntity)) {
            return FAILED.name();
        }
        // TODO voucher đã được sử dụng cchuwa
        try {
            voucherEntity.setDeleteFlag(true);
            voucherRepo.save(voucherEntity);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED.name();
        }
    }

    private VoucherEntity mapToVoucherEntity(VoucherRequest x, VoucherEntity s) {
        if (x == null) return null;
        CategoryEntity categoryEntity = categoryRepo.getByIdAndDeleteFlagIsFalse(x.getVoucherCategory());
        return s.toBuilder()
                .id(x.getVoucherId())
                .name(x.getVoucherName())
                .code(x.getVoucherCode())
                .quantity(Integer.parseInt(x.getVoucherQuantity()))
                .discount(Long.parseLong(x.getVoucherDiscount()))
                .endDate(ConvertUtil.get().strToDate(x.getVoucherEndDate(), "dd-MM-yyyy"))
                .startDate(ConvertUtil.get().strToDate(x.getVoucherStartDate(), "dd-MM-yyyy"))
                .description(x.getVoucherDescription())
                .typeDiscount(x.getVoucherTypeDiscount())
                .typeDiscountMoneyMin(Objects.isNull(x.getTypeDiscountMoneyMin()) ? 0 : Long.parseLong(x.getTypeDiscountMoneyMin()))
                .minAmount(Integer.parseInt(x.getVoucherMoneyMin()))
                .categoryId(Objects.isNull(categoryEntity) ? null : categoryEntity.getId())
                .build();
    }

    private <R> VoucherResponse mapToVoucherDto(VoucherEntity voucherEntity) {
        if (voucherEntity == null) return new VoucherResponse();
        return VoucherResponse.builder()
                .voucherId(voucherEntity.getId())
                .voucherCode(voucherEntity.getCode())
                .voucherName(voucherEntity.getName())
                .voucherStartDate(voucherEntity.getStartDate())
                .voucherEndDate(voucherEntity.getEndDate())
                .voucherDiscount(voucherEntity.getDiscount())
                .voucherMinAmount(voucherEntity.getMinAmount())
                .voucherQuantity(voucherEntity.getQuantity())
                .voucherDescription(voucherEntity.getDescription())
                .categoryId(Objects.isNull(voucherEntity.getCategoryId()) ? "" : voucherEntity.getCategoryId())
                .voucherStatus(voucherEntity.getStatus())
                .voucherCreateDate(voucherEntity.getCreateDate())
                .voucherCreateBy(voucherEntity.getCreateBy())
                .voucherModifiedDate(voucherEntity.getModifierDate())
                .voucherHidden(voucherEntity.getStartDate().compareTo(new Date()) < 0)
                .voucherTypeDiscount(voucherEntity.getTypeDiscount())
                .typeDiscountMinAmount(voucherEntity.getTypeDiscountMoneyMin())
                .build();
    }

}
