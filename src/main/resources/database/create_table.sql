CREATE TABLE staff
(
    id                varchar(64)  NOT NULL,
    full_name         varchar(100) NOT NULL,
    phone_number      varchar(20)  NOT NULL,
    email             varchar(50)  NOT NULL,
    address           varchar(255) NOT NULL,
    date_of_birth     date         NOT NULL,
    staff_code        int(7)       NOT NULL,
    avatar            varchar(255) NOT NULL,
    role              varchar(20)  NOT NULL,
    status            varchar(50)  NOT NULL,
    password          varchar(64)  NOT NULL,
    count_login_false int(1)       NOT NULL,
    note              text,
    create_date       timestamp    NOT NULL,
    modifier_by       varchar(64)  NOT NULL
);
CREATE TABLE customer
(
    id                varchar(64)  NOT NULL,
    full_name         varchar(100) NOT NULL,
    phone_number      varchar(20)  NOT NULL,
    email             varchar(50)  NOT NULL,
    address           varchar(255) NOT NULL,
    date_of_birth     date         NOT NULL,
    role              varchar(20)  NOT NULL,
    status            varchar(50)  NOT NULL,
    count_login_false int(1)       NOT NULL,
    note              text,
    create_date       timestamp    NOT NULL,
    modifier_by       varchar(64)  NOT NULL
);
CREATE TABLE supplier
(
    id           varchar(64)  NOT NULL,
    name         varchar(100) NOT NULL,
    phone_number varchar(20)  NOT NULL,
    email        varchar(50)  NOT NULL,
    address      varchar(255) NOT NULL,
    status       varchar(50)  NOT NULL,
    note         int(10),
    create_date  timestamp    NOT NULL,
    modifier_by  varchar(64)  NOT NULL
);
CREATE TABLE brand
(
    id           varchar(64)  NOT NULL,
    name         varchar(100) NOT NULL,
    phone_number varchar(20)  NOT NULL,
    email        varchar(50)  NOT NULL,
    address      varchar(255) NOT NULL,
    img_logo     varchar(255) NOT NULL,
    status       varchar(50)  NOT NULL,
    note         int(10),
    create_date  timestamp    NOT NULL,
    modifier_by  varchar(64)  NOT NULL
);
CREATE TABLE category
(
    id          varchar(64) NOT NULL,
    name        varchar(50) NOT NULL,
    status      varchar(50) NOT NULL,
    note        text,
    create_date timestamp   NOT NULL,
    modifier_by varchar(64) NOT NULL
);
CREATE TABLE product
(
    id            varchar(64)  NOT NULL,
    name          varchar(255) NOT NULL,
    category_id   varchar(64)  NOT NULL,
    category_name varchar(50)  NOT NULL,
    brand_id      varchar(64)  NOT NULL,
    brand_name    varchar(100) NOT NULL,
    detail        text         NOT NULL,
    status        varchar(50)  NOT NULL,
    note          text,
    create_date   timestamp    NOT NULL,
    modifier_by   varchar(64)
);
CREATE TABLE voucher
(
    id           varchar(64)  NOT NULL,
    name         varchar(200) NOT NULL,
    voucher_code varchar(50)  NOT NULL,
    quantity     int(11)      NOT NULL,
    start_date   datetime     NOT NULL,
    end_date     datetime     NOT NULL,
    status       varchar(50)  NOT NULL,
    discount     bigint(20)   NOT NULL,
    create_date  timestamp    NOT NULL,
    modifier_by  varchar(64)  NOT NULL,
    note         text
);
CREATE TABLE voucher_customer
(
    id            varchar(64) NOT NULL,
    customer_id   varchar(64) NOT NULL,
    phone_number  varchar(20) NOT NULL,
    customer_name varchar(50) NOT NULL,
    voucher_code  varchar(50) NOT NULL,
    discount      bigint(20)  NOT NULL,
    start_date    datetime    NOT NULL,
    end_date      datetime    NOT NULL,
    status        varchar(50) NOT NULL,
    note          text,
    create_date   timestamp   NOT NULL,
    modifier_by   varchar(64) NOT NULL
);

ALTER TABLE staff
    ADD PRIMARY KEY (id);
ALTER TABLE customer
    ADD PRIMARY KEY (id);
ALTER TABLE supplier
    ADD PRIMARY KEY (id);
ALTER TABLE brand
    ADD PRIMARY KEY (id);
ALTER TABLE category
    ADD PRIMARY KEY (id);
ALTER TABLE product
    ADD PRIMARY KEY (id);
ALTER TABLE voucher
    ADD PRIMARY KEY (id);
ALTER TABLE voucher_customer
    ADD PRIMARY KEY (id);