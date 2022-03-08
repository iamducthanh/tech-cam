package com.techcam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 8:55 PM
 * Project_name : tech-cam
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherRespDto {

    private String id;

    private String voucherCode;

    private String voucherName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long discount;

    private Integer minAmount;

    private Integer quantity;

    private String description;

    private String categoryId;

    private Boolean status;

    private Boolean hidden;

    private LocalDateTime createDate;

    private String createBy;

    private LocalDateTime modifiedDate;

}
