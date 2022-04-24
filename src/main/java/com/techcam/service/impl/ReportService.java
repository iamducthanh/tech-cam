package com.techcam.service.impl;

import com.techcam.dto.Report.DailySaleDto;
import com.techcam.dto.Report.StaffSaleDto;
import com.techcam.dto.Report.TopProductSaleByMonthDto;
import com.techcam.dto.Report.TopProductSaleDto;
import com.techcam.repo.IProductRepo;
import com.techcam.service.IReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2022/04/24 17:25
 * Project_name : tech-cam
 */

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final IProductRepo reportRepo;

    @Override
    public List<DailySaleDto> getDailySaleReport(String date) {
        try {
            return reportRepo.getDailySaleReport(date);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StaffSaleDto> getStaffSaleReport(String startDate, String endDate) {
        return reportRepo.getStaffSaleReport(startDate, endDate);
    }

    @Override
    public List<TopProductSaleDto> getTopProductSale(String startDate, String endDate, Integer numberSize) {
        return reportRepo.getTopProductSale(startDate, endDate, numberSize);
    }

    @Override
    public List<TopProductSaleByMonthDto> getTopProductSaleByMonth(Integer numberSize, Integer month, Integer year) {
        return reportRepo.getTopProductSaleByMonth(numberSize, month, year);
    }

}
