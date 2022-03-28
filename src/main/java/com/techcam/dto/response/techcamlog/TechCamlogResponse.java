package com.techcam.dto.response.techcamlog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/28/2022
 * Project_name: Tech-cam
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechCamlogResponse {
    private String id;
    private String operationLink;
    private String operationKey;
    private String operationAct;
    private String operationDesc;
    private String staffId;
    private Date createDate;
    private String createBy;
}
