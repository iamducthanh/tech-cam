package com.techcam.entity;

import javax.persistence.*;

@Table(name = "attribute")
@Entity
public class AttributeEntity extends BaseEntity {
    @Column(name = "ATTRIBUTE_NAME", nullable = false, length = 50)
    private String attributeName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_ID", nullable = false)
    private CategoryEntity category;

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

    public CategoryEntity getcategory() {
        return category;
    }

    public void setcategory(CategoryEntity category) {
        this.category = category;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
