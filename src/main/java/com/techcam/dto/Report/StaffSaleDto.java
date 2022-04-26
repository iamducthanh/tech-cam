package com.techcam.dto.Report;

import lombok.Data;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2022/04/24 17:17
 * Project_name : tech-cam
 */

@Data
public class StaffSaleDto {

    public StaffSaleDto(String salePerson, String fullName, Long sum) {
        this.salePerson = salePerson;
        this.fullName = fullName;
        this.sum = sum;
    }

    private String salePerson;

    private String fullName;

    private Long sum;

}
