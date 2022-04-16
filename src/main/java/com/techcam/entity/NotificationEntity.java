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
import java.util.Date;
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
    @Column(name = "product_id")
    private String productId;
    @Column(name = "content")
    private String content;
    @Column(name = "type_col")
    private String type;
    @Column(name = "read_col")
    private Boolean read;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "MODIFIER_DATE")
    private Date modifierDate;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "MODIFIER_BY")
    private String modifierBy;
    @Column(name = "DELETE_FLAG")
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifierDate() {
        return modifierDate;
    }

    public void setModifierDate(Date modifierDate) {
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
