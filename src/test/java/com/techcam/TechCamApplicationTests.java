package com.techcam;

import com.techcam.repo.IProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TechCamApplicationTests {
    @Autowired
    IProductRepo productRepo;

    @Test
    void contextLoads() {
        System.out.println(productRepo.getTopProductSaleByMonth(5, 4, 2022));
    }

}
