package com.techcam.service.impl;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.receiptvoucher.ReceiptVoucherRequest;
import com.techcam.dto.response.receiptvoucher.ReceiptVoucherResponse;
import com.techcam.entity.ReceiptVoucherEntity;
import com.techcam.entity.StaffEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.repo.IReceiptVoucherRepo;
import com.techcam.service.IReceiptVoucherService;
import com.techcam.type.CommonStatus;
import com.techcam.type.ReceiptVoucherStatus;
import com.techcam.util.SessionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Objects;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/24/2022
 * Project_name: Tech-cam
 */

public class ReceiptVoucherServiceImpl implements IReceiptVoucherService {
    @Autowired
    private IReceiptVoucherRepo receiptVoucherRepo;
    @Autowired
    private SessionUtil sessionUtil;
    private final ModelMapper MODEL_MAPPER = new ModelMapper();
    public ReceiptVoucherResponse resgistration(ReceiptVoucherRequest request){
        ReceiptVoucherResponse response = new ReceiptVoucherResponse().builder().status(CommonStatus.SUCCESS.name()).build();
        ReceiptVoucherEntity receiptVoucher = MODEL_MAPPER.map(request,ReceiptVoucherEntity.class);
        receiptVoucher.setCreateDate(new Date());
        receiptVoucher.setModifierDate(new Date());
        receiptVoucher.setDeleteFlag(false);
        receiptVoucher.setCreateBy(getInfoStaff());
        receiptVoucher.setStatus(ReceiptVoucherStatus.PAID.name());
        try {
            receiptVoucherRepo.save(receiptVoucher);
        }catch (Exception e){
          response.setStatus(CommonStatus.FAIL.name());
        }
        return null;

    }
//    public ReceiptVoucherResponse editReceiptById(){
//
//    }
    private String getInfoStaff(){
        StaffEntity staffEntity = (StaffEntity) sessionUtil.getObject("STAFF");
        if(Objects.isNull(staffEntity)){
            throw new TechCamExp(ConstantsErrorCode.INTERNAL_SERVER_ERROR);
        }
        return staffEntity.getFullName();
    }
}
