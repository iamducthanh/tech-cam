package com.techcam.repo;

import com.techcam.entity.StatisProfit;
import com.techcam.entity.TopProductSaleByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStatisProfitRepo extends JpaRepository<StatisProfit, String> {
    @Query(name = "findProfit")
    List<StatisProfit> findProfit(Integer year);
}
