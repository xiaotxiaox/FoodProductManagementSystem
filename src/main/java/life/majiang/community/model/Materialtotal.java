package life.majiang.community.model;

public class Materialtotal {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column materialtotal.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column materialtotal.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column materialtotal.purchaser
     *
     * @mbg.generated
     */
    private String purchaser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column materialtotal.price
     *
     * @mbg.generated
     */
    private Integer price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column materialtotal.unit
     *
     * @mbg.generated
     */
    private String unit;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column materialtotal.id
     *
     * @return the value of materialtotal.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column materialtotal.id
     *
     * @param id the value for materialtotal.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column materialtotal.name
     *
     * @return the value of materialtotal.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column materialtotal.name
     *
     * @param name the value for materialtotal.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column materialtotal.purchaser
     *
     * @return the value of materialtotal.purchaser
     *
     * @mbg.generated
     */
    public String getPurchaser() {
        return purchaser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column materialtotal.purchaser
     *
     * @param purchaser the value for materialtotal.purchaser
     *
     * @mbg.generated
     */
    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser == null ? null : purchaser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column materialtotal.price
     *
     * @return the value of materialtotal.price
     *
     * @mbg.generated
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column materialtotal.price
     *
     * @param price the value for materialtotal.price
     *
     * @mbg.generated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column materialtotal.unit
     *
     * @return the value of materialtotal.unit
     *
     * @mbg.generated
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column materialtotal.unit
     *
     * @param unit the value for materialtotal.unit
     *
     * @mbg.generated
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }
}