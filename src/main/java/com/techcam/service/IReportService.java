package com.techcam.service;

import com.techcam.dto.Report.DailySaleDto;
import com.techcam.dto.Report.StaffSaleDto;
import com.techcam.dto.Report.TopProductSaleByMonthDto;
import com.techcam.dto.Report.TopProductSaleDto;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2022/04/24 17:25
 * Project_name : tech-cam
 */

public interface IReportService {

    List<DailySaleDto> getDailySaleReport(String date);

    List<StaffSaleDto> getStaffSaleReport(String startDate, String endDate);

    List<TopProductSaleDto> getTopProductSale(String startDate, String endDate, Integer numberSize);

    List<TopProductSaleByMonthDto> getTopProductSaleByMonth(Integer numberSize, Integer month, Integer year);
}
