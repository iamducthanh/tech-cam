package com.techcam.api;

import com.techcam.entity.StatisProfit;
import com.techcam.entity.TopProductSaleByMonth;
import com.techcam.repo.IStatisProfitRepo;
import com.techcam.repo.ITopProductSaleByMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatisApi {
    private final ITopProductSaleByMonth topProductSaleByMonth;
    private final IStatisProfitRepo statisProfitRepo;
    @GetMapping("/top-product-sale")
    public ResponseEntity<List<TopProductSaleByMonth>> getTopProductSaleByMonth(
            @RequestParam("top") String top,
            @RequestParam("month") String month,
            @RequestParam("year") String year
    ){
        List<TopProductSaleByMonth> topProductSaleByMonthList = topProductSaleByMonth.findTopProductSaleByMonth(Integer.valueOf(top),Integer.valueOf(month),Integer.valueOf(year));
        return ResponseEntity.ok(topProductSaleByMonthList);

    }

    @GetMapping("/statis-profit")
    public ResponseEntity<List<StatisProfit>> getStatisProfit(@RequestParam("year") String year){
        List<StatisProfit> statisProfits = statisProfitRepo.findProfit(Integer.valueOf(year));
        return ResponseEntity.ok(statisProfits);
    }
}
