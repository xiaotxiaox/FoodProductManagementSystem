package life.majiang.community.dto;

import life.majiang.community.model.Custom;
import life.majiang.community.model.Goods;
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
//    private Integer goods; //商品名称 目前这个为一个数字（没表），可以先不传
    private String orderDate; //订货日期
    private String pickDate;  //提货日期
    private Integer handler;  //处理人（这个字段没用）
    private User user;  //处理人，get的时候获得一个Object对象（User对象1）
    private BigDecimal totalCost;  //订单总金额
    private BigDecimal discountCost;  //优惠后金额
    private BigDecimal paidMoney;  //已付货款
    private String willDate;  //预付款日期
    private String finalDate;  //尾款日期
    private Integer state;  //订单状态
    private String backDate;  //退货日期
    private String backAgreeDate;  //退货同意日期
    private Integer backMoneySymbol;  //退货款符号（0为+，1为-）
    private BigDecimal backMoney;  //退货款
    private Goods good;
}
