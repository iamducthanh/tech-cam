package com.techcam.api.impl;

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

    @PutMapping("/update")
    public ResponseEntity<PromotionResponseDTO> update(@Valid @RequestBody PromotionRequestDTO promotionRequestDTO){

        PromotionResponseDTO promotionResponseDTO = promotionService.create(promotionRequestDTO);
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

}
