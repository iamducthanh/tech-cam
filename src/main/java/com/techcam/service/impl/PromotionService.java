package com.techcam.service.impl;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.promotion.PromotionRequestDTO;
import com.techcam.dto.response.product.ProductResponseDTO;
import com.techcam.dto.response.PromotionResponseDTO;
import com.techcam.entity.ProductEntity;
import com.techcam.entity.PromotionEntity;
import com.techcam.entity.PromotionProductEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.mapper.ProductMapper;
import com.techcam.mapper.PromotionMapper;
import com.techcam.repo.IProductRepo;
import com.techcam.repo.IPromotionProductRepo;
import com.techcam.repo.IPromotionRepo;
import com.techcam.service.IPromotionService;
import com.techcam.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PromotionService implements IPromotionService {

    @Autowired
    IPromotionRepo promotionRepository;

    @Autowired
    IPromotionProductRepo promotionProductRepository;

    @Autowired
    IProductRepo productRepository;

    @Autowired
    PromotionMapper promotionMapper;

   @Autowired
    ProductMapper productMapper;


    @Override
    public List<PromotionResponseDTO> getAll(){
        List<PromotionEntity> promotions = promotionRepository.findAllByDeleteFlagFalse();
        return promotionMapper.toPromotionResponseDTOs(promotions);
    }

    @Override
    public PromotionResponseDTO findById(String id){
        Optional<PromotionEntity> promotionOptional = promotionRepository.findById(id);
        if(!promotionOptional.isPresent()){
            throw new TechCamExp(ConstantsErrorCode.PROMOTION_NOT_FOUND);
        }
        PromotionEntity promotionEntity = promotionOptional.get();
        List<PromotionProductEntity> promotionProducts = promotionProductRepository.findAllByPromotionId(promotionEntity.getId());
        List<String> productIds = promotionProducts.stream().map(x -> x.getProductId()).collect(Collectors.toList());
        List<ProductEntity> products = productRepository.findAllByIdInAndDeleteFlagFalse(productIds);
        PromotionResponseDTO promotionResponseDTO = promotionMapper.toPromotionResponseDTO(promotionEntity);
        promotionResponseDTO.setProducts(productMapper.toProductResponseDTOs(products));
        return promotionResponseDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PromotionResponseDTO create(PromotionRequestDTO promotionRequestDTO){
        promotionRequestDTO.getProductIds().forEach(x -> {
            if(promotionProductRepository.findByProductIdAndDeleteFlagFalse(x).isPresent()){
                throw new TechCamExp(ConstantsErrorCode.PROMOTION_PRODUCT_EXIST, productRepository.findById(x).get().getName());
            }
        });
        if(promotionRequestDTO.getEndDate().compareTo(promotionRequestDTO.getStartDate()) <= 0){
            throw new TechCamExp(ConstantsErrorCode.DATE_ERROR);
        }
        Date now = new Date();
        promotionRequestDTO.setStatus(false);
        if(promotionRequestDTO.getStartDate().compareTo(now) <= 0 && DateUtil.addDays(promotionRequestDTO.getEndDate(),1).compareTo(now) >= 0){
            promotionRequestDTO.setStatus(true);
        }
        PromotionEntity promotionEntity = promotionMapper.toPromotionEntity(promotionRequestDTO);
        promotionEntity = promotionRepository.save(promotionEntity);
        String promotionId = promotionEntity.getId();
        List<PromotionProductEntity> promotionProducts = new ArrayList<>();
       if(promotionRequestDTO.getProductIds().size()>0){
           promotionRequestDTO.getProductIds().forEach(x -> {
               PromotionProductEntity promotionProductEntity = new PromotionProductEntity();
               promotionProductEntity.setPromotionId(promotionId);
               promotionProductEntity.setProductId(x);
               promotionProducts.add(promotionProductEntity);
           });
           promotionProductRepository.saveAll(promotionProducts);
       }
        PromotionResponseDTO promotionResponseDTO = promotionMapper.toPromotionResponseDTO(promotionEntity);
        return promotionResponseDTO;
    }

    @Override
    public PromotionResponseDTO findByProductId(String productId){
        PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO();
        Optional<PromotionProductEntity> promotionProductEntityOptional = promotionProductRepository.findByProductIdAndDeleteFlagFalse(productId);
        if(promotionProductEntityOptional.isPresent()) {
            PromotionEntity promotionEntity = promotionRepository.findByIdAndStatusTrue(promotionProductEntityOptional.get().getId()).get();
            promotionResponseDTO = promotionMapper.toPromotionResponseDTO(promotionEntity);
           try {
               ProductEntity product= productRepository.findById(productId).get();
               List<ProductResponseDTO> products = Arrays.asList(productMapper.toProductResponseDTO(product));
               promotionResponseDTO.setProducts(products);
           }catch(Exception e){
               log.error("Sản phẩm không tồn tại");
           }
        }
        return promotionResponseDTO ;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PromotionResponseDTO update(String id, PromotionRequestDTO promotionRequestDTO){
        if(!promotionRepository.findById(id).isPresent()){
            throw new TechCamExp(ConstantsErrorCode.PROMOTION_NOT_FOUND);
        }
        PromotionEntity promotionEntity = promotionRepository.findById(id).get();
        BeanUtils.copyProperties(promotionRequestDTO, promotionEntity);
        promotionRepository.saveAndFlush(promotionEntity);

        return promotionMapper.toPromotionResponseDTO(promotionEntity);
    }

    @Override
    public void delete(String id){
        if(!promotionRepository.findById(id).isPresent()){
            throw new TechCamExp(ConstantsErrorCode.PROMOTION_NOT_FOUND);
        }
        PromotionEntity promotionEntity = promotionRepository.findById(id).get();
        promotionEntity.setDeleteFlag(true);
        List<PromotionProductEntity> promotionProducts = promotionProductRepository.findAllByPromotionId(id);
       if(promotionProducts.size() > 0){
           promotionProducts.forEach(x ->{
               x.setDeleteFlag(true);
           });
           promotionProductRepository.saveAll(promotionProducts);
       }
    }
}
