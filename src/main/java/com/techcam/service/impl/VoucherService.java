package com.techcam.service.impl;

import com.techcam.dto.request.VoucherResDto;
import com.techcam.dto.response.VoucherRespDto;
import com.techcam.entity.VoucherEntity;
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
            // TODO return exception voucher không đúng
            return null;
        }
        List<VoucherEntity> lstVouchers = voucherRepo.findByVoucherCode(voucherCode);
        if (lstVouchers.isEmpty()) {
            // TODO return exception voucher không đúng
            return null;
        }
        return mapToVoucherResp(lstVouchers.get(0));
    }

    @Override
    public VoucherRespDto createVoucher(VoucherResDto voucherResDto) {
        if (voucherResDto == null) {
            // TODO trả về dữ liệu rỗng
            return null;
        }
        if (!voucherRepo.findByVoucherCode(voucherResDto.getVoucherCode()).isEmpty()) {
            // TODO trả về mã code đã tồn tại
            return null;
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
