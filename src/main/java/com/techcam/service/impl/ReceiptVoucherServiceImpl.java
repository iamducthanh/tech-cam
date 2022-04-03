package com.techcam.service.impl;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.MailDto;
import com.techcam.dto.request.receiptvoucher.ReceiptVoucherRequest;
import com.techcam.dto.response.receiptvoucher.ReceiptVoucherResponse;
import com.techcam.entity.ReceiptVoucherEntity;
import com.techcam.entity.StaffEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.repo.IReceiptVoucherRepo;
import com.techcam.repo.IStaffRepo;
import com.techcam.service.IReceiptVoucherService;
import com.techcam.type.CommonStatus;
import com.techcam.type.ReceiptVoucherStatus;
import com.techcam.util.MailerUtil;
import com.techcam.util.MessageUtil;
import com.techcam.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/24/2022
 * Project_name: Tech-cam
 */
@Service
public class ReceiptVoucherServiceImpl implements IReceiptVoucherService {
    @Autowired
    private IReceiptVoucherRepo receiptVoucherRepo;
    @Autowired
    private MailerUtil mailerUtil;
    @Autowired
    private IStaffRepo staffRepo;
    @Autowired
    private SessionUtil sessionUtil;
    private final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Override
    public ReceiptVoucherResponse resgistration(ReceiptVoucherRequest request) {
        ReceiptVoucherResponse response = new ReceiptVoucherResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        ReceiptVoucherEntity receiptVoucher = MODEL_MAPPER.map(request, ReceiptVoucherEntity.class);
        receiptVoucher.setId(UUID.randomUUID().toString());
        receiptVoucher.setCreateDate(new Date());
        receiptVoucher.setModifierDate(new Date());
        receiptVoucher.setDeleteFlag(false);
//        receiptVoucher.setCreateBy(getInfoStaff());
        receiptVoucher.setCreateBy("oke");
        receiptVoucher.setStatus(ReceiptVoucherStatus.PAID.name());
        try {
            receiptVoucherRepo.save(receiptVoucher);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(CommonStatus.FAIL.name());
        }
        return response;

    }

    public ReceiptVoucherResponse editReceiptById(ReceiptVoucherRequest request) {
        ReceiptVoucherResponse response = new ReceiptVoucherResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        if(Objects.isNull(request) || StringUtils.isBlank(request.getId())){
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        ReceiptVoucherEntity receiptVoucherEntity = receiptVoucherRepo.findFirstByIdAndDeleteFlagFalse(request.getId());
        if(Objects.isNull(receiptVoucherEntity)){
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        ReceiptVoucherEntity receiptVoucher = MODEL_MAPPER.map(request, ReceiptVoucherEntity.class);
        receiptVoucher.setModifierDate(new Date());
        receiptVoucher.setModifierBy(getInfoStaff());
        try {
            receiptVoucherRepo.save(receiptVoucher);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(CommonStatus.FAIL.name());
        }
        return response;
    }
    public ReceiptVoucherResponse deleteReceiptVoucher(String id, String note){
        ReceiptVoucherResponse response = new ReceiptVoucherResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        if( StringUtils.isBlank(id)){
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        ReceiptVoucherEntity receiptVoucherEntity = receiptVoucherRepo.findFirstByIdAndDeleteFlagFalse(id);
        if(Objects.isNull(receiptVoucherEntity)){
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        receiptVoucherEntity.setModifierBy(getInfoStaff());
        receiptVoucherEntity.setDeleteFlag(true);
        receiptVoucherEntity.setNote(note);
        try {
            receiptVoucherRepo.save(receiptVoucherEntity);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(CommonStatus.FAIL.name());
        }
        return  response;
    }
    @Override
    public String getInfoStaff() {
        StaffEntity staffEntity = (StaffEntity) sessionUtil.getObject("STAFF");
        if (Objects.isNull(staffEntity)) {
            throw new TechCamExp(ConstantsErrorCode.INTERNAL_SERVER_ERROR);
        }
        return staffEntity.getUsername();
    }
    @Async
    public void sendMail(String id, String note){
        List<String>  role = Arrays.asList("ADMIN");
        // todo thiếu lấy danh sách mail boss
        List<StaffEntity> mailBoss =staffRepo.findAllByRoleInAndDeleteFlagFalse(role);
        MailDto mailDto = new MailDto();
        mailDto.setFrom(MessageUtil.FROM_MAIL);
        mailDto.setSubject("Cảnh báo nhân viên thực hiện thao tác xóa");
        mailDto.setBody(String.format(MessageUtil.MAIL_DELETE_RECEIPT_VOUCHER,getInfoStaff(),id,note));
        mailBoss.forEach(s -> {
            mailDto.setTo(s.getEmail());
            try {
                mailerUtil.send(mailDto);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });

    }
}
