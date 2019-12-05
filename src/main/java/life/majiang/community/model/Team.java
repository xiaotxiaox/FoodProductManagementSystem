package life.majiang.community.model;

public class Team {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team.department
     *
     * @mbg.generated
     */
    private Integer department;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team.factory
     *
     * @mbg.generated
     */
    private Integer factory;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team.leader
     *
     * @mbg.generated
     */
    private String leader;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team.leader_phone
     *
     * @mbg.generated
     */
    private String leaderPhone;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team.id
     *
     * @return the value of team.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team.id
     *
     * @param id the value for team.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team.department
     *
     * @return the value of team.department
     *
     * @mbg.generated
     */
    public Integer getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team.department
     *
     * @param department the value for team.department
     *
     * @mbg.generated
     */
    public void setDepartment(Integer department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team.factory
     *
     * @return the value of team.factory
     *
     * @mbg.generated
     */
    public Integer getFactory() {
        return factory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team.factory
     *
     * @param factory the value for team.factory
     *
     * @mbg.generated
     */
    public void setFactory(Integer factory) {
        this.factory = factory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team.name
     *
     * @return the value of team.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team.name
     *
     * @param name the value for team.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team.leader
     *
     * @return the value of team.leader
     *
     * @mbg.generated
     */
    public String getLeader() {
        return leader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team.leader
     *
     * @param leader the value for team.leader
     *
     * @mbg.generated
     */
    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team.leader_phone
     *
     * @return the value of team.leader_phone
     *
     * @mbg.generated
     */
    public String getLeaderPhone() {
        return leaderPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team.leader_phone
     *
     * @param leaderPhone the value for team.leader_phone
     *
     * @mbg.generated
     */
    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone == null ? null : leaderPhone.trim();
    }
}