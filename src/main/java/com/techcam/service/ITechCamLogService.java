package com.techcam.service;

import com.techcam.dto.request.techcamlog.TechCamlogRequest;
import com.techcam.dto.response.techcamlog.TechCamlogResponse;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/28/2022
 * Project_name: Tech-cam
 */

public interface ITechCamLogService {
    void saveLog(TechCamlogRequest request);

    List<TechCamlogResponse> getALlTechCamLog();
}
