package com.techcam.api;

import com.techcam.dto.request.promotion.PromotionRequestDTO;
import com.techcam.dto.response.PromotionResponseDTO;
import com.techcam.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/promotion")
public class PromotionApi {
    @Autowired
    IPromotionService promotionService;

    @PostMapping("/create")
    public ResponseEntity<PromotionResponseDTO> create(@Valid @RequestBody PromotionRequestDTO promotionRequestDTO){

        PromotionResponseDTO promotionResponseDTO = promotionService.create(promotionRequestDTO);
        return ResponseEntity.ok(promotionResponseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PromotionResponseDTO> update(@PathVariable("id") String id,@Valid @RequestBody PromotionRequestDTO promotionRequestDTO){

        PromotionResponseDTO promotionResponseDTO = promotionService.update(id, promotionRequestDTO);
        return ResponseEntity.ok(promotionResponseDTO);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<PromotionResponseDTO> findById(@PathVariable("id") String id){
        PromotionResponseDTO promotionResponseDTO = promotionService.findById(id);
        return ResponseEntity.ok(promotionResponseDTO);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){

        promotionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-sale-product")
    public Double getSaleProduct(@PathVariable("productId") String productId){

        return  promotionService.getPromotionProduct(productId);
    }

    @GetMapping("/active/{id}")
    public ResponseEntity<Void> activePromotion(@PathVariable("id") String id){

        promotionService.activePromotion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity<Void> inActivePromotion(@PathVariable("id") String id){
        promotionService.inActivePromotion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
