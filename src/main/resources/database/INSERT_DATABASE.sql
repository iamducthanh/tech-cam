-- ----------------------------
-- Records of attribute
-- ----------------------------
INSERT INTO `attribute` VALUES ('1', 'Tần số', '1', '1', NULL, '2022-03-05 17:54:32', '2022-03-05 17:54:35', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `attribute` VALUES ('2', 'Dung lượng thẻ nhớ', '1', '1', NULL, '2022-03-05 17:57:42', '2022-03-05 17:57:45', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `attribute` VALUES ('3', 'Cổng kết nối', '1', '1', NULL, '2022-03-05 17:58:06', '2022-03-05 17:58:12', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `attribute` VALUES ('4', 'Độ phân giải', '1', '1', NULL, '2022-03-05 17:58:33', '2022-03-05 17:58:37', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `attribute` VALUES ('7d6c4d82-9e26-11ec-ab50-6f4d28466523', 'HONG NGOAI', '4d6c4d82-9e26-11ec-ab50-6f4d28466520', '1', 'NOTE2', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `attribute` VALUES ('7d6c4d82-9e26-11ec-ab50-6f4d28466527', 'DO PHAN GIAI', '4d6c4d82-9e26-11ec-ab50-6f4d28466520', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `attribute` VALUES ('7d6c4d82-9e26-11ec-ab50-6f4d28466529', 'MAU SAC', '4d6c4d82-9e26-11ec-ab50-6f4d28466520', '1', 'NOTE3', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');

-- ----------------------------
-- Records of attribute_fixed_value
-- ----------------------------
INSERT INTO `attribute_fixed_value` VALUES ('1', '3', 'Wifi', '1', NULL, '2022-03-06 20:16:26', '2022-03-06 20:16:28', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `attribute_fixed_value` VALUES ('9d6c4d82-9e26-11ec-ab50-6f4d28466548', '7d6c4d82-9e26-11ec-ab50-6f4d28466523', 'CO', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `attribute_fixed_value` VALUES ('9d6c4d82-9e26-11ec-ab50-6f4d28466549', '7d6c4d82-9e26-11ec-ab50-6f4d28466523', 'KHONG', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1', 'Ezviz', '0944484474', 'thanhnd@gmail.com', 'Hà Nội', '1', '1', '2022-03-05 21:16:47', '2022-03-05 21:16:50', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `brand` VALUES ('6d6c4d82-9e26-11ec-ab50-6f4d28466520', 'BRAND1', '0911111111', 'BRAND1@GMAIL.COM', 'BRAND_ADDRESS_1', '1', 'NOTE1', '2022-01-01 00:11:01', '2022-01-01 00:11:01', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `brand` VALUES ('6d6c4d82-9e26-11ec-ab50-6f4d28466525', 'BRAND2', '0911111122', 'BRAND2@GMAIL.COM', 'BRAND_ADDRESS_2', '1', 'NOTE2', '2022-01-01 00:11:02', '2022-01-01 00:11:02', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Camera', NULL, '1', NULL, '2022-03-05 17:50:53', '2022-03-05 17:50:57', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `category` VALUES ('4d6c4d82-9e26-11ec-ab50-6f4d28466520', 'CAMERA', NULL, '1', NULL, '2022-01-01 00:00:01', '2022-01-01 00:00:01', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `category` VALUES ('4d6c4d82-9e26-11ec-ab50-6f4d28466523', 'THE NHO', NULL, '1', NULL, '2022-01-01 00:11:01', '2022-01-01 00:11:01', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1sdafadsfdasf', 'A NH NH', '0978825572', 'Duy@gmail.com', '2022-03-09', 'Hà Nội', 'OFF', '2022-06-14 21:28:39', 'Hà NỘi');
INSERT INTO `customer` VALUES ('3664c30d-5362-4277-9de0-7847fa7be462', 'Hoang Hồng Quang', '0386093875', 'abc123@gmail.com', '2022-03-16', '23 CoNhue', 'OFF', '2022-03-15 18:30:05', NULL);
INSERT INTO `customer` VALUES ('498f5a49-990e-4078-ab19-551c10b384da', 'Duy Đỗ Văn', '0384748037', 'duydv411.os@tpb.com.vn', '2022-03-14', '23 CoNhue', 'ON', '2022-03-14 20:16:41', NULL);
INSERT INTO `customer` VALUES ('83087770-11fc-4e5d-b81c-b5063717452b', 'hoàng hồng quang', '0386093875', 'quang041087@gmail.com', '2022-03-16', '1234114', 'OFF', '2022-03-16 22:29:15', NULL);
INSERT INTO `customer` VALUES ('91afe1bb-fd51-431e-baa9-bb750c2c3c79', 'hoàng hồng quang', '0386093870', 'abc1223@gmail.com', '2022-03-16', '1234114', 'ON', '2022-03-16 22:23:39', NULL);
INSERT INTO `customer` VALUES ('9550da15-c3b6-40eb-8308-1031ab3d5ee2', 'abc', '0384748088', 'abc@runsystem.net', '2022-03-14', '23 CoNhue', 'OFF', '2022-03-14 19:32:27', NULL);
INSERT INTO `customer` VALUES ('984c0b38-3b81-4d78-94a9-881681c29fb3', 'hoàng hồng quang', '0386093875', 'quang041087@gmail.com', '2022-03-16', '1234114', 'ON', '2022-03-16 22:30:27', NULL);
INSERT INTO `customer` VALUES ('a0ce9cc0-1fd5-4796-ad17-6349b48dfadb', 'hoàng hồng quang', '0386093888', 'quang0410587@gmail.com', '2022-03-16', '1234114', 'ON', '2022-03-16 22:31:54', NULL);
INSERT INTO `customer` VALUES ('ac7d283c-5bf0-4269-9fcc-9a4713690d71', 'ÀKF', '0384748069', 'duydv4111.os@tpb.com.vn', '2022-03-14', '23 CoNhue', 'OFF', '2022-03-14 20:14:01', NULL);
INSERT INTO `customer` VALUES ('c92b406e-0979-4c22-804b-d482eb328fbd', 'Duy Đỗ Văn', '0384748015', 'duydv4.os@tpb.com.vn', '2022-03-14', '23 CoNhue', 'ON', '2022-03-14 19:30:18', NULL);
INSERT INTO `customer` VALUES ('e96d7337-9b2a-4125-b6ce-c26ad5744a6a', 'Hoang Hồng Quang', '0978257256', 'quang@gmail.com', '2022-03-14', '23 CoNhue', 'OFF', '2022-03-14 16:36:15', NULL);

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES ('1', 'anh 1', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '1', '1', NULL, '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('10', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '8', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('11', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '9', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('12', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '10', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('13', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '11', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('14', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '12', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('15', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '13', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('16', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '14', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('17', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '15', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('18', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '16', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('19', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '17', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('2', 'anh 2', 'https://s.meta.com.vn/Data/image/2019/12/28/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-1.png', '1', '1', NULL, '2022-03-06 20:26:34', '2022-03-06 20:26:37', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('20', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '18', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('21', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '19', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('22', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '20', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('23', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '21', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('24', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '22', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('25', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '23', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('26', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '24', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('27', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '25', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('28', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '26', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('29', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '27', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('3', 'anh 3', 'https://s.meta.com.vn/Data/image/2019/12/28/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-2.png', '1', '1', NULL, '2022-03-06 23:15:36', '2022-03-06 23:15:40', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('30', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '28', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('31', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '29', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('32', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '30', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('4', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '2', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('5', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '3', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('6', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '4', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('7', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '5', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('8', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '6', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `images` VALUES ('9', 'anh', 'https://s.meta.com.vn/Data/image/2021/09/20/camera-ip-wifi-ezviz-cs-cv246-c6n-1080p-2mp-a0-1c2wfr-g.jpg', '7', 'status', '', '2022-03-06 20:26:06', '2022-03-06 20:26:09', 'thanhnd', 'thanhnd', b'0');

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'Camera IP wifi Ezviz CS-CV246 C6N 1080P 2MP (A0-1C2WFR)\r\n', '1', '1', '1', 100, 7799000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('10', 'Cam 10', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('11', 'Cam 11', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('12', 'Cam 12', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('13', 'Cam 13', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('14', 'Cam 14', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('15', 'Cam 15', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('16', 'Cam 16', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('17', 'Cam 17', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('18', 'Cam 18', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('19', 'Cam 19', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('2', 'Cam 2', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-06 23:18:48', '2022-03-06 23:18:53', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('20', 'Cam 20', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('21', 'Cam 21', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('22', 'Cam 22', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('23', 'Cam 23', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('24', 'Cam 24', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('25', 'Cam 25', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('26', 'Cam 26', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('27', 'Cam 27', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('28', 'Cam 28', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('29', 'Cam 29', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('3', 'Cam 3', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-06 23:18:48', '2022-03-06 23:18:53', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('30', 'Cam 30', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('4', 'Cam 4', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-06 23:18:48', '2022-03-06 23:18:53', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('5', 'Cam 5', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('5d6c4d82-9e26-11ec-ab50-6f4d28466525', 'CAMERA001', '4d6c4d82-9e26-11ec-ab50-6f4d28466520', '6d6c4d82-9e26-11ec-ab50-6f4d28466520', 'PRODUCTCODE1', 12, 1200000, 'DETAIL', 'DESC', '1', 'NOTE', '2022-01-01 00:00:01', '2022-01-01 00:00:01', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product` VALUES ('5d6c4d82-9e26-11ec-ab50-6f4d28466526', 'CAMERA002', '4d6c4d82-9e26-11ec-ab50-6f4d28466520', '6d6c4d82-9e26-11ec-ab50-6f4d28466520', 'PRODUCTCODE2', 25, 1500000, 'DETAIL', 'DESC', '1', 'NOTE', '2022-01-01 00:00:02', '2022-02-01 00:00:02', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product` VALUES ('5d6c4d82-9e26-11ec-ab50-6f4d28466527', 'CAMERA003', '4d6c4d82-9e26-11ec-ab50-6f4d28466520', '6d6c4d82-9e26-11ec-ab50-6f4d28466525', 'PRODUCTCODE3', 44, 900000, 'DETAIL', 'DESC', '1', 'NOTE', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product` VALUES ('6', 'Cam 6', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('7', 'Cam 7', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('8', 'Cam 8', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product` VALUES ('9', 'Cam 9', '1', '1', '1', 200, 600000, 'detail', 'Mô tả ngắn', '1', 'Ghi chú', '2022-03-05 21:18:24', '2022-03-05 21:18:27', 'thanhnd', 'thanhnd', b'0');

-- ----------------------------
-- Records of product_property
-- ----------------------------
INSERT INTO `product_property` VALUES ('1', '1', '1', '2.4 Ghz', '1', '1', '', '2022-03-05 21:20:08', '2022-03-06 20:17:10', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product_property` VALUES ('2', '2', '1', '128GB', '1', '1', '', '2022-03-05 21:20:11', '2022-03-06 20:17:13', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product_property` VALUES ('3', '3', '1', '', '1', '1', '', '2022-03-05 21:20:15', '2022-03-06 20:17:16', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product_property` VALUES ('4', '4', '1', '1920x1080p', '1', '1', '', '2022-03-05 21:20:17', '2022-03-06 20:17:17', 'thanhnd', 'thanhnd', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466520', '7d6c4d82-9e26-11ec-ab50-6f4d28466529', '5d6c4d82-9e26-11ec-ab50-6f4d28466525', 'TRANG', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466521', '7d6c4d82-9e26-11ec-ab50-6f4d28466529', '5d6c4d82-9e26-11ec-ab50-6f4d28466525', 'DEN', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466522', '7d6c4d82-9e26-11ec-ab50-6f4d28466529', '5d6c4d82-9e26-11ec-ab50-6f4d28466525', 'XAM', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466523', '7d6c4d82-9e26-11ec-ab50-6f4d28466529', '5d6c4d82-9e26-11ec-ab50-6f4d28466526', 'TRANG', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466524', '7d6c4d82-9e26-11ec-ab50-6f4d28466529', '5d6c4d82-9e26-11ec-ab50-6f4d28466526', 'DEN', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466525', '7d6c4d82-9e26-11ec-ab50-6f4d28466529', '5d6c4d82-9e26-11ec-ab50-6f4d28466526', 'XAM', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466526', '7d6c4d82-9e26-11ec-ab50-6f4d28466529', '5d6c4d82-9e26-11ec-ab50-6f4d28466527', 'TRANG', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466527', '7d6c4d82-9e26-11ec-ab50-6f4d28466529', '5d6c4d82-9e26-11ec-ab50-6f4d28466527', 'DEN', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466528', '7d6c4d82-9e26-11ec-ab50-6f4d28466529', '5d6c4d82-9e26-11ec-ab50-6f4d28466527', 'XAM', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466530', '7d6c4d82-9e26-11ec-ab50-6f4d28466527', '5d6c4d82-9e26-11ec-ab50-6f4d28466525', '720P', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466531', '7d6c4d82-9e26-11ec-ab50-6f4d28466527', '5d6c4d82-9e26-11ec-ab50-6f4d28466525', '1080P', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466532', '7d6c4d82-9e26-11ec-ab50-6f4d28466527', '5d6c4d82-9e26-11ec-ab50-6f4d28466525', '480P', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466533', '7d6c4d82-9e26-11ec-ab50-6f4d28466527', '5d6c4d82-9e26-11ec-ab50-6f4d28466526', '720P', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466534', '7d6c4d82-9e26-11ec-ab50-6f4d28466527', '5d6c4d82-9e26-11ec-ab50-6f4d28466526', '1080P', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466535', '7d6c4d82-9e26-11ec-ab50-6f4d28466527', '5d6c4d82-9e26-11ec-ab50-6f4d28466526', '480P', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466536', '7d6c4d82-9e26-11ec-ab50-6f4d28466527', '5d6c4d82-9e26-11ec-ab50-6f4d28466527', '720P', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466537', '7d6c4d82-9e26-11ec-ab50-6f4d28466527', '5d6c4d82-9e26-11ec-ab50-6f4d28466527', '1080P', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466538', '7d6c4d82-9e26-11ec-ab50-6f4d28466527', '5d6c4d82-9e26-11ec-ab50-6f4d28466527', '480P', NULL, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466541', NULL, '5d6c4d82-9e26-11ec-ab50-6f4d28466525', NULL, '9d6c4d82-9e26-11ec-ab50-6f4d28466548', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466542', NULL, '5d6c4d82-9e26-11ec-ab50-6f4d28466525', NULL, '9d6c4d82-9e26-11ec-ab50-6f4d28466549', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466544', NULL, '5d6c4d82-9e26-11ec-ab50-6f4d28466526', NULL, '9d6c4d82-9e26-11ec-ab50-6f4d28466548', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466545', NULL, '5d6c4d82-9e26-11ec-ab50-6f4d28466526', NULL, '9d6c4d82-9e26-11ec-ab50-6f4d28466549', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466547', NULL, '5d6c4d82-9e26-11ec-ab50-6f4d28466527', NULL, '9d6c4d82-9e26-11ec-ab50-6f4d28466548', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `product_property` VALUES ('8d6c4d82-9e26-11ec-ab50-6f4d28466548', NULL, '5d6c4d82-9e26-11ec-ab50-6f4d28466527', NULL, '9d6c4d82-9e26-11ec-ab50-6f4d28466549', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('1', 'Thành', '0123456789', 'thanhnd@gmail.com', 'HT', '2022-03-13', '1234', '0123456789', 'none', 'ROLE_ADMIN', 'thanhnd', '$2a$10$CnHJe8MD4ujjgXl.OvMD5ufV9o3UcaJg4xwQVsYQ4qlQb3c9oEjz2', 0, NULL, 0, '1', NULL, '2022-03-13 19:02:03', '2022-03-19 11:57:09', 'ADMIN', 'ADMIN', b'0');
INSERT INTO `staff` VALUES ('2', 'STAFF_NAME_1', '0969708715', 'STAFF1@GMAIL.COM', 'STAFF_ADDRESS1', '1998-12-12', '1', '0123412340', 'AVA1', 'ROLE_USER', 'STAFF001', '$2a$10$CnHJe8MD4ujjgXl.OvMD5ufV9o3UcaJg4xwQVsYQ4qlQb3c9oEjz2', 0, NULL, 1, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-03-16 18:44:15', 'ADMIN', 'ADMIN', b'0');
INSERT INTO `staff` VALUES ('3', 'STAFF_NAME_2', '0123123140', 'STAFF2@GMAIL.COM', 'STAFF_ADDRESS2', '1992-12-12', '2', '0123412311', 'AVA2', 'ROLE_USER', 'STAFF002', '$2a$10$CnHJe8MD4ujjgXl.OvMD5ufV9o3UcaJg4xwQVsYQ4qlQb3c9oEjz2', 0, NULL, 0, '1', 'NOTE1', '2022-01-01 00:00:04', '2022-03-19 11:57:23', 'ADMIN', 'ADMIN', b'0');
INSERT INTO `staff` VALUES ('4', 'quang', '0123123140', 'quang041087@gmail.com', '324', '1992-12-12', '14213', '435234523452', 'none', 'ROLE_ADMIN', 'quanghh', '$2a$10$CnHJe8MD4ujjgXl.OvMD5ufV9o3UcaJg4xwQVsYQ4qlQb3c9oEjz2', 0, NULL, 0, '1', NULL, '2022-01-01 00:00:04', '2022-03-17 11:03:00', 'ADMIN', 'ADMIN', b'0');

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('2c6c4d82-9e26-11ec-ab50-6f4d28466548', 'SUP2', '1234123332', 'SUP2@GMAIL.COM', 'SUP_ADR2', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');
INSERT INTO `supplier` VALUES ('2c6c4d82-9e26-11ec-ab50-6f4d28466549', 'SUP1', '1234123412', 'SUP1@GMAIL.COM', 'SUP_ADR1', '1', 'NOTE1', '2022-01-01 00:00:04', '2022-01-01 00:00:04', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', '1c6c4d82-9e26-11ec-ab50-6f4d28466548', b'0');