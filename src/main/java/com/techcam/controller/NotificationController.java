package com.techcam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/9/2022 9:38 PM
 */

@Controller
public class NotificationController {
    @GetMapping("/notification")
    public String notificationPage() {
        return "views/notification/notification";
    }

    @GetMapping("/view-comment")
    public String viewComment(@RequestParam("productId") String productId){
        return "";
    }
}
