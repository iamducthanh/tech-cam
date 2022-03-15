DROP DATABASE POLY_TECHCAM;
CREATE DATABASE POLY_TECHCAM;
USE  POLY_TECHCAM;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS CATEGORY;
CREATE TABLE CATEGORY(
ID VARCHAR(64) NOT NULL PRIMARY KEY, 
NAME VARCHAR(50) NOT NULL, 
PARENT_ID		VARCHAR(64),
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
CONSTRAINT FK_CAT_PARENT FOREIGN KEY (PARENT_ID) REFERENCES CATEGORY(ID)
) ;

DROP TABLE IF EXISTS PRODUCT;
CREATE TABLE PRODUCT(
ID VARCHAR(64) NOT NULL PRIMARY KEY, 
NAME VARCHAR (100) NOT NULL,
CATEGORY_ID VARCHAR(64) NOT NULL ,
BRAND_ID VARCHAR(64) NOT NULL ,
PRODUCT_CODE VARCHAR(64) NOT NULL ,
QUANTITY INT NOT NULL DEFAULT 0,
PRICE BIGINT NOT NULL DEFAULT 0,
DETAIL  TEXT,
DESCRIPTION TEXT,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0)  ;

ALTER TABLE PRODUCT ADD CONSTRAINT FK_1 FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (ID);
ALTER TABLE PRODUCT ADD CONSTRAINT FK_2 FOREIGN KEY (BRAND_ID) REFERENCES BRAND (ID);
/*
DROP TABLE IF EXISTS PRODUCT_DETAIL;
CREATE TABLE PRODUCT_DETAIL(
ID VARCHAR(64) NOT NULL PRIMARY KEY, 
QUANTITY INT NOT NULL DEFAULT 0,
PRICE BIGINT NOT NULL DEFAULT 0,
PRODUCT_ID VARCHAR(64) NOT NULL,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
CONSTRAINT FK_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID));
*/

DROP TABLE IF EXISTS ATTRIBUTE;
CREATE TABLE ATTRIBUTE(
ID VARCHAR(64) NOT NULL PRIMARY KEY, 
ATTRIBUTE_NAME VARCHAR(50) NOT NULL,
CATEGORY_ID VARCHAR(64) NOT NULL,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
CONSTRAINT FK_CATEGORY_ATTRIBUTE FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID));

DROP TABLE IF EXISTS PRODUCT_PROPERTY;
CREATE TABLE PRODUCT_PROPERTY(
ID VARCHAR(64) NOT NULL PRIMARY KEY, 
ATTRIBUTE_ID VARCHAR(64) NULL,
PRODUCT_ID VARCHAR(64) NOT NULL,
ATTRIBUTE_VALUE  VARCHAR(50)  NULL,
ATTRIBUTE_FIXED_ID VARCHAR(64)  NULL,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
CONSTRAINT FK_ATTRIBUTE_PROPERTY FOREIGN KEY (ATTRIBUTE_ID) REFERENCES ATTRIBUTE(ID),
CONSTRAINT FK_DETAIL_PROPERTY FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID),
CONSTRAINT FK_FIXED_PROPERTY FOREIGN KEY (ATTRIBUTE_FIXED_ID) REFERENCES ATTRIBUTE_FIXED_VALUE(ID)
);

-- ALTER TABLE PRODUCT_PROPERTY ADD PROPERTY_VALUE VARCHAR(50) NOT NULL;

DROP TABLE IF EXISTS ATTRIBUTE_FIXED_VALUE;
CREATE TABLE ATTRIBUTE_FIXED_VALUE(
ID VARCHAR(64) NOT NULL PRIMARY KEY, 
ATTRIBUTE_ID VARCHAR(64) NOT NULL,
ATTRIBUTE_FIXED_VAL VARCHAR(50) NOT NULL,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
CONSTRAINT FK_ATTRIBUTE_FIX_PROPERTY FOREIGN KEY (ATTRIBUTE_ID) REFERENCES ATTRIBUTE(ID)
);


DROP TABLE IF EXISTS IMAGES;
CREATE TABLE IMAGES(
ID VARCHAR(64) NOT NULL PRIMARY KEY, 
IMAGES_NAME VARCHAR(255) NOT NULL,
IMAGES_LINK VARCHAR(255) NOT NULL,
PRODUCT_ID  VARCHAR(50) NOT NULL, 
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
CONSTRAINT FK_IMG_DETAIL_PROPERTY FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
);

DROP TABLE IF EXISTS STAFF;
CREATE TABLE STAFF(
ID	VARCHAR(64) NOT NULL PRIMARY KEY,
FULL_NAME VARCHAR(200) NOT NULL,
PHONE_NUMBER	VARCHAR(20) NOT NULL,
EMAIL	VARCHAR(50) NOT NULL,
ADDRESS	VARCHAR(200) NOT NULL,
DATE_OF_BIRTH	DATE NOT NULL,
STAFF_CODE VARCHAR(20) NOT NULL,
IDENTITY_NUMBER	VARCHAR(20) NOT NULL,
AVATAR	VARCHAR(255) NOT NULL,
ROLE	VARCHAR(20) NOT NULL,
USERNAME VARCHAR(20) NOT NULL,
PASSWORD VARCHAR(20) NOT NULL,
COUNT_LOGIN_FALSE	INT NOT NULL,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0);

DROP TABLE IF EXISTS SUPPLIER;
CREATE TABLE SUPPLIER(
ID	VARCHAR(64) NOT NULL PRIMARY KEY,
NAME	VARCHAR(200) NOT NULL,
PHONE_NUMBER	VARCHAR(20) NOT NULL,
EMAIL	VARCHAR(50) NOT NULL,
ADDRESS	VARCHAR(200) NOT NULL,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0);

DROP TABLE IF EXISTS BRAND;
CREATE TABLE BRAND(
ID	VARCHAR(64) NOT NULL PRIMARY KEY,
NAME	VARCHAR(200) NOT NULL,
PHONE	VARCHAR(20) NOT NULL,
EMAIL	VARCHAR(50) NOT NULL,
ADDRESS	VARCHAR(200) NOT NULL,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0);

/*
DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE CUSTOMER(
ID VARCHAR(64) NOT NULL PRIMARY KEY,
FULL_NAME	VARCHAR(100) NOT NULL,
PHONE_NUMBER	VARCHAR(20) NOT NULL,
EMAIL	VARCHAR(50) NOT NULL,
ADDRESS	VARCHAR(200) NOT NULL,
IDENTITY_NUMBER	VARCHAR(20) NULL,
AVATAR	VARCHAR(255) NOT NULL,
ROLE	VARCHAR(20) NOT NULL,
DATE_OF_BIRTH	DATE NOT NULL,
COUNT_LOGIN_FALSE INT NOT NULL,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0);*/

-- 13/3 set null cac gia tri
DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE CUSTOMER(
ID VARCHAR(64) NOT NULL PRIMARY KEY,
FULL_NAME	VARCHAR(100) NOT NULL,
PHONE_NUMBER	VARCHAR(20) NOT NULL,
EMAIL	VARCHAR(50) NULL,
ADDRESS	VARCHAR(200) NOT NULL,
IDENTITY_NUMBER	VARCHAR(20) NULL,
AVATAR	VARCHAR(255) NULL,
ROLE	VARCHAR(20) NOT NULL,
DATE_OF_BIRTH	DATE NULL,
COUNT_LOGIN_FALSE INT NOT NULL,
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0);

DROP TABLE IF EXISTS VOUCHER;
CREATE TABLE VOUCHER(
ID VARCHAR(64) NOT NULL PRIMARY KEY, 
NAME VARCHAR(200) NOT NULL, 
CODE VARCHAR(10) NOT NULL, 
QUANTITY INT NOT NULL, 
DISCOUNT BIGINT NOT NULL, 
START_DATE DATE NOT NULL, 
END_DATE DATE NOT NULL, 
DESCRIPTION TEXT NOT NULL, 
MIN_AMOUNT INT NOT NULL, 
CATEGORY_ID VARCHAR(64) NOT NULL, 
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
CONSTRAINT FK_CAT_VOUCHER FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID));

DROP TABLE IF EXISTS VOUCHER_CUSTOMER;
CREATE TABLE VOUCHER_CUSTOMER(
ID VARCHAR(64) NOT NULL PRIMARY KEY, 
CUSTOMER_ID VARCHAR(64) NOT NULL, 
VOUCHER_ID VARCHAR(10) NOT NULL, 
DISCOUNT BIGINT NOT NULL, 
START_DT DATE NOT NULL, 
END_DT DATE NOT NULL, 
STATUS        VARCHAR(50) NOT NULL, 
NOTE          TEXT, 
CREATE_DATE   TIMESTAMP NOT NULL, 
MODIFIER_DATE   TIMESTAMP NOT NULL, 
CREATE_BY   VARCHAR(64) NOT NULL, 
MODIFIER_BY   VARCHAR(64) NOT NULL,
DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
CONSTRAINT FK_CUS_VOUCHER FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID),
CONSTRAINT FK_CUS_VOUCHER_2 FOREIGN KEY (VOUCHER_ID) REFERENCES VOUCHER(ID));

-- danh sach don khach hang yeu cau
DROP TABLE IF EXISTS Wishlist;
CREATE TABLE Wishlist (
  ID VARCHAR(64) NOT NULL, 
  Customer_ID VARCHAR(64) NOT NULL,
  OrderDate DATE NOT NULL,
  Tax DECIMAL NOT NULL,
  PaymentDate DATE NOT NULL,
  ItemQuantity INT NULL,
  TotalPrice DECIMAL NOT NULL,
  WishlistStatus VARCHAR(50) NOT NULL,
  STATUS        VARCHAR(50) NOT NULL, 
  NOTE          TEXT, 
  CREATE_DATE   TIMESTAMP NOT NULL, 
  MODIFIER_DATE   TIMESTAMP NOT NULL, 
  CREATE_BY   VARCHAR(64) NOT NULL, 
  MODIFIER_BY   VARCHAR(64) NOT NULL,
  DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (ID),
  CONSTRAINT FK_Wish_cust FOREIGN KEY (Customer_ID) REFERENCES CUSTOMER(ID)
  );
  
DROP TABLE IF EXISTS WishlistDetail;
CREATE TABLE WishlistDetail (
  Wishlist_ID VARCHAR(64) NOT NULL,
  Product_ID VARCHAR(64) NOT NULL,
  STATUS        VARCHAR(50) NOT NULL, 
  NOTE          TEXT, 
  CREATE_DATE   TIMESTAMP NOT NULL, 
  MODIFIER_DATE   TIMESTAMP NOT NULL, 
  CREATE_BY   VARCHAR(64) NOT NULL, 
  MODIFIER_BY   VARCHAR(64) NOT NULL,
  DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,  
  PRIMARY KEY (Wishlist_ID,Product_ID),
  CONSTRAINT FK_WishDetail_product FOREIGN KEY (Product_ID) REFERENCES Product(ID),
  CONSTRAINT FK_WishDetail_ORDER FOREIGN KEY (Wishlist_ID) REFERENCES Wishlist(ID)
  );

-- danh sach don dc xac nhan
DROP TABLE IF EXISTS Orders;
CREATE TABLE Orders (
  ID VARCHAR(64) NOT NULL, 
  Customer_ID VARCHAR(64) NOT NULL,
  OrderDate DATE NOT NULL,
  Tax DECIMAL NOT NULL,
  TransactionStatus VARCHAR(50) NOT NULL,
  PaymentDate DATE NOT NULL,
  ItemQuantity INT NOT NULL,
  TotalAmount DECIMAL NOT NULL,
  VOUCHER_CUST_ID VARCHAR(64) NOT NULL, 
  STATUS        VARCHAR(50) NOT NULL, 
  NOTE          TEXT, 
  CREATE_DATE   TIMESTAMP NOT NULL, 
  MODIFIER_DATE   TIMESTAMP NOT NULL, 
  CREATE_BY   VARCHAR(64) NOT NULL, 
  MODIFIER_BY   VARCHAR(64) NOT NULL,
  DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (ID),
  CONSTRAINT FK_Order_cust FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID),
  CONSTRAINT FK_Order_voucher FOREIGN KEY (VOUCHER_CUST_ID) REFERENCES VOUCHER_CUSTOMER(ID)
  );
  
DROP TABLE IF EXISTS OrderDetail;
CREATE TABLE OrderDetail (
  Order_ID VARCHAR(64) NOT NULL,
  Product_ID VARCHAR(64) NOT NULL,
  Imei VARCHAR(64) NOT NULL,
  STATUS        VARCHAR(50) NOT NULL, 
  NOTE          TEXT, 
  CREATE_DATE   TIMESTAMP NOT NULL, 
  MODIFIER_DATE   TIMESTAMP NOT NULL, 
  CREATE_BY   VARCHAR(64) NOT NULL, 
  MODIFIER_BY   VARCHAR(64) NOT NULL,
  DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,  
  PRIMARY KEY (Order_ID,Product_ID, Imei),
  CONSTRAINT FK_OrderDetail_product FOREIGN KEY (Product_ID) REFERENCES Product(ID),
  CONSTRAINT FK_OrderDetail_ORDER FOREIGN KEY (Order_ID) REFERENCES Orders(ID)
  );

-- Nhap kho
DROP TABLE IF EXISTS GoodsReceipt;
CREATE TABLE GoodsReceipt (
  ID VARCHAR(64) NOT NULL,
  Product_ID VARCHAR(64) NOT NULL,
  TotalQuantity INT NOT NULL,
  TotalAmount   DECIMAL NOT NULL,
  Supplier_ID	VARCHAR(64) NOT NULL,
  ReceiptStatus	VARCHAR(50) NOT NULL,  #trang thai hoa don nhap hang ( khoi tao, trinh duyet, da duyet  ....)
  STATUS        VARCHAR(50) NOT NULL, 
  NOTE          TEXT, 
  CREATE_DATE   TIMESTAMP NOT NULL, 
  MODIFIER_DATE   TIMESTAMP NOT NULL, 
  CREATE_BY   VARCHAR(64) NOT NULL, 
  MODIFIER_BY   VARCHAR(64) NOT NULL,
  DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,  
  PRIMARY KEY (ID),
  CONSTRAINT FK_GoodsReceipt_product FOREIGN KEY (Product_ID) REFERENCES Product(ID),
  CONSTRAINT FK_GoodsReceipt_Sup FOREIGN KEY (Supplier_ID) REFERENCES Supplier(ID)
  );
 
 DROP TABLE IF EXISTS GoodsReceiptDetail;
  CREATE TABLE GoodsReceiptDetail (
  GoodsReceipt_ID VARCHAR(64) NOT NULL,
  Product_ID VARCHAR(64) NOT NULL,
  Quantity	Int not null,
  Price Decimal not null,
  STATUS        VARCHAR(50) NOT NULL, 
  NOTE          TEXT, 
  CREATE_DATE   TIMESTAMP NOT NULL, 
  MODIFIER_DATE   TIMESTAMP NOT NULL, 
  CREATE_BY   VARCHAR(64) NOT NULL, 
  MODIFIER_BY   VARCHAR(64) NOT NULL,
  DELETE_FLAG BIT(1) NOT NULL DEFAULT 0,  
  PRIMARY KEY (GoodsReceipt_ID,Product_ID),
  CONSTRAINT FK_Detail_Receipt FOREIGN KEY (GoodsReceipt_ID) REFERENCES GoodsReceipt(ID),
  CONSTRAINT FK_Detail_Product FOREIGN KEY (Product_ID) REFERENCES Product(ID)
  );
  


SET FOREIGN_KEY_CHECKS = 1;