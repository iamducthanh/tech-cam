package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@NamedNativeQuery(
        name = "findProfit",
        query = "call PR_PROFIT_PRODUCT(?1)",
        resultSetMapping = "findProfit"
)
@SqlResultSetMapping(
        name = "findProfit",
        classes = @ConstructorResult(
                targetClass = StatisProfit.class,
                columns = {
                        @ColumnResult(name = "month_profit", type = String.class),
                        @ColumnResult(name = "profit", type = Integer.class),
                }
        )
)
public class StatisProfit {
    @Id
    @Column(name = "month_profit")
    private String month;
    private Integer profit;
}
