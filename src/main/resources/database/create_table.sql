create database  if not exists `poly_techcam` /*!40100 default character set utf8mb4 collate utf8mb4_0900_ai_ci */ /*!80016 default encryption='n' */;
use `poly_techcam`;
-- mysql dump 10.13  distrib 8.0.27, for win64 (x86_64)
--
-- host: localhost    database: poly_techcam
-- ------------------------------------------------------
-- server version	8.0.27

/*!40101 set @old_character_set_client=@@character_set_client */;
/*!40101 set @old_character_set_results=@@character_set_results */;
/*!40101 set @old_collation_connection=@@collation_connection */;
/*!50503 set names utf8 */;
/*!40103 set @old_time_zone=@@time_zone */;
/*!40103 set time_zone='+00:00' */;
/*!40014 set @old_unique_checks=@@unique_checks, unique_checks=0 */;
/*!40014 set @old_foreign_key_checks=@@foreign_key_checks, foreign_key_checks=0 */;
/*!40101 set @old_sql_mode=@@sql_mode, sql_mode='no_auto_value_on_zero' */;
/*!40111 set @old_sql_notes=@@sql_notes, sql_notes=0 */;

--
-- table structure for table `attribute`
--

drop table if exists `attribute`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `attribute` (
                             `id` varchar(64) not null,
                             `attribute_name` varchar(50) not null,
                             `category_id` varchar(64) not null,
                             `status` varchar(50) not null,
                             `note` text,
                             `create_date` timestamp not null,
                             `modifier_date` timestamp not null,
                             `create_by` varchar(64) not null,
                             `modifier_by` varchar(64) not null,
                             `delete_flag` bit(1) not null default b'0',
                             primary key (`id`),
                             key `fk_category_attribute` (`category_id`),
                             constraint `fk_category_attribute` foreign key (`category_id`) references `category` (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `brand`
--

drop table if exists `brand`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `brand` (
                         `id` varchar(64) not null,
                         `name` varchar(200) not null,
                         `phone` varchar(20) not null,
                         `email` varchar(50) not null,
                         `address` varchar(200) not null,
                         `status` varchar(50) not null,
                         `note` text,
                         `create_date` timestamp not null,
                         `modifier_date` timestamp not null,
                         `create_by` varchar(64) not null,
                         `modifier_by` varchar(64) not null,
                         `delete_flag` bit(1) not null default b'0',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `category`
--

drop table if exists `category`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `category` (
                            `id` varchar(64) not null,
                            `name` varchar(50) not null,
                            `parent_id` varchar(64) default null,
                            `status` varchar(50) not null,
                            `note` text,
                            `create_date` timestamp not null,
                            `modifier_date` timestamp not null,
                            `create_by` varchar(64) not null,
                            `modifier_by` varchar(64) not null,
                            `delete_flag` bit(1) not null default b'0',
                            primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `customer`
--

drop table if exists `customer`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `customer` (
                            `id` varchar(64) not null,
                            `full_name` varchar(100) not null,
                            `phone_number` varchar(20) not null,
                            `email` varchar(50) not null,
                            `address` varchar(200) not null,
                            `identity_number` varchar(20) not null,
                            `avatar` varchar(255) not null,
                            `role` varchar(20) not null,
                            `date_of_birth` date not null,
                            `count_login_false` int not null,
                            `status` varchar(50) not null,
                            `note` text,
                            `create_date` timestamp not null,
                            `modifier_date` timestamp not null,
                            `create_by` varchar(64) not null,
                            `modifier_by` varchar(64) not null,
                            `delete_flag` bit(1) not null default b'0',
                            primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `images`
--

drop table if exists `images`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `images` (
                          `id` varchar(64) not null,
                          `image_link` varchar(4000) not null,
                          `object_id` varchar(64) not null,
                          `object_type` varchar(50) not null,
                          `start_dt` date not null,
                          `end_dt` date not null,
                          `status` varchar(50) not null,
                          `note` text,
                          `create_date` timestamp not null,
                          `modifier_date` timestamp not null,
                          `create_by` varchar(64) not null,
                          `modifier_by` varchar(64) not null,
                          `delete_flag` bit(1) not null default b'0',
                          primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `product`
--

drop table if exists `product`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `product` (
                           `id` varchar(64) not null,
                           `name` varchar(100) not null,
                           `product_code` varchar(100) not null,
                           `category_id` varchar(64) not null,
                           `detail` text,
                           `status` bit(1) not null,
                           `note` text,
                           `create_date` timestamp not null,
                           `modifier_date` timestamp not null,
                           `create_by` varchar(64) not null,
                           `modifier_by` varchar(64) not null,
                           `delete_flag` bit(1) not null default b'0',
                           primary key (`id`),
                           key `fk_1` (`category_id`),
                           constraint `fk_1` foreign key (`category_id`) references `category` (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `product_detail`
--

drop table if exists `product_detail`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `product_detail` (
                                  `id` varchar(64) not null,
                                  `quantity` int not null default '0',
                                  `price` bigint not null default '0',
                                  `product_id` varchar(64) not null,
                                  `status` varchar(50) not null,
                                  `note` text,
                                  `create_date` timestamp not null,
                                  `modifier_date` timestamp not null,
                                  `create_by` varchar(64) not null,
                                  `modifier_by` varchar(64) not null,
                                  `delete_flag` bit(1) not null default b'0',
                                  primary key (`id`),
                                  key `fk_product` (`product_id`),
                                  constraint `fk_product` foreign key (`product_id`) references `product` (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `product_property`
--

drop table if exists `product_property`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `product_property` (
                                    `id` varchar(64) not null,
                                    `attribute_id` varchar(64) not null,
                                    `product_detail_id` varchar(64) not null,
                                    `status` varchar(50) not null,
                                    `note` text,
                                    `create_date` timestamp not null,
                                    `modifier_date` timestamp not null,
                                    `create_by` varchar(64) not null,
                                    `modifier_by` varchar(64) not null,
                                    `delete_flag` bit(1) not null default b'0',
                                    `property_value` varchar(50) not null,
                                    primary key (`id`),
                                    key `fk_attribute_property` (`attribute_id`),
                                    key `fk_detail_property` (`product_detail_id`),
                                    constraint `fk_attribute_property` foreign key (`attribute_id`) references `attribute` (`id`),
                                    constraint `fk_detail_property` foreign key (`product_detail_id`) references `product_detail` (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `staff`
--

drop table if exists `staff`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `staff` (
                         `id` varchar(64) not null,
                         `full_name` varchar(200) not null,
                         `phone_number` varchar(20) not null,
                         `email` varchar(50) not null,
                         `address` varchar(200) not null,
                         `date_of_birth` date not null,
                         `staff_code` int not null,
                         `identity_number` varchar(20) not null,
                         `avatar` varchar(255) not null,
                         `role` varchar(20) not null,
                         `username` varchar(20) not null,
                         `password` varchar(20) not null,
                         `count_login_false` int not null,
                         `status` varchar(50) not null,
                         `note` text,
                         `create_date` timestamp not null,
                         `modifier_date` timestamp not null,
                         `create_by` varchar(64) not null,
                         `modifier_by` varchar(64) not null,
                         `delete_flag` bit(1) not null default b'0',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `supplier`
--

drop table if exists `supplier`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `supplier` (
                            `id` varchar(64) not null,
                            `name` varchar(200) not null,
                            `phone_number` varchar(20) not null,
                            `email` varchar(50) not null,
                            `address` varchar(200) not null,
                            `status` bit(1) not null,
                            `note` text,
                            `create_date` timestamp not null,
                            `modifier_date` timestamp not null,
                            `create_by` varchar(64) not null,
                            `modifier_by` varchar(64) not null,
                            `delete_flag` bit(1) not null default b'0',
                            primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `voucher`
--

drop table if exists `voucher`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `voucher` (
                           `id` varchar(64) not null,
                           `name` varchar(200) not null,
                           `code` varchar(10) not null,
                           `quantity` int not null,
                           `discount` bigint not null,
                           `start_date` date not null,
                           `end_date` date not null,
                           `description` text not null,
                           `min_amount` int not null,
                           `category_id` varchar(64),
                           `status` varchar(50) not null,
                           `note` text,
                           `create_date` timestamp not null,
                           `modifier_date` timestamp not null,
                           `create_by` varchar(64) not null,
                           `modifier_by` varchar(64) not null,
                           `delete_flag` bit(1) not null default b'0',
                           primary key (`id`),
                           key `fk_cat_voucher` (`category_id`),
                           constraint `fk_cat_voucher` foreign key (`category_id`) references `category` (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;

--
-- table structure for table `voucher_customer`
--

drop table if exists `voucher_customer`;
/*!40101 set @saved_cs_client     = @@character_set_client */;
/*!50503 set character_set_client = utf8mb4 */;
create table `voucher_customer` (
                                    `id` varchar(64) not null,
                                    `customer_id` varchar(64) not null,
                                    `voucher_id` varchar(10) not null,
                                    `discount` bigint not null,
                                    `start_dt` date not null,
                                    `end_dt` date not null,
                                    `status` varchar(50) not null,
                                    `note` text,
                                    `create_date` timestamp not null,
                                    `modifier_date` timestamp not null,
                                    `create_by` varchar(64) not null,
                                    `modifier_by` varchar(64) not null,
                                    `delete_flag` bit(1) not null default b'0',
                                    primary key (`id`),
                                    key `fk_cus_voucher` (`customer_id`),
                                    key `fk_cus_voucher_2` (`voucher_id`),
                                    constraint `fk_cus_voucher` foreign key (`customer_id`) references `customer` (`id`),
                                    constraint `fk_cus_voucher_2` foreign key (`voucher_id`) references `voucher` (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
/*!40101 set character_set_client = @saved_cs_client */;
/*!40103 set time_zone=@old_time_zone */;

/*!40101 set sql_mode=@old_sql_mode */;
/*!40014 set foreign_key_checks=@old_foreign_key_checks */;
/*!40014 set unique_checks=@old_unique_checks */;
/*!40101 set character_set_client=@old_character_set_client */;
/*!40101 set character_set_results=@old_character_set_results */;
/*!40101 set collation_connection=@old_collation_connection */;
/*!40111 set sql_notes=@old_sql_notes */;

-- dump completed on 2022-03-01 22:01:35
