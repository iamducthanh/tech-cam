package com.techcam.util;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/28/2022
 * Project_name: Tech-cam
 */

public interface DescLog {
    // log của customer
    String LOG_INSERT_CUSTOMER = "%s vừa thêm mới khách hàng %s"; // tên viết createby, mã giao dịch

    //log order
    String LOG_ORDER= "%s vừa % hóa đơn %s";
    String INSERT_ORDER="tạo";
    String EDIT_ORDER_VERIFY= "sửa";
    String CANCEL_ORDER="hủy";
    String CONFIRM_ORDER ="xác nhận";
    String CONFIRM_EXPORT_ORDER="xuất hàng cho";
    String PAY_ORDER="hoàn thành thanh toán cho";
}
