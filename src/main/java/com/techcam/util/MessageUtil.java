package com.techcam.util;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/23/2022
 * Project_name: Tech-cam
 */

public interface MessageUtil {
    String NOTE_EDIT_ORDER_DETAILS= " ( Có IMEI Là : %s )";
    String SAVE_ORDER_CUSTOMER_DONE= "Khách hàng thanh toán hóa đơn : %s";
    String RECEIPT_NAME_ORDER="Thanh toán hóa đơn mua hàng";
    // mail mua hàng
    String FROM_MAIL="techcam.poly@gmail.com";
    String SUBJECT_MAIL_ORDER="Thông Tin Mua Hàng";
    String MAIL_CUSTOMER_ORDER_DONE="Cảm Ơn Quý Khách đã mua sắm tại TechCam.<br><br> " +
            "Vui lòng đánh giá dịch vụ tại hotline : 0978825572 hoặc gửi mail về TechCam@gmail.com.<br><br>" +
            " Xin cảm ơn và chúc quý khách nhiều sức khỏe !";
    String MAIL_CONFIRM_ORDER="Tech Cam xác nhận đơn hàng %s của bạn.<br><br> Sản phẩm sẽ được giao đến bạn vào %s.<br><br> Mọi thắc mắc xin liên hệ : 0978825572";
    String MAIL_ORDER_REGISTRATION_ONLINE="Chúc Mừng quý khách đã đặt hàng thành công tại TechCam.<br><br> Mã đơn hàng là : %s. <br><br> Chúng tôi sẽ liên hệ sớm nhất đến quý khách <br><br> Hoặc liên hệ với chúng tôi qua Hotline : 0978825572";
    String VOUCHER_DESCRIPTION_SEND_BIRTHDAY= "Voucher gửi tặng sinh nhật khách hàng";

    // mai xóa hóa đơn gửi cho oogn quản lí.
    String MAIL_DELETE_RECEIPT_VOUCHER="%s vừa xóa phiếu chi %s với lí do : %s";
}
