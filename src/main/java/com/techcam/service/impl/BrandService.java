package com.techcam.service.impl;

import com.techcam.dto.response.brand.BrandResponse;
import com.techcam.entity.BrandEntity;
import com.techcam.repo.IBrandRepo;
import com.techcam.service.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@Service
@RequiredArgsConstructor
public class BrandService implements IBrandService {

    private final IBrandRepo brandRepo;

    @Override
    public List<BrandResponse> getAllBrand() {
        return brandRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToBrandResponse).collect(Collectors.toList());
    }

    private <R> BrandResponse mapToBrandResponse(BrandEntity x) {
        if (Objects.isNull(x)) return new BrandResponse();
        return BrandResponse.builder()
                .brandId(x.getId())
                .brandName(x.getName())
                .brandEmail(x.getEmail())
                .brandAddress(x.getAddress())
                .brandAvatar(x.getAvatar())
                .brandNote(x.getNote())
                .brandStatus(x.getStatus())
                .brandPhone(x.getPhone())
                .build();
    }
}
