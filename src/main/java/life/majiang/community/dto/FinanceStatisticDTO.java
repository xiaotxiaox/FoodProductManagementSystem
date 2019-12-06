package life.majiang.community.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by xiaot
 * 2019/12/6 18:40
 */
@Data
public class FinanceStatisticDTO {
    private String month;
    private BigDecimal order_income = new BigDecimal(0);
    private int salary_outcome = 0;
    private BigDecimal back_outcome= new BigDecimal(0);
    private BigDecimal purchase_outcome= new BigDecimal(0);
    private BigDecimal total_income= new BigDecimal(0);
    private BigDecimal total_outcome= new BigDecimal(0);
    private BigDecimal profit= new BigDecimal(0);
}
