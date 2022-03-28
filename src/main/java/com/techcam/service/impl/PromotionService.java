package com.techcam.service.impl;

import com.techcam.repo.IPromotionRepo;
import com.techcam.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService implements IPromotionService {

    @Autowired
    IPromotionRepo promotionRepository;

}
