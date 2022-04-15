package com.techcam.service;

import com.techcam.dto.request.promotion.PromotionRequestDTO;
import com.techcam.dto.response.PromotionResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPromotionService {

    List<PromotionResponseDTO> getAll();

    PromotionResponseDTO findById(String id);

    @Transactional(rollbackFor = Exception.class)
    PromotionResponseDTO create(PromotionRequestDTO promotionRequestDTO);

    PromotionResponseDTO findByProductId(String productId);

    PromotionResponseDTO update(String id, PromotionRequestDTO promotionRequestDTO);

    void delete(String id);

    double getPromotionProduct(String id);
}
