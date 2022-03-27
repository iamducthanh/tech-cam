package com.techcam.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shipment")
public class ShipmentEntity extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false, length = 64)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrdersEntity order;

    @Column(name = "shipper_name")
    private String shipperName;

    @Column(name = "shipper_phone", length = 20)
    private String shipperPhone;

    @Column(name = "ship_fee", precision = 10)
    private BigDecimal shipFee;

    @Column(name = "shipment_status", length = 50)
    private String shipmentStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;

    @Lob
    @Column(name = "NOTE")
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public BigDecimal getShipFee() {
        return shipFee;
    }

    public void setShipFee(BigDecimal shipFee) {
        this.shipFee = shipFee;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public OrdersEntity getOrder() {
        return order;
    }

    public void setOrder(OrdersEntity order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}