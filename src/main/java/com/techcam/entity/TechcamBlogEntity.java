package com.techcam.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/28/2022
 * Project_name: Tech-cam
 */

@Entity
@Table(name = "techcam_blog", schema = "poly_techcam", catalog = "")
public class TechcamBlogEntity {
    private String id;
    private String operationLink;
    private String operationKey;
    private String operationAct;
    private String operationDesc;
    private String staffId;
    private Date createDate;
    private String createBy;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "operation_link")
    public String getOperationLink() {
        return operationLink;
    }

    public void setOperationLink(String operationLink) {
        this.operationLink = operationLink;
    }

    @Basic
    @Column(name = "operation_key")
    public String getOperationKey() {
        return operationKey;
    }

    public void setOperationKey(String operationKey) {
        this.operationKey = operationKey;
    }

    @Basic
    @Column(name = "operation_act")
    public String getOperationAct() {
        return operationAct;
    }

    public void setOperationAct(String operationAct) {
        this.operationAct = operationAct;
    }

    @Basic
    @Column(name = "operation_desc")
    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    @Basic
    @Column(name = "staff_id")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_BY")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TechcamBlogEntity that = (TechcamBlogEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (operationLink != null ? !operationLink.equals(that.operationLink) : that.operationLink != null)
            return false;
        if (operationKey != null ? !operationKey.equals(that.operationKey) : that.operationKey != null) return false;
        if (operationAct != null ? !operationAct.equals(that.operationAct) : that.operationAct != null) return false;
        if (operationDesc != null ? !operationDesc.equals(that.operationDesc) : that.operationDesc != null)
            return false;
        if (staffId != null ? !staffId.equals(that.staffId) : that.staffId != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (operationLink != null ? operationLink.hashCode() : 0);
        result = 31 * result + (operationKey != null ? operationKey.hashCode() : 0);
        result = 31 * result + (operationAct != null ? operationAct.hashCode() : 0);
        result = 31 * result + (operationDesc != null ? operationDesc.hashCode() : 0);
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        return result;
    }
}
