package com.techcam.service.impl;

import com.techcam.dto.request.VoucherResDto;
import com.techcam.dto.response.VoucherRespDto;
import com.techcam.entity.CategoryEntity;
import com.techcam.entity.VoucherEntity;
import com.techcam.repo.ICategoryRepo;
import com.techcam.repo.IVoucherRepo;
import com.techcam.service.IVoucherService;
import com.techcam.util.ConstantsErrorCode;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
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
    public List<VoucherRespDto> getAllVoucher() {
        return voucherRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToVoucherDto).collect(Collectors.toList());
    }

    @Override
    public String createVoucher(VoucherResDto voucherResDto) {
        if (Objects.isNull(voucherResDto)) {
            return ConstantsErrorCode.ERROR;
        }
        // check voucher đã tồnt ại hay chưa
        if (!voucherRepo.findAllByCodeAndDeleteFlagIsFalse(voucherResDto.getVoucherCode()).isEmpty()) {
            return ConstantsErrorCode.ERROR;
        }
        VoucherEntity voucherEntity = mapToVoucherEntity(voucherResDto);
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
    public String updateVoucher(VoucherResDto voucherResDto) {
        if (Objects.isNull(voucherResDto)) {
            return ConstantsErrorCode.ERROR;
        }
        VoucherEntity voucherEntity = voucherRepo.getByIdAndDeleteFlagIsFalse(voucherResDto.getId());
        if (Objects.isNull(voucherEntity)) {
            return ConstantsErrorCode.ERROR;
        }
        String voucherId = voucherEntity.getId();
        List<VoucherEntity> lstFindAllByCode = voucherRepo.findAllByCodeAndDeleteFlagIsFalse(voucherResDto.getVoucherCode())
                .stream().filter(e -> !e.getId().equals(voucherId)).collect(Collectors.toList());
        if (!lstFindAllByCode.isEmpty()) {
            return ConstantsErrorCode.ERROR;
        }
        voucherEntity = mapToVoucherEntity(voucherResDto);
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

    private VoucherEntity mapToVoucherEntity(VoucherResDto voucherResDto) {
        if (voucherResDto == null) return null;
        return VoucherEntity.builder()
                .name(voucherResDto.getVoucherName())
                .code(voucherResDto.getVoucherCode())
                .quantity(Integer.parseInt(voucherResDto.getQuantity()))
                .discount(Long.parseLong(voucherResDto.getDiscount()))
                .startDate(ConvertUtil.get().strToDate(voucherResDto.getStartDate(), "dd-MM-yyyy"))
                .endDate(ConvertUtil.get().strToDate(voucherResDto.getEndDate(), "dd-MM-yyyy"))
                .description(voucherResDto.getDescription())
                .minAmount(Integer.parseInt(voucherResDto.getMinAmount()))
                .category(categoryRepo.getByIdAndDeleteFlagIsFalse(voucherResDto.getCategoryId()))
                .status(voucherResDto.getStatus().equals("true"))
                .note(voucherResDto.getNote())
                .build();
    }

    private <R> VoucherRespDto mapToVoucherDto(VoucherEntity voucherEntity) {
        if (voucherEntity == null) return new VoucherRespDto();
        return VoucherRespDto.builder()
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
