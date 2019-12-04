package life.majiang.community.model;

import java.math.BigDecimal;

public class Order {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.id
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.custom
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private Integer custom;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.count
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private Integer count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.goods
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private Integer goods;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.order_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private String orderDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.pick_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private String pickDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.handler
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private Integer handler;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.total_cost
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private BigDecimal totalCost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.discount_cost
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private BigDecimal discountCost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.paid_money
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private BigDecimal paidMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.will_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private String willDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.final_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private String finalDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.state
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private Integer state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.back_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private String backDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.back_agree_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private String backAgreeDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.back_money_symbol
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private Integer backMoneySymbol;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.back_money
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    private BigDecimal backMoney;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.id
     *
     * @return the value of order_collection.id
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.id
     *
     * @param id the value for order_collection.id
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.custom
     *
     * @return the value of order_collection.custom
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public Integer getCustom() {
        return custom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.custom
     *
     * @param custom the value for order_collection.custom
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setCustom(Integer custom) {
        this.custom = custom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.count
     *
     * @return the value of order_collection.count
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.count
     *
     * @param count the value for order_collection.count
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.goods
     *
     * @return the value of order_collection.goods
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public Integer getGoods() {
        return goods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.goods
     *
     * @param goods the value for order_collection.goods
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.order_date
     *
     * @return the value of order_collection.order_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.order_date
     *
     * @param orderDate the value for order_collection.order_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate == null ? null : orderDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.pick_date
     *
     * @return the value of order_collection.pick_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public String getPickDate() {
        return pickDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.pick_date
     *
     * @param pickDate the value for order_collection.pick_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setPickDate(String pickDate) {
        this.pickDate = pickDate == null ? null : pickDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.handler
     *
     * @return the value of order_collection.handler
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public Integer getHandler() {
        return handler;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.handler
     *
     * @param handler the value for order_collection.handler
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setHandler(Integer handler) {
        this.handler = handler;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.total_cost
     *
     * @return the value of order_collection.total_cost
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.total_cost
     *
     * @param totalCost the value for order_collection.total_cost
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.discount_cost
     *
     * @return the value of order_collection.discount_cost
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public BigDecimal getDiscountCost() {
        return discountCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.discount_cost
     *
     * @param discountCost the value for order_collection.discount_cost
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setDiscountCost(BigDecimal discountCost) {
        this.discountCost = discountCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.paid_money
     *
     * @return the value of order_collection.paid_money
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public BigDecimal getPaidMoney() {
        return paidMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.paid_money
     *
     * @param paidMoney the value for order_collection.paid_money
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setPaidMoney(BigDecimal paidMoney) {
        this.paidMoney = paidMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.will_date
     *
     * @return the value of order_collection.will_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public String getWillDate() {
        return willDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.will_date
     *
     * @param willDate the value for order_collection.will_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setWillDate(String willDate) {
        this.willDate = willDate == null ? null : willDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.final_date
     *
     * @return the value of order_collection.final_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public String getFinalDate() {
        return finalDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.final_date
     *
     * @param finalDate the value for order_collection.final_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate == null ? null : finalDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.state
     *
     * @return the value of order_collection.state
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.state
     *
     * @param state the value for order_collection.state
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.back_date
     *
     * @return the value of order_collection.back_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public String getBackDate() {
        return backDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.back_date
     *
     * @param backDate the value for order_collection.back_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setBackDate(String backDate) {
        this.backDate = backDate == null ? null : backDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.back_agree_date
     *
     * @return the value of order_collection.back_agree_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public String getBackAgreeDate() {
        return backAgreeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.back_agree_date
     *
     * @param backAgreeDate the value for order_collection.back_agree_date
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setBackAgreeDate(String backAgreeDate) {
        this.backAgreeDate = backAgreeDate == null ? null : backAgreeDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.back_money_symbol
     *
     * @return the value of order_collection.back_money_symbol
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public Integer getBackMoneySymbol() {
        return backMoneySymbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.back_money_symbol
     *
     * @param backMoneySymbol the value for order_collection.back_money_symbol
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setBackMoneySymbol(Integer backMoneySymbol) {
        this.backMoneySymbol = backMoneySymbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.back_money
     *
     * @return the value of order_collection.back_money
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public BigDecimal getBackMoney() {
        return backMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.back_money
     *
     * @param backMoney the value for order_collection.back_money
     *
     * @mbg.generated Wed Dec 04 21:48:20 GMT+08:00 2019
     */
    public void setBackMoney(BigDecimal backMoney) {
        this.backMoney = backMoney;
    }
}