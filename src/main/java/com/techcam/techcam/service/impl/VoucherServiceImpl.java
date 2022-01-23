package com.techcam.techcam.service.impl;

import com.techcam.techcam.dto.response.VoucherResp;
import com.techcam.techcam.entity.VoucherEntity;
import com.techcam.techcam.repo.VoucherRepo;
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
public class VoucherServiceImpl implements com.techcam.techcam.service.VoucherService {

    private final VoucherRepo voucherRepo;

    @Override
    public VoucherResp checkVoucher(String voucherCode) {
        if (voucherCode == null) {
            // TODO return exception voucher không đúng
        }
        List<VoucherEntity> lstVouchers = voucherRepo.findByVoucherCode(voucherCode);
        if (lstVouchers.isEmpty()) {
            // TODO return exception voucher không đúng
        }
        return mapToVoucherResp(lstVouchers.get(0));
    }

    private VoucherResp mapToVoucherResp(VoucherEntity voucherEntity) {
        if (voucherEntity == null) return null;
        return VoucherResp.builder()
                .name(voucherEntity.getName())
                .voucherCode(voucherEntity.getVoucherCode())
                .startDate(voucherEntity.getStartDate().toString())
                .endDate(voucherEntity.getEndDate().toString())
                .note(voucherEntity.getNote())
                .discount(voucherEntity.getDiscount())
                .build();
    }

}
