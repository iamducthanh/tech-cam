package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "techcam_blog", schema = "poly_techcam", catalog = "")
public class LogEntity {
    @Id
    private String id;
    @Column(name = "operation_link")
    private String operationLink;
    @Column(name = "operation_key")
    private String operationKey;
    @Column(name = "operation_act")
    private String operationAct;
    @Column(name = "operation_desc")
    private String operationDesc;
    @Column(name = "staff_id")
    private String staffId;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "CREATE_BY")
    private String createBy;

    public String getTimeString(){
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(this.createDate);
    }

}
