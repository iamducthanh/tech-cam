package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 16.4.2022
 * Description :
 */
@Entity@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification", schema = "poly_techcam", catalog = "")
public class NotificationEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "product_id")
    private String productId;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "type_col")
    private String type;
    @Basic
    @Column(name = "read_col")
    private Boolean read;
    @Basic
    @CreatedDate
    @Column(name = "CREATE_DATE", insertable = false)
    private Timestamp createDate;
    @Basic
    @LastModifiedDate
    @Column(name = "MODIFIER_DATE", insertable = false)
    private Timestamp modifierDate;
    @Basic
    @CreatedBy
    @Column(name = "CREATE_BY", insertable = false)
    private String createBy;
    @Basic
    @LastModifiedBy
    @Column(name = "MODIFIER_BY", insertable = false)
    private String modifierBy;
    @Basic
    @Column(name = "DELETE_FLAG", insertable = false)
    private Boolean deleteFlag = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifierDate() {
        return modifierDate;
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

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
