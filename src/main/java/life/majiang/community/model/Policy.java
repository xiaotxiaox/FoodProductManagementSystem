package life.majiang.community.model;

public class Policy {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POLICY.ID
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POLICY.TYPE
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POLICY.RATE
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    private Integer rate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POLICY.HANDLER
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    private Integer handler;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POLICY.DISCOUNT
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    private Integer discount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POLICY.ID
     *
     * @return the value of POLICY.ID
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POLICY.ID
     *
     * @param id the value for POLICY.ID
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POLICY.TYPE
     *
     * @return the value of POLICY.TYPE
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POLICY.TYPE
     *
     * @param type the value for POLICY.TYPE
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POLICY.RATE
     *
     * @return the value of POLICY.RATE
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POLICY.RATE
     *
     * @param rate the value for POLICY.RATE
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POLICY.HANDLER
     *
     * @return the value of POLICY.HANDLER
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public Integer getHandler() {
        return handler;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POLICY.HANDLER
     *
     * @param handler the value for POLICY.HANDLER
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public void setHandler(Integer handler) {
        this.handler = handler;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POLICY.DISCOUNT
     *
     * @return the value of POLICY.DISCOUNT
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POLICY.DISCOUNT
     *
     * @param discount the value for POLICY.DISCOUNT
     *
     * @mbg.generated Mon Dec 02 22:07:53 CST 2019
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}