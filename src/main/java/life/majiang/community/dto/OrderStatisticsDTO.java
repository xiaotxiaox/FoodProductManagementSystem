package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/5 10:46
 */
@Data
public class OrderStatisticsDTO {
    private long allNumber; // 所有订单数量
    private long state1Number; // 状态1的订单数量
    private long state2Number;// 状态2的订单数量
    private long state3Number;// 状态3的订单数量
    private long state4Number;// 状态4的订单数量
    private long state5Number;// 状态5的订单数量
    private long state6Number;// 状态6的订单数量
}
