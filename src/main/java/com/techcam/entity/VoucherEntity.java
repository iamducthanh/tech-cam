package com.techcam.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@ToString
@Table(name = "voucher")
@Entity
public class VoucherEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "voucher_code", nullable = false, length = 50)
    private String voucherCode;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "discount", nullable = false)
    private Long discount;
}
