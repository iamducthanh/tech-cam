package com.techcam.mapper;

import com.techcam.dto.request.promotion.PromotionRequestDTO;
import com.techcam.dto.response.PromotionResponseDTO;
import com.techcam.entity.PromotionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    PromotionEntity toPromotionEntity(PromotionRequestDTO promotionRequestDTO);

    List<PromotionEntity> toPromotionEntities(List<PromotionRequestDTO> promotionRequestDTOs);

    PromotionResponseDTO toPromotionResponseDTO(PromotionEntity promotionEntity);

    List<PromotionResponseDTO> toPromotionResponseDTOs(List<PromotionEntity> promotionEntities);
}
