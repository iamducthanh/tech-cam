package com.techcam.entity;

import com.techcam.dto.Report.DailySaleDto;
import com.techcam.dto.Report.StaffSaleDto;
import com.techcam.dto.Report.TopProductSaleByMonthDto;
import com.techcam.dto.Report.TopProductSaleDto;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@NamedNativeQuery(
        name = "getDailySaleReport",
        query = "CALL PR_DAILY_SALES_REPORT(?1)",
        resultSetMapping = "getDailySaleReport"
)
@NamedNativeQuery(
        name = "getStaffSaleReport",
        query = "CALL PR_STAFF_SALES(?1, ?2)",
        resultSetMapping = "getStaffSaleReport"
)
@NamedNativeQuery(
        name = "getTopProductSale",
        query = "CALL PR_TOP_PRODUCT_SALE(?1, ?2, ?3)",
        resultSetMapping = "getTopProductSale"
)
@NamedNativeQuery(
        name = "getTopProductSaleByMonth",
        query = "CALL PR_TOP_PRODUCT_SALE_BY_MONTH(?1, ?2, ?3)",
        resultSetMapping = "getTopProductSaleByMonth"
)

@SqlResultSetMapping(
        name = "getDailySaleReport",
        classes = @ConstructorResult(
                targetClass = DailySaleDto.class,
                columns = {
                        @ColumnResult(name = "PRODUCT_CODE", type = String.class),
                        @ColumnResult(name = "NAME", type = String.class),
                        @ColumnResult(name = "price", type = Long.class),
                        @ColumnResult(name = "total_quantity", type = Integer.class),
                        @ColumnResult(name = "PROMOTION", type = Long.class),
                        @ColumnResult(name = "total_amount", type = Long.class)
                }
        )
)

@SqlResultSetMapping(
        name = "getStaffSaleReport",
        classes = @ConstructorResult(
                targetClass = StaffSaleDto.class,
                columns = {
                        @ColumnResult(name = "sales_person", type = String.class),
                        @ColumnResult(name = "FULL_NAME", type = String.class),
                        @ColumnResult(name = "sumReceiptValue", type = Long.class)
                }
        )
)

@SqlResultSetMapping(
        name = "getTopProductSale",
        classes = @ConstructorResult(
                targetClass = TopProductSaleDto.class,
                columns = {
                        @ColumnResult(name = "PRODUCT_CODE", type = String.class),
                        @ColumnResult(name = "price", type = String.class),
                        @ColumnResult(name = "total_quantity", type = Integer.class),
                        @ColumnResult(name = "total_amout", type = Long.class)
                }
        )
)

@SqlResultSetMapping(
        name = "getTopProductSaleByMonth",
        classes = @ConstructorResult(
                targetClass = TopProductSaleByMonthDto.class,
                columns = {
                        @ColumnResult(name = "PRODUCT_CODE", type = String.class),
                        @ColumnResult(name = "NAME", type = String.class),
                        @ColumnResult(name = "image", type = String.class),
                        @ColumnResult(name = "price", type = Long.class),
                        @ColumnResult(name = "total_quantity", type = Integer.class),
                        @ColumnResult(name = "discount", type = Long.class),
                        @ColumnResult(name = "total_amout", type = Long.class)
                }
        )
)

/**
 * @author DucBV
 * @version 1.0
 * @since 8.3.2022
 */
@Entity
@Table(name = "product", schema = "poly_techcam", catalog = "")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    private String id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CATEGORY_ID")
    private String categoryId;
    @Basic
    @Column(name = "BRAND_ID")
    private String brandId;
    @Basic
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Basic
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic
    @Column(name = "PRICE")
    private long price;
    @Basic
    @Column(name = "DETAIL")
    private String detail;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "NOTE")
    private String note;
    @Basic
    @Column(name = "THUMBNAIL")
    private String thumbnail;
    @Column(name = "PROMOTION")
    private Long promotion;
    @Column(name = "IMPORT_PRICE", nullable = false)
    private Integer importPrice;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<OrderdetailEntity> orderdetailEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductEntity that = (ProductEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
