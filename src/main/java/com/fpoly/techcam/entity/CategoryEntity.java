package com.fpoly.techcam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name = "category")
@Entity
public class CategoryEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Lob
    @Column(name = "note")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
