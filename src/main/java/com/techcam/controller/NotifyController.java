package com.techcam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/2/2022 7:17 PM
 */

@Controller
public class NotifyController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @GetMapping("/test-notify")
    public String testNotify(){
        messagingTemplate.convertAndSend("/topic/notify", "hello bro");
        return "";
    }
}
