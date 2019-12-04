package life.majiang.community.dto;

import life.majiang.community.model.Custom;
import life.majiang.community.model.User;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by xiaot
 * 2019/12/4 14:24
 */
@Data
public class OrderCollectionDTO {
    private Integer id;
    private Custom custom;
    private Integer count; //商品数量
    private Integer goods; //商品名称
    private String orderDate;
    private String pickDate;
    private Integer handler;
    private User user;
    private BigDecimal totalCost;
    private BigDecimal discountCost;
    private BigDecimal paidMoney;
    private String willDate;
    private String finalDate;
    private Integer state;
}
