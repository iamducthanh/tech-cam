package com.techcam.dto.request.techcamlog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
public class TechCamlogRequest {
    private String operationLink; // 'link thao tac',
    private String operationKey;//'ID ban ghi duoc thao tac',
    private String operationAct; //'them / sua/ xoa',
    private String operationDesc; // Mô tả thao tác
    private String staffId; // staff id;
    private String createBy; // người tạo thao tác tên nhân viên viết tắt
}
