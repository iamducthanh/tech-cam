package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/21/2022 11:24 PM
 */
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@NamedNativeQuery(
        name = "findTopProductSaleByMonth",
        query = "call PR_TOP_PRODUCT_SALE_BY_MONTH(?1, ?2, ?3)",
        resultSetMapping = "findTopProductSaleByMonth"
)
@SqlResultSetMapping(
        name = "findTopProductSaleByMonth",
        classes = @ConstructorResult(
                targetClass = TopProductSaleByMonth.class,
                columns = {
                        @ColumnResult(name = "PRODUCT_CODE", type = String.class),
                        @ColumnResult(name = "NAME", type = String.class),
                        @ColumnResult(name = "image", type = String.class),
                        @ColumnResult(name = "price", type = Double.class),
                        @ColumnResult(name = "total_quantity", type = Integer.class),
                        @ColumnResult(name = "discount", type = Double.class),
                        @ColumnResult(name = "total_amout", type = Double.class)
                }
        )
)
public class TopProductSaleByMonth {
 @Id
 //@Column(name = "PRODUCT_CODE")
 private String productCode;
 private String name;
 private String image;

 //@Column(name = "price")
 private Double price;
 //@Column(name = "total_quantity")
 private Integer totalQuantity;
 //@Column(name = "total_amout")
 private Double discount;
 private Double totalAmount;
}
