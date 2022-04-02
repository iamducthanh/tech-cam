package com.techcam.constants;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 2/27/2022
 * Project_name: Tech-cam
 */

public interface ConstantsErrorCode {

    String ERROR_LENGTH = "CM-TC-001";
    String ERROR_MIN_MONEY = "CM-TC-003";
    String ERROR_EXISTS = "CM-TC-004";
    String ERROR_NOT_EXISTS = "CM-TC-005";
    String ERROR_FORMAT_NUMBER = "CM-TC-007";

    String ERROR_SAVE_FAILED = "CM-TC-006";

    // lỗi hệ thống
    String ERROR_DATA_REQUEST = "TC-000-001";
    String INTERNAL_SERVER_ERROR = "TC-000-002";
    String EMAIL_EXIST = "TC-000-004";
    String PHONE_NUMBER_EXIST = "TC-000-003";
    String CATEGORY_NAME_EXIST = "TC-LG-011";
    String CATEGORY_NAME = "TC-LG-010";


    // lỗi customer
    String CUSTOMER_EXIST = "TC-004-002";
    String CUSTOMER_NOT_EXIST = "TC-004-003";

    String DATE_NOT_FORMAT = "TC-005-002";
    String VOUCHER_CODE_BLANK = "TC-005-001";
    String VOUCHER_NAME_BLANK = "TC-005-003";
    String VOUCHER_START_DATE_BLANK = "TC-005-004";
    String VOUCHER_END_DATE_BLANK = "TC-005-005";
    String VOUCHER_DISCOUNT_BLANK = "TC-005-006";
    String VOUCHER_NOT_EXISTS = "TC-005-007";
    String VOUCHER_END_USED = "TC-005-008";
    String VOUCHER_MAX_USED = "TC-005-009";
    String VOUCHER_DATE_NOT_PAST = "TC-005-010";

    String ERROR_BLANK = "CM-TC-002";

    String PRODUCT_CODE_ERROR_REGEX = "TC-006-001";

    String PRODUCT_MONEY_ERROR = "TC-006-002";

    String PRODUCT_NAME_ERROR = "TC-006-003";

    String PRODUCT_IMAGE_ERROR = "TC-006-004";

    String PRODUCT_IMAGE_MAX = "TC-006-005";

    String PRODUCT_NOT_EXISTS = "TC-006-006";

    String PRODUCT_CODE_DUPLICATE = "TC-006-007";


    //Lỗi supplier

    String SUPPLIER_EXIST = "TC-003-001";
    String SUPPLIER_NOT_EXIST = "TC-003-002";
    // lỗi login
    String LOGIN_DATA_FAIL = "TC-LG-001";
    String LOGIN_PASS_C_FAIL = "TC-LG-002";
    String LOGIN_PASS_M_FAIL = "TC-LG-003";
    String LOGIN_EMAIL_INPUT_NULL = "TC-LG-004";
    String LOGIN_EMAIL_NOT_EXITS = "TC-LG-005";
    String LOGIN_CHANGE_PASS_SUCCESS = "TC-LG-006";
    String LOGIN_PASS_FAIL = "TC-LG-007";
    String LOGIN_FAIL_FIVE = "TC-LG-008";
    String LOGIN_RESET_BY_EMAIL_SUCCESS = "TC-LG-009";
    String LOGIN_ACCOUNT_BLOCKED = "TC-LG-012";

    String ORDER_PRODUCT_OUT_OF_STOCK = "TC-007-001";
    String CUST_ORDER_TOO_MUCH = "TC-007-002";
    String VOUCHER_ERROR = "TC-007-003";
    String IMEI_DUPLICATE = "TC-007-004";

    String INVOICE_NOT_DETAIL = "TC-008-001";

}
