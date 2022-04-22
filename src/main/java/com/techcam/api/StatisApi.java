package com.techcam.api;

import com.techcam.entity.TopProductSaleByMonth;
import com.techcam.repo.ITopProductSaleByMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatisApi {
    private final ITopProductSaleByMonth topProductSaleByMonth;
    @GetMapping("/top-product-sale")
    public List<TopProductSaleByMonth> getTopProductSaleByMonth(
            @RequestParam("top") String top,
            @RequestParam("month") String month,
            @RequestParam("year") String year
    ){
        LocalDate now = LocalDate.now();
        System.out.println(now.getMonthValue());
        List<TopProductSaleByMonth> topProductSaleByMonthList = topProductSaleByMonth.findTopProductSaleByMonth(Integer.valueOf(top),Integer.valueOf(month),Integer.valueOf(year));
        return topProductSaleByMonthList;

    }
}
