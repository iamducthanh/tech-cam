package com.techcam.controller;

import com.techcam.entity.LogEntity;
import com.techcam.entity.StatisProfit;
import com.techcam.entity.TopProductSaleByMonth;
import com.techcam.repo.IProductRepo;
import com.techcam.repo.IStatisProfitRepo;
import com.techcam.repo.ITopProductSaleByMonth;
import com.techcam.service.ILogService;
import com.techcam.service.IOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@Controller
public class IndexController {

    private final String LOCALHOST_IPV4 = "127.0.0.1";
    private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    @Autowired
    private ILogService logService;
    @Autowired
    private IStatisProfitRepo statisProfitRepo;

    @GetMapping("/")
    public String index(Model model) {
        List<LogEntity> list = logService.findAllLog̣();
        model.addAttribute("logs",list);

        List<StatisProfit> statisProfits = statisProfitRepo.findProfit(2022);
        statisProfits.forEach(o -> {
            System.out.println(o.toString());
        });
        return "views/index";
    }

//    @GetMapping("/index")
//    public String index1(HttpServletRequest request) {
//        System.out.println(getDevice(request));
//        System.out.println(orderService.findAllOrdersDetailsById("39f49e48-3d80-4065-ab06-79a26c22a3a3").size());
//        return "test_vnpay";
//    }

    public String getDevice(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!StringUtils.isEmpty(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }
        return ipAddress;
    }

}
