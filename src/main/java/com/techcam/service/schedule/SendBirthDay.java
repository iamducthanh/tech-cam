package com.techcam.service.schedule;

import com.techcam.dto.request.MailDto;
import com.techcam.dto.request.voucher.VoucherRequest;
import com.techcam.entity.CustomerEntity;
import com.techcam.repo.ICustomerRepo;
import com.techcam.service.IVoucherService;
import com.techcam.type.CommonStatus;
import com.techcam.type.CustomerStatus;
import com.techcam.util.MailerUtil;
import com.techcam.util.MessageUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/25/2022
 * Project_name: Tech-cam
 */
@Component
public class SendBirthDay {
    @Autowired
    private ICustomerRepo customerRepo;
    @Autowired
    private IVoucherService voucherService;
    @Autowired
    private MailerUtil mailerUtil;
    @Value("${techcam.schedule.birthday.message}")
    private String message;
    @Value("${techcam.schedule.birthday.subject}")
    private String subject;
    @Value("${techcam.schedule.birthday.frommail}")
    private String fromMail;

    @Scheduled(cron = "${techcam.schedule.birthday.cron}")
    public void sendMailBirthDay() {
        Date date = new Date();
        List<CustomerEntity> customerEntities = customerRepo.findAllBySendBirthDay(CustomerStatus.ON.name(),
               Integer.parseInt(new SimpleDateFormat("MM").format(date)), Integer.parseInt(new SimpleDateFormat("dd").format(date)));
        System.out.println(customerEntities.size());
        if (customerEntities.isEmpty()) {
            return;
        }
        customerEntities.forEach(customerEntity -> {
            if (Objects.nonNull(customerEntity.getEmail())) {
                try {
//                    VoucherRequest voucherRequest = sendVoucher(customerEntity.getId());
//                    String response = voucherService.createVoucher(voucherRequest);
//                    VoucherRequest voucherRequest  = voucherService.findAllByCode("")
//                    if (CommonStatus.SUCCESS.name().equals(response)) {
                        sendMail(String.format(message, "SNTECHCAM","2222-04-27"), customerEntity.getEmail(), subject, fromMail);
                        Thread.sleep(5000);
//                    }
                } catch (InterruptedException | MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private VoucherRequest sendVoucher(String customerId) {
        Date date = new Date();
        Date endDate = DateUtils.addDays(date,15);
        VoucherRequest voucherRequest = new VoucherRequest();
//        voucherRequest.setVoucherId(UUID.randomUUID().toString());
        voucherRequest.setVoucherCode(renderCode());
        voucherRequest.setVoucherDescription(MessageUtil.VOUCHER_DESCRIPTION_SEND_BIRTHDAY);
        voucherRequest.setVoucherCategory(null);
        voucherRequest.setVoucherName("Quà Sinh Nhật");
        voucherRequest.setVoucherDiscount("200000");
        voucherRequest.setVoucherStatus("ON");
        voucherRequest.setVoucherEndDate(new SimpleDateFormat("dd-MM-yyyy").format(endDate));
        voucherRequest.setVoucherStartDate(new SimpleDateFormat("dd-MM-yyyy").format(date));
        voucherRequest.setTypeDiscountMoneyMin(null);
        voucherRequest.setVoucherMoneyMin("100");
        voucherRequest.setVoucherTypeDiscount("đ");
        voucherRequest.setVoucherQuantity("1");
        voucherRequest.setVoucherPersonApply(customerId);
        return voucherRequest;

    }
    public String renderCode(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
    private void sendMail(String body, String toMail, String subject, String fromMail) throws MessagingException {
        MailDto mailDto = new MailDto();
        mailDto.setBody(body);
        mailDto.setTo(toMail);
        mailDto.setSubject(subject);
        mailDto.setFrom(fromMail);
        mailerUtil.send(mailDto);
    }
}
