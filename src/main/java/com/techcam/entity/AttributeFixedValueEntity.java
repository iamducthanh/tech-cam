package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author DucBV
 * @version 1.0
 * @since 8.3.2022
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "attribute_fixed_value", schema = "poly_techcam", catalog = "")
public class AttributeFixedValueEntity {
    @Id
    @Column(name = "ID")
    private String id;
    @Basic
    @Column(name = "ATTRIBUTE_ID")
    private String attributeId;
    @Basic
    @Column(name = "ATTRIBUTE_FIXED_VAL")
    private String attributeFixedVal;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "NOTE")
    private String note;
    @Basic
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Basic
    @Column(name = "MODIFIER_DATE")
    private Date modifierDate;
    @Basic
    @Column(name = "CREATE_BY")
    private String createBy;
    @Basic
    @Column(name = "MODIFIER_BY")
    private String modifierBy;
    @Basic
    @Column(name = "DELETE_FLAG")
    private boolean deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeFixedVal() {
        return attributeFixedVal;
    }

    public void setAttributeFixedVal(String attributeFixedVal) {
        this.attributeFixedVal = attributeFixedVal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }


    public void setModifierDate(Timestamp modifierDate) {
        this.modifierDate = modifierDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifierBy() {
        return modifierBy;
    }

    public void setModifierBy(String modifierBy) {
        this.modifierBy = modifierBy;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeFixedValueEntity that = (AttributeFixedValueEntity) o;
        return deleteFlag == that.deleteFlag && Objects.equals(id, that.id) && Objects.equals(attributeId, that.attributeId) && Objects.equals(attributeFixedVal, that.attributeFixedVal) && Objects.equals(status, that.status) && Objects.equals(note, that.note) && Objects.equals(createDate, that.createDate) && Objects.equals(modifierDate, that.modifierDate) && Objects.equals(createBy, that.createBy) && Objects.equals(modifierBy, that.modifierBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attributeId, attributeFixedVal, status, note, createDate, modifierDate, createBy, modifierBy, deleteFlag);
    }
}
