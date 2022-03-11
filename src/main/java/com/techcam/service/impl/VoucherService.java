package com.techcam.service.impl;

import com.techcam.dto.request.voucher.VoucherRequest;
import com.techcam.dto.response.voucher.VoucherResponse;
import com.techcam.entity.VoucherEntity;
import com.techcam.repo.ICategoryRepo;
import com.techcam.repo.IVoucherRepo;
import com.techcam.service.IVoucherService;
import com.techcam.constants.ConstantsErrorCode;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<VoucherResponse> getAllVoucher() {
        return voucherRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToVoucherDto).collect(Collectors.toList());
    }

    @Override
    public String createVoucher(VoucherRequest voucherRequest) {
        if (Objects.isNull(voucherRequest)) {
            return ConstantsErrorCode.ERROR;
        }
        // check voucher đã tồnt ại hay chưa
        if (!voucherRepo.findAllByCodeAndDeleteFlagIsFalse(voucherRequest.getVoucherCode()).isEmpty()) {
            return ConstantsErrorCode.ERROR;
        }
        VoucherEntity voucherEntity = mapToVoucherEntity(voucherRequest);
        voucherEntity.setId(UUID.randomUUID().toString());
        if (Objects.isNull(voucherEntity)) {
            return ConstantsErrorCode.ERROR;
        }
        try {
            voucherRepo.save(voucherEntity);
            return ConstantsErrorCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ConstantsErrorCode.ERROR;
        }
    }

    @Override
    public String updateVoucher(VoucherRequest voucherRequest) {
        if (Objects.isNull(voucherRequest)) {
            return ConstantsErrorCode.ERROR;
        }
        VoucherEntity voucherEntity = voucherRepo.getByIdAndDeleteFlagIsFalse(voucherRequest.getId());
        if (Objects.isNull(voucherEntity)) {
            return ConstantsErrorCode.ERROR;
        }
        String voucherId = voucherEntity.getId();
        List<VoucherEntity> lstFindAllByCode = voucherRepo.findAllByCodeAndDeleteFlagIsFalse(voucherRequest.getVoucherCode())
                .stream().filter(e -> !e.getId().equals(voucherId)).collect(Collectors.toList());
        if (!lstFindAllByCode.isEmpty()) {
            return ConstantsErrorCode.ERROR;
        }
        voucherEntity = mapToVoucherEntity(voucherRequest);
        voucherEntity.setId(voucherId);
        try {
            voucherRepo.save(voucherEntity);
            return ConstantsErrorCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ConstantsErrorCode.ERROR;
        }
    }

    @Override
    public String deleteVoucher(String id) {
        VoucherEntity voucherEntity = voucherRepo.getByIdAndDeleteFlagIsFalse(id);
        if (Objects.isNull(voucherEntity)) {
            return ConstantsErrorCode.ERROR;
        }
        // TODO voucher đã được sử dụng cchuwa
        try {
            voucherRepo.delete(voucherEntity);
            return ConstantsErrorCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ConstantsErrorCode.ERROR;
        }
    }

    private VoucherEntity mapToVoucherEntity(VoucherRequest voucherRequest) {
        if (voucherRequest == null) return null;
        return VoucherEntity.builder()
                .name(voucherRequest.getVoucherName())
                .code(voucherRequest.getVoucherCode())
                .quantity(Integer.parseInt(voucherRequest.getQuantity()))
                .discount(Long.parseLong(voucherRequest.getDiscount()))
                .startDate(ConvertUtil.get().strToDate(voucherRequest.getStartDate(), "dd-MM-yyyy"))
                .endDate(ConvertUtil.get().strToDate(voucherRequest.getEndDate(), "dd-MM-yyyy"))
                .description(voucherRequest.getDescription())
                .minAmount(Integer.parseInt(voucherRequest.getMinAmount()))
                .category(categoryRepo.getByIdAndDeleteFlagIsFalse(voucherRequest.getCategoryId()))
                .status(voucherRequest.getStatus().equals("true"))
                .note(voucherRequest.getNote())
                .build();
    }

    private <R> VoucherResponse mapToVoucherDto(VoucherEntity voucherEntity) {
        if (voucherEntity == null) return new VoucherResponse();
        return VoucherResponse.builder()
                .id(voucherEntity.getId())
                .voucherCode(voucherEntity.getCode())
                .voucherName(voucherEntity.getName())
                .startDate(voucherEntity.getStartDate())
                .endDate(voucherEntity.getEndDate())
                .discount(voucherEntity.getDiscount())
                .minAmount(voucherEntity.getMinAmount())
                .quantity(voucherEntity.getQuantity())
                .description(voucherEntity.getDescription())
                .categoryId(Objects.isNull(voucherEntity.getCategory()) ? "" : voucherEntity.getCategory().getId())
                .status(voucherEntity.getStatus())
                .createDate(voucherEntity.getCreateDate())
                .createBy(voucherEntity.getCreateBy())
                .modifiedDate(voucherEntity.getModifierDate())
                .hidden(voucherEntity.getStartDate().compareTo(LocalDate.now()) < 0)
                .build();
    }

}
