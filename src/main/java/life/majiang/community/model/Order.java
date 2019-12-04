package life.majiang.community.model;

public class Order {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.id
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.custom
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    private Integer custom;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.count
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    private Integer count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.goods
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    private Integer goods;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.order_date
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    private String orderDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.pick_date
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    private String pickDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_collection.handler
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    private Long handler;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.id
     *
     * @return the value of order_collection.id
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.id
     *
     * @param id the value for order_collection.id
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_collection.custom
     *
     * @return the value of order_collection.custom
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public Long getHandler() {
        return handler;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_collection.handler
     *
     * @param handler the value for order_collection.handler
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public void setHandler(Long handler) {
        this.handler = handler;
    }
}