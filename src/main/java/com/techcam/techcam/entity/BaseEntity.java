package com.techcam.techcam.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 11:27 AM
 * Project_name : tech-cam
 */

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    /**
     * ngày tạo bản ghi
     */
    @CreatedDate
    @Column(name = "create_date", updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createDate;

    /**
     * người cập nhật bản ghi
     */
    @LastModifiedBy
    @Column(name = "modifier_by", length = 50, nullable = false)
    private String modifiedBy;

    /**
     * trạng thái xoá bản ghi
     * true là đã xoá
     * false là chưa bị xoá
     */
    @Column(name = "delete_flag", columnDefinition = "bit default 0")
    private boolean deleteFlag;

}