package life.majiang.community.model;

public class Role_permissions {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permissions.id
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permissions.role
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    private Integer role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permissions.permissions
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    private Integer permissions;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permissions.id
     *
     * @return the value of role_permissions.id
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permissions.id
     *
     * @param id the value for role_permissions.id
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permissions.role
     *
     * @return the value of role_permissions.role
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permissions.role
     *
     * @param role the value for role_permissions.role
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permissions.permissions
     *
     * @return the value of role_permissions.permissions
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public Integer getPermissions() {
        return permissions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permissions.permissions
     *
     * @param permissions the value for role_permissions.permissions
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }
}