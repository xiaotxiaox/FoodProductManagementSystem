package life.majiang.community.model;

public class Policy {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.id
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.type
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.rate
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    private Integer rate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.handler
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    private Integer handler;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.discount
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    private Integer discount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.id
     *
     * @return the value of policy.id
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.id
     *
     * @param id the value for policy.id
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.type
     *
     * @return the value of policy.type
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.type
     *
     * @param type the value for policy.type
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.rate
     *
     * @return the value of policy.rate
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.rate
     *
     * @param rate the value for policy.rate
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.handler
     *
     * @return the value of policy.handler
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public Integer getHandler() {
        return handler;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.handler
     *
     * @param handler the value for policy.handler
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public void setHandler(Integer handler) {
        this.handler = handler;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.discount
     *
     * @return the value of policy.discount
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.discount
     *
     * @param discount the value for policy.discount
     *
     * @mbg.generated Wed Dec 04 14:52:02 CST 2019
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}