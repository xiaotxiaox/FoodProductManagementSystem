package life.majiang.community.model;

public class Role {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.id
     *
     * @mbg.generated Wed Dec 04 18:30:12 GMT+08:00 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.name
     *
     * @mbg.generated Wed Dec 04 18:30:12 GMT+08:00 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.description
     *
     * @mbg.generated Wed Dec 04 18:30:12 GMT+08:00 2019
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbg.generated Wed Dec 04 18:30:12 GMT+08:00 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbg.generated Wed Dec 04 18:30:12 GMT+08:00 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.name
     *
     * @return the value of role.name
     *
     * @mbg.generated Wed Dec 04 18:30:12 GMT+08:00 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.name
     *
     * @param name the value for role.name
     *
     * @mbg.generated Wed Dec 04 18:30:12 GMT+08:00 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.description
     *
     * @return the value of role.description
     *
     * @mbg.generated Wed Dec 04 18:30:12 GMT+08:00 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.description
     *
     * @param description the value for role.description
     *
     * @mbg.generated Wed Dec 04 18:30:12 GMT+08:00 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}