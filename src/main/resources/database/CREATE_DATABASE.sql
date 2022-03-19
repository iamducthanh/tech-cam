CREATE TABLE `attribute`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ATTRIBUTE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CATEGORY_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_CATEGORY_ATTRIBUTE`(`CATEGORY_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `attribute_fixed_value`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ATTRIBUTE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ATTRIBUTE_FIXED_VAL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_ATTRIBUTE_FIX_PROPERTY`(`ATTRIBUTE_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `brand`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ADDRESS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `category`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PARENT_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_CAT_PARENT`(`PARENT_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `customer`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FULL_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PHONE_NUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DATE_OF_BIRTH` date NULL DEFAULT NULL,
  `ADDRESS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `goodsreceipt`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Product_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TotalQuantity` int NOT NULL,
  `TotalAmount` decimal(10, 0) NOT NULL,
  `Supplier_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ReceiptStatus` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_GoodsReceipt_product`(`Product_ID` ASC) USING BTREE,
  INDEX `FK_GoodsReceipt_Sup`(`Supplier_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `goodsreceiptdetail`  (
  `GoodsReceipt_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Product_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Quantity` int NOT NULL,
  `Price` decimal(10, 0) NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`GoodsReceipt_ID`, `Product_ID`) USING BTREE,
  INDEX `FK_Detail_Product`(`Product_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `images`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IMAGES_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IMAGES_LINK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PRODUCT_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_IMG_DETAIL_PROPERTY`(`PRODUCT_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `orderdetail`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Order_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Product_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IMEI` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `Status` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MODIFIER_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DELETE_FLAG` bit(1) NULL DEFAULT NULL,
  `promotion_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'khuyen mai',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_ORDER_ID`(`Order_ID` ASC) USING BTREE,
  INDEX `FK_PRODUCT_ID`(`Product_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `orders`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Customer_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `OrderDate` date NOT NULL,
  `Tax` decimal(10, 0) NOT NULL,
  `TransactionStatus` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PaymentDate` date NOT NULL,
  `ItemQuantity` int NULL DEFAULT NULL COMMENT 'tong so san pham',
  `TotalAmount` decimal(10, 0) NOT NULL,
  `VOUCHER_CUST_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'loai thanh toan (online / truc tiep)',
  `Stockkeeper` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'nguoi xuat kho',
  `recipient_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ten nguoi nhan',
  `recipient_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sdt nguoi nhan',
  `payment_method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'loai thanh toan: tien mat/ chuyen khoan/ vnpay ... ',
  `recipient_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'dia chi nguoi nhan',
  `shipment_status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'trang thai giao hang',
  `salesperson` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'nhan vien ban hang, xac nhan don hang',
  `accounting` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ke toan ban hang, xac nhan don hang thanh cong',
  `shipment_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'thong tin giao hang',
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_Order_cust`(`Customer_ID` ASC) USING BTREE,
  INDEX `FK_Order_voucher`(`VOUCHER_CUST_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `payment_voucher`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `recipient` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'nguoi nhan tien',
  `recipient_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sdt nguoi nhan tien',
  `payment_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ten phieu chi',
  `payment_value` decimal(10, 0) NULL DEFAULT NULL COMMENT 'so tien chi',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'muc dich chi / mo ta',
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `product`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CATEGORY_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BRAND_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PRODUCT_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `QUANTITY` int NOT NULL DEFAULT 0,
  `PRICE` bigint NOT NULL DEFAULT 0,
  `DETAIL` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `DESCRIPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_1`(`CATEGORY_ID` ASC) USING BTREE,
  INDEX `FK_2`(`BRAND_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `product_property`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ATTRIBUTE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PRODUCT_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ATTRIBUTE_VALUE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ATTRIBUTE_FIXED_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_ATTRIBUTE_PROPERTY`(`ATTRIBUTE_ID` ASC) USING BTREE,
  INDEX `FK_DETAIL_PROPERTY`(`PRODUCT_ID` ASC) USING BTREE,
  INDEX `FK_FIXED_PROPERTY`(`ATTRIBUTE_FIXED_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `promotion`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Product_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ten chuong trinh khuyen mai',
  `promo_value` decimal(10, 0) NULL DEFAULT 0 COMMENT 'gia tri khuyen mai',
  `promo_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'loai khuyen mai: tru gia hoac tru %',
  `begin_Dt` timestamp NULL DEFAULT NULL COMMENT 'ngay bat dau khuyen mai',
  `end_Dt` timestamp NULL DEFAULT NULL COMMENT 'ngay ket thuc khuyen mai',
  `desciption` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'mo ta khuyen mai',
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_promo_product`(`Product_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `receipt_voucher`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Order_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ma order co the null neu thu muc dich khac',
  `payer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'nguoi nop tien',
  `payer_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sdt nguoi nop tien',
  `receipt_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ten phieu thu',
  `receipt_value` decimal(10, 0) NULL DEFAULT NULL COMMENT 'so tien thu',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'muc dich thu / mo ta',
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `shipment`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shipper_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ten nguoi ship',
  `shipper_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sdt nguoi ship',
  `ship_fee` decimal(10, 0) NULL DEFAULT 0 COMMENT 'phi ship',
  `shipment_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'trang thai don hang',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'mo ta',
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_order_ship`(`order_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `staff`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FULL_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PHONE_NUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ADDRESS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DATE_OF_BIRTH` date NOT NULL,
  `STAFF_CODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IDENTITY_NUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AVATAR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ROLE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count_etoken` int NULL DEFAULT 0 COMMENT 'dem so lan nhap etoken sai',
  `etoken` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `COUNT_LOGIN_FALSE` int NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `supplier`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PHONE_NUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ADDRESS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `voucher`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CODE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `QUANTITY` int NOT NULL,
  `DISCOUNT` bigint NOT NULL,
  `START_DATE` date NOT NULL,
  `END_DATE` date NOT NULL,
  `DESCRIPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MIN_AMOUNT` int NOT NULL,
  `CATEGORY_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_CAT_VOUCHER`(`CATEGORY_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `voucher_customer`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CUSTOMER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VOUCHER_ID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DISCOUNT` bigint NOT NULL,
  `START_DT` date NOT NULL,
  `END_DT` date NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_CUS_VOUCHER`(`CUSTOMER_ID` ASC) USING BTREE,
  INDEX `FK_CUS_VOUCHER_2`(`VOUCHER_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `wishlist`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Customer_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `OrderDate` date NOT NULL,
  `Tax` decimal(10, 0) NOT NULL,
  `PaymentDate` date NOT NULL,
  `ItemQuantity` int NULL DEFAULT NULL,
  `TotalPrice` decimal(10, 0) NOT NULL,
  `WishlistStatus` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_Wish_cust`(`Customer_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `wishlistdetail`  (
  `Wishlist_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Product_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_DATE` timestamp NOT NULL,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MODIFIER_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`Wishlist_ID`, `Product_ID`) USING BTREE,
  INDEX `FK_WishDetail_product`(`Product_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

ALTER TABLE `attribute` ADD CONSTRAINT `FK_CATEGORY_ATTRIBUTE` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `attribute_fixed_value` ADD CONSTRAINT `FK_ATTRIBUTE_FIX_PROPERTY` FOREIGN KEY (`ATTRIBUTE_ID`) REFERENCES `attribute` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `category` ADD CONSTRAINT `FK_CAT_PARENT` FOREIGN KEY (`PARENT_ID`) REFERENCES `category` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `goodsreceipt` ADD CONSTRAINT `FK_GoodsReceipt_product` FOREIGN KEY (`Product_ID`) REFERENCES `product` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `goodsreceipt` ADD CONSTRAINT `FK_GoodsReceipt_Sup` FOREIGN KEY (`Supplier_ID`) REFERENCES `supplier` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `goodsreceiptdetail` ADD CONSTRAINT `FK_Detail_Product` FOREIGN KEY (`Product_ID`) REFERENCES `product` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `goodsreceiptdetail` ADD CONSTRAINT `FK_Detail_Receipt` FOREIGN KEY (`GoodsReceipt_ID`) REFERENCES `goodsreceipt` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `images` ADD CONSTRAINT `FK_IMG_DETAIL_PROPERTY` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `orderdetail` ADD CONSTRAINT `FK_ORDER_ID` FOREIGN KEY (`Order_ID`) REFERENCES `orders` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `orderdetail` ADD CONSTRAINT `FK_PRODUCT_ID` FOREIGN KEY (`Product_ID`) REFERENCES `product` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `orders` ADD CONSTRAINT `FK_Order_cust` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `orders` ADD CONSTRAINT `FK_Order_voucher` FOREIGN KEY (`VOUCHER_CUST_ID`) REFERENCES `voucher_customer` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `product` ADD CONSTRAINT `FK_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `product` ADD CONSTRAINT `FK_2` FOREIGN KEY (`BRAND_ID`) REFERENCES `brand` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `product_property` ADD CONSTRAINT `FK_ATTRIBUTE_PROPERTY` FOREIGN KEY (`ATTRIBUTE_ID`) REFERENCES `attribute` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `product_property` ADD CONSTRAINT `FK_DETAIL_PROPERTY` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `product_property` ADD CONSTRAINT `FK_FIXED_PROPERTY` FOREIGN KEY (`ATTRIBUTE_FIXED_ID`) REFERENCES `attribute_fixed_value` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `promotion` ADD CONSTRAINT `FK_promo_product` FOREIGN KEY (`Product_ID`) REFERENCES `product` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `shipment` ADD CONSTRAINT `FK_order_ship` FOREIGN KEY (`order_id`) REFERENCES `orders` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `voucher` ADD CONSTRAINT `FK_CAT_VOUCHER` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `voucher_customer` ADD CONSTRAINT `FK_CUS_VOUCHER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `voucher_customer` ADD CONSTRAINT `FK_CUS_VOUCHER_2` FOREIGN KEY (`VOUCHER_ID`) REFERENCES `voucher` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `wishlist` ADD CONSTRAINT `FK_Wish_cust` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `wishlistdetail` ADD CONSTRAINT `FK_WishDetail_ORDER` FOREIGN KEY (`Wishlist_ID`) REFERENCES `wishlist` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `wishlistdetail` ADD CONSTRAINT `FK_WishDetail_product` FOREIGN KEY (`Product_ID`) REFERENCES `product` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

