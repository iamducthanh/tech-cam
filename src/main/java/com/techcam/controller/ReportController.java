package com.techcam.controller;

import com.techcam.dto.Report.DailySaleDto;
import com.techcam.dto.Report.StaffSaleDto;
import com.techcam.dto.Report.TopProductSaleByMonthDto;
import com.techcam.dto.Report.TopProductSaleDto;
import com.techcam.entity.StaffEntity;
import com.techcam.service.IAuthService;
import com.techcam.service.IReportService;
import com.techcam.util.ConvertDateUtil;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2022/04/24 17:10
 * Project_name : tech-cam
 */

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final IReportService reportService;

    private final IAuthService authService;

    @GetMapping("/daily-sales")
    public String dailySalesReport(Model model, @RequestParam(value = "date", required = false) String date) {
        if (Objects.isNull(date)) {
            date = ConvertDateUtil.convertDateTime(new Date(), "dd/MM/yyyy");
            return "redirect:/daily-sales?date=" + date;
        }
        if (ConvertUtil.get().strToDate(date, "dd/MM/yyyy").compareTo(new Date()) > 0) {
            date = ConvertDateUtil.convertDateTime(new Date(), "dd/MM/yyyy");
            return "redirect:/daily-sales?date=" + date;
        }
        if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
            List<DailySaleDto> lstDailySaleDto = reportService.getDailySaleReport(date);
            model.addAttribute("lstDailySale", lstDailySaleDto);
        }
        LocalDate now = LocalDate.now();
        StaffEntity staffEntity = authService.getAuth();
        model.addAttribute("staff", staffEntity);
        model.addAttribute("date", date);
        model.addAttribute("day", now.getDayOfMonth());
        model.addAttribute("month", now.getMonthValue() + 1);
        model.addAttribute("year", now.getYear());
        return "views/report/daily-report";
    }

    @GetMapping("/staff-sales")
    public String staffSalesReport(Model model,
                                   @RequestParam(value = "startDate", required = false) String startDate,
                                   @RequestParam(value = "endDate", required = false) String endDate) {
        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            if (startDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")
                    && endDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                List<StaffSaleDto> lstStaffSaleDto = reportService.getStaffSaleReport(startDate, endDate);
                model.addAttribute("lstStaffSaleDto", lstStaffSaleDto);
            }
        }
        return "views/report/staff-sale";
    }

    @GetMapping("/top-product-sales")
    public String topProductSales(Model model,
                                  @RequestParam(value = "startDate", required = false) String startDate,
                                  @RequestParam(value = "endDate", required = false) String endDate,
                                  @RequestParam(value = "numberSize", required = false) Integer numberSize) {
        if (Objects.nonNull(startDate) && Objects.nonNull(endDate) && Objects.nonNull(numberSize)) {
            if (startDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")
                    && endDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                List<TopProductSaleDto> lstTopProductSaleDto = reportService.getTopProductSale(startDate, endDate, numberSize);
                model.addAttribute("lstTopProductSaleDto", lstTopProductSaleDto);
            }
        }
        return "views/report/top-product-sale";
    }

    @GetMapping("/top-product-sales-by-month")
    public String topProductSaleByMonth(Model model,
                                        @RequestParam(value = "month", required = false) Integer month,
                                        @RequestParam(value = "year", required = false) Integer year,
                                        @RequestParam(value = "numberSize", required = false) Integer numberSize) {
        LocalDate now = LocalDate.now();
        if (Objects.isNull(month)) {
            month = now.getMonthValue() + 1;
        }
        if (Objects.isNull(year)) {
            year = now.getYear();
        }
        if (Objects.isNull(numberSize)) {
            numberSize = 10;
        }
        List<TopProductSaleByMonthDto> lstTopProductSaleByMonthDto = reportService.getTopProductSaleByMonth(numberSize, month, year);
        model.addAttribute("lstTopProductSaleByMonthDto", lstTopProductSaleByMonthDto);
        return "views/report/top-product-sale-by-month";
    }

}
