package com.techcam.service.impl;

import com.techcam.dto.error.ErrorRespDto;
import com.techcam.dto.request.VoucherResDto;
import com.techcam.dto.response.VoucherRespDto;
import com.techcam.entity.VoucherEntity;
import com.techcam.exception.DuplicateKeyConfig;
import com.techcam.exception.IllegalStateConfig;
import com.techcam.repo.IVoucherRepo;
import com.techcam.service.IVoucherService;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public VoucherRespDto checkVoucher(String voucherCode) {
        if (voucherCode == null) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Không có dữ liệu")
                    .build());
        }
        List<VoucherEntity> lstVouchers = voucherRepo.findByVoucherCode(voucherCode);
        if (lstVouchers.isEmpty()) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Không có dữ liệu")
                    .build());
        }
        return mapToVoucherResp(lstVouchers.get(0));
    }

    @Override
    public VoucherRespDto createVoucher(VoucherResDto voucherResDto) {
        if (voucherResDto == null) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Không có dữ liệu")
                    .build());
        }
        if (!voucherRepo.findByVoucherCode(voucherResDto.getVoucherCode()).isEmpty()) {
            throw new DuplicateKeyConfig(ErrorRespDto.builder()
                    .message("Mã voucher đã tồn tại rồi")
                    .build());
        }
        VoucherEntity voucherEntity = mapToVoucherEntity(voucherResDto);
        voucherRepo.save(voucherEntity);
        return mapToVoucherResp(voucherEntity);
    }

    private VoucherEntity mapToVoucherEntity(VoucherResDto voucherResDto) {
        if (voucherResDto == null) return null;
        VoucherEntity voucherEntity = VoucherEntity.builder()
                .name(voucherResDto.getName())
                .voucherCode(voucherResDto.getVoucherCode())
                .quantity(voucherResDto.getQuantity())
                .startDate(ConvertUtil.get().strToDate(voucherResDto.getStartDate(), "yyyy-MM-dd hh:mm").toInstant())
                .endDate(ConvertUtil.get().strToDate(voucherResDto.getEndDate(), "yyyy-MM-dd hh:mm").toInstant())
                .note(voucherResDto.getNote())
                .discount(voucherResDto.getDiscount())
                .build();
        voucherEntity.setId(voucherResDto.getId());
        return voucherEntity;
    }

    private VoucherRespDto mapToVoucherResp(VoucherEntity voucherEntity) {
        if (voucherEntity == null) return null;
        return VoucherRespDto.builder()
                .id(voucherEntity.getId())
                .name(voucherEntity.getName())
                .voucherCode(voucherEntity.getVoucherCode())
                .startDate(voucherEntity.getStartDate().toString())
                .endDate(voucherEntity.getEndDate().toString())
                .note(voucherEntity.getNote())
                .discount(voucherEntity.getDiscount())
                .build();
    }

}
