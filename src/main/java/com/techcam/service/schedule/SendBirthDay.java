package com.techcam.service.schedule;

import com.techcam.dto.request.MailDto;
import com.techcam.dto.request.voucher.VoucherRequest;
import com.techcam.entity.CustomerEntity;
import com.techcam.repo.ICustomerRepo;
import com.techcam.service.IVoucherService;
import com.techcam.type.CommonStatus;
import com.techcam.type.CustomerStatus;
import com.techcam.util.MailerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
                new SimpleDateFormat("MM").format(date), new SimpleDateFormat("dd").format(date));
        System.out.println(customerEntities.size());
        if (customerEntities.isEmpty()) {
            return;
        }
        customerEntities.forEach(customerEntity -> {
            if (Objects.nonNull(customerEntity.getEmail())) {
                try {
                    VoucherRequest voucherRequest = sendVoucher();
                    String response = voucherService.createVoucher(voucherRequest);
                    if (CommonStatus.SUCCESS.name().equals(response)) {
                        sendMail(String.format(message, voucherRequest.getVoucherId(), voucherRequest.getVoucherEndDate()), customerEntity.getEmail(), subject, fromMail);
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException | MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private VoucherRequest sendVoucher() {
        VoucherRequest voucherRequest = new VoucherRequest();
        voucherRequest.setVoucherId(UUID.randomUUID().toString());
//        voucherRequest.setVoucherCode();
//        voucherRequest.setVoucherDescription(MessageUtil.VOUCHER_DESCRIPTION_SEND_BIRTHDAY);
//        voucherRequest.setVoucherCategory();
//        voucherRequest.setVoucherName();
//        voucherRequest.setVoucherDiscount();
//        voucherRequest.setTypeDiscountMoneyMin();
//        voucherRequest.setVoucherStatus(ConvertDateUtil);
//        voucherRequest.setVoucherEndDate();
//        voucherRequest.setTypeDiscountMoneyMin();
//        voucherRequest.setVoucherMoneyMin();
//        voucherRequest.setVoucherTypeDiscount();
//        voucherRequest.setVoucherQuantity();
//        voucherRequest.setVoucherPersonApply();
        return voucherRequest;

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
