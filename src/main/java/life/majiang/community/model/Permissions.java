package life.majiang.community.model;

public class Permissions {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permissions.id
     *
     * @mbg.generated Tue Dec 03 11:46:41 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permissions.name
     *
     * @mbg.generated Tue Dec 03 11:46:41 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permissions.description
     *
     * @mbg.generated Tue Dec 03 11:46:41 CST 2019
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permissions.id
     *
     * @return the value of permissions.id
     *
     * @mbg.generated Tue Dec 03 11:46:41 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permissions.id
     *
     * @param id the value for permissions.id
     *
     * @mbg.generated Tue Dec 03 11:46:41 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permissions.name
     *
     * @return the value of permissions.name
     *
     * @mbg.generated Tue Dec 03 11:46:41 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permissions.name
     *
     * @param name the value for permissions.name
     *
     * @mbg.generated Tue Dec 03 11:46:41 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permissions.description
     *
     * @return the value of permissions.description
     *
     * @mbg.generated Tue Dec 03 11:46:41 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permissions.description
     *
     * @param description the value for permissions.description
     *
     * @mbg.generated Tue Dec 03 11:46:41 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}