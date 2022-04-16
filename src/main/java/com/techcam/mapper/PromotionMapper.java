package com.techcam.mapper;

import com.techcam.dto.request.promotion.PromotionRequestDTO;
import com.techcam.dto.response.PromotionResponseDTO;
import com.techcam.entity.PromotionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface PromotionMapper {

    @Mapping(target = "startDate", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "endDate", dateFormat = "dd-MM-yyyy")
    PromotionEntity toPromotionEntity(PromotionRequestDTO promotionRequestDTO);

    List<PromotionEntity> toPromotionEntities(List<PromotionRequestDTO> promotionRequestDTOs);

    PromotionResponseDTO toPromotionResponseDTO(PromotionEntity promotionEntity);

    List<PromotionResponseDTO> toPromotionResponseDTOs(List<PromotionEntity> promotionEntities);
}
