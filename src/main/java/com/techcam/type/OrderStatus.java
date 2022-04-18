package com.techcam.type;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/20/2022
 * Project_name: Tech-cam
 */

public enum OrderStatus {
    UNPAID,     // Chưa thanh toán
    PAID,       // Đã thanh toán

    VERIFY,     // Chờ xác nhận
    CONFIRM,    // Chờ xuất hàng
    SHIPPING,   // Chờ giao hàng
    GOSHIPPING, // đang đi giao hàng
    DONE,       // Hoàn thành
    CANCEL
}
