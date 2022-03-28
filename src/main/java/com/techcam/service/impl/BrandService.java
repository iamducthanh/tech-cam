package com.techcam.service.impl;

import com.techcam.dto.request.brand.BrandAddRequestDTO;
import com.techcam.dto.response.brand.BrandResponse;
import com.techcam.entity.BrandEntity;
import com.techcam.repo.IBrandRepo;
import com.techcam.service.IBrandService;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    private final SessionUtil sessionUtil;

    @Override
    public List<BrandResponse> getAllBrand() {
        return brandRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToBrandResponse).collect(Collectors.toList());
    }

    @Override
    public String addBrand(BrandAddRequestDTO dto) {
        BrandEntity entity = new BrandEntity();
        if(brandRepo.findByEmailAndDeleteFlagIsFalse(dto.getEmail()).size() != 0){
            return "email đã tồn tại";
        }

        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setAvatar(dto.getAvatar());
        entity.setStatus("0");
        entity.setNote(dto.getNote());
        entity.setDeleteFlag(false);
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setCreateBy((String) sessionUtil.getObject("username"));
        entity.setModifierBy((String) sessionUtil.getObject("username"));
        brandRepo.save(entity);

        return "ok";
    }

    @Override
    public Integer findByEmailaAndDeleteFlagIsFalse(String email) {
           List<BrandEntity> list =  brandRepo.findByEmailAndDeleteFlagIsFalse(email);
           if(list.isEmpty()){
               return  0;
           }
           return list.size();
    }

    @Override
    public Integer findByPhoneAndDeleteFlagIsFalse(String phone) {
        List<BrandEntity> list =  brandRepo.findByPhoneAndDeleteFlagIsFalse(phone);
        if(list.isEmpty()){
            return  0;
        }
        return list.size();
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

    private BrandResponse entityToDto(BrandEntity brandEntity){
        BrandResponse brandResponse = new BrandResponse();
        brandResponse.setBrandId(brandEntity.getId());
        brandResponse.setBrandAddress(brandEntity.getAddress());
        brandResponse.setBrandAvatar(brandEntity.getAvatar());
        brandResponse.setBrandEmail(brandEntity.getEmail());
        brandResponse.setBrandName(brandEntity.getName());
        brandResponse.setBrandNote(brandEntity.getNote());
        brandResponse.setBrandPhone(brandEntity.getPhone());
        brandResponse.setBrandStatus(brandEntity.getStatus());
        return brandResponse;
    }
}
