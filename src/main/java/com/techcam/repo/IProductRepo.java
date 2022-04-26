package com.techcam.repo;

import com.techcam.dto.Report.DailySaleDto;
import com.techcam.dto.Report.StaffSaleDto;
import com.techcam.dto.Report.TopProductSaleByMonthDto;
import com.techcam.dto.Report.TopProductSaleDto;
import com.techcam.entity.ProductEntity;
import com.techcam.entity.TopProductSaleByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:37 AM
 * Project_name : tech-cam
 */

@Repository
public interface IProductRepo extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findAllByDeleteFlagIsFalse();

    List<ProductEntity> findAllByIdInAndDeleteFlagFalse(List<String> productIds);

    List<ProductEntity> findALlByProductCodeAndDeleteFlagIsFalse(String productCode);

    ProductEntity getByIdAndDeleteFlagIsFalse(String id);

    List<ProductEntity> findAllByCategoryIdAndDeleteFlagFalse(String categoryId);

    @Query("SELECT p FROM ProductEntity p WHERE p.status = 'ON' AND p.name LIKE %:keyword% OR p.productCode LIKE %:keyword%")
    List<ProductEntity> findAllByKeyWords(@Param("keyword") String keyword);

    //    @Query(value = "call PR_TOP_PRODUCT_SALE_BY_MONTH(?1, ?2, ?3)", nativeQuery = true)
//    List<TopProductSaleByMonth> getTopProductSaleByMonth(Integer top, Integer month, Integer year);

    List<ProductEntity> findAllByCategoryIdInAndDeleteFlagFalse(List<String> categoryIds);

    @Query(name = "getDailySaleReport", nativeQuery = true)
    List<DailySaleDto> getDailySaleReport(String date);

    @Query(name = "getStaffSaleReport", nativeQuery = true)
    List<StaffSaleDto> getStaffSaleReport(String startDate, String endDate);

    @Query(name = "getTopProductSale", nativeQuery = true)
    List<TopProductSaleDto> getTopProductSale(String startDate, String endDate, Integer numberSize);

    @Query(name = "getTopProductSaleByMonth", nativeQuery = true)
    List<TopProductSaleByMonthDto> getTopProductSaleByMonth(Integer numberSize, Integer month, Integer year);

}
