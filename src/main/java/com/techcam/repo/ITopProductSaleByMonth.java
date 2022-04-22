package com.techcam.repo;

import com.techcam.entity.TopProductSaleByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITopProductSaleByMonth extends JpaRepository<TopProductSaleByMonth, String> {
    @Query(name = "findTopProductSaleByMonth")
    List<TopProductSaleByMonth> findTopProductSaleByMonth(Integer top, Integer month, Integer year);
}
