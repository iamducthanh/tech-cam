package com.techcam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcam.dto.StaffDto;
import com.techcam.entity.StaffEntity;
import com.techcam.util.EncodeUtil;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/9/2022 9:38 PM
 */

@Controller
@RequiredArgsConstructor
public class NotificationController {
    private final SessionUtil sessionUtil;
    private final EncodeUtil encodeUtil;

    private final String url = "http://localhost:8888";

    @GetMapping("/notification")
    public String notificationPage() {
        return "views/notification/notification";
    }

    @GetMapping("/view-comment")
    @ResponseBody
    public String viewComment(@RequestParam("productId") String productId){
        StaffEntity staffEntity = (StaffEntity) sessionUtil.getObject("user");
        StaffDto staffDto = StaffDto.builder()
                .id(staffEntity.getId())
                .fullname(staffEntity.getFullName())
                .username(staffEntity.getUsername())
                .build();
        try {
            String json = new ObjectMapper().writeValueAsString(staffDto);
            String token = encodeUtil.encrypt(json);
            String detail = "/product-details?productId=" + productId;
            String urlRe = url + "/auth?token=" + token + "&redirectUrl=" + detail;
            return urlRe;
        } catch (Exception e) {
            return "";
        }
    }
}
