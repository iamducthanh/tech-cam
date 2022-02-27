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

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public VoucherRespDto checkVoucher(String voucherCode) {
        if (voucherCode == null) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Không có dữ liệu")
                    .date(LocalDateTime.now())
                    .build());
        }
        List<VoucherEntity> lstVouchers = voucherRepo.findByVoucherCode(voucherCode);
        if (lstVouchers.isEmpty()) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Không có dữ liệu")
                    .date(LocalDateTime.now())
                    .build());
        }
        return mapToVoucherResp(lstVouchers.get(0));
    }

    @Override
    public VoucherRespDto createVoucher(VoucherResDto voucherResDto) {
        if (voucherResDto == null) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Không có dữ liệu")
                    .date(LocalDateTime.now())
                    .build());
        }
        if (!voucherRepo.findByVoucherCode(voucherResDto.getVoucherCode()).isEmpty()) {
            throw new DuplicateKeyConfig(ErrorRespDto.builder()
                    .message("Mã voucher đã tồn tại rồi")
                    .date(LocalDateTime.now())
                    .build());
        }
        VoucherEntity voucherEntity = mapToVoucherEntity(voucherResDto);
        voucherEntity.setId(UUID.randomUUID().toString());
        voucherEntity.setStatus("Active");
        voucherEntity.setCreateDate(LocalDateTime.now());
        voucherEntity.setModifiedBy("System");
        voucherRepo.save(voucherEntity);
        return mapToVoucherResp(voucherEntity);
    }

    @Override
    public VoucherRespDto updateVoucher(VoucherResDto voucherResDto) {
        if (voucherResDto == null) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Không có dữ liệu")
                    .date(LocalDateTime.now())
                    .build());
        }
        VoucherEntity voucherEntity = voucherRepo.getById(voucherResDto.getId());
        if (voucherEntity == null) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Voucher không tồn tại")
                    .date(LocalDateTime.now())
                    .build());
        }
        voucherEntity.setName(voucherResDto.getVoucherName());
        voucherEntity.setQuantity(Integer.parseInt(voucherResDto.getQuantity()));
        voucherEntity.setStartDate(ConvertUtil.get().strToDate(voucherResDto.getStartDate(), "yyyy-MM-dd HH:mm").toInstant());
        voucherEntity.setEndDate(ConvertUtil.get().strToDate(voucherResDto.getEndDate(), "yyyy-MM-dd HH:mm").toInstant());
        voucherEntity.setNote(voucherResDto.getDescription());
        voucherEntity.setDiscount(Long.parseLong(voucherResDto.getDiscount()));
        voucherRepo.save(voucherEntity);
        return mapToVoucherResp(voucherEntity);
    }

    @Override
    public List<VoucherRespDto> getAllVoucher() {
        return voucherRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToVoucherResp).collect(Collectors.toList());
    }

    private VoucherEntity mapToVoucherEntity(VoucherResDto voucherResDto) {
        if (voucherResDto == null) return null;
        VoucherEntity voucherEntity = VoucherEntity.builder()
                .name(voucherResDto.getVoucherName())
                .voucherCode(voucherResDto.getVoucherCode())
                .quantity(Integer.parseInt(voucherResDto.getQuantity()))
                .startDate(ConvertUtil.get().strToDate(voucherResDto.getStartDate(), "yyyy-MM-dd hh:mm").toInstant())
                .endDate(ConvertUtil.get().strToDate(voucherResDto.getEndDate(), "yyyy-MM-dd hh:mm").toInstant())
                .note(voucherResDto.getDescription())
                .discount(Long.parseLong(voucherResDto.getDiscount()))
                .build();
        voucherEntity.setId(voucherResDto.getId());
        return voucherEntity;
    }

    private VoucherRespDto mapToVoucherResp(VoucherEntity voucherEntity) {
        if (voucherEntity == null) return null;
        return VoucherRespDto.builder()
                .id(voucherEntity.getId())
                .voucherName(voucherEntity.getName())
                .voucherCode(voucherEntity.getVoucherCode())
//                .startDate(voucherEntity.getStartDate())
//                .endDate(voucherEntity.getEndDate().toString())
//                .note(voucherEntity.getNote())
                .discount(voucherEntity.getDiscount())
                .hidden(voucherEntity.getStartDate().compareTo(Instant.now()) < 0)
                .build();
    }

}
