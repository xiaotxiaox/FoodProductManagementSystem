package life.majiang.community.model;

public class Productionplan {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productionplan.id
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productionplan.timecreate
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    private String timecreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productionplan.neednum
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    private Integer neednum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productionplan.timelastest
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    private String timelastest;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productionplan.state
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    private Integer state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productionplan.id
     *
     * @return the value of productionplan.id
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productionplan.id
     *
     * @param id the value for productionplan.id
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productionplan.timecreate
     *
     * @return the value of productionplan.timecreate
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public String getTimecreate() {
        return timecreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productionplan.timecreate
     *
     * @param timecreate the value for productionplan.timecreate
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public void setTimecreate(String timecreate) {
        this.timecreate = timecreate == null ? null : timecreate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productionplan.neednum
     *
     * @return the value of productionplan.neednum
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public Integer getNeednum() {
        return neednum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productionplan.neednum
     *
     * @param neednum the value for productionplan.neednum
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public void setNeednum(Integer neednum) {
        this.neednum = neednum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productionplan.timelastest
     *
     * @return the value of productionplan.timelastest
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public String getTimelastest() {
        return timelastest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productionplan.timelastest
     *
     * @param timelastest the value for productionplan.timelastest
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public void setTimelastest(String timelastest) {
        this.timelastest = timelastest == null ? null : timelastest.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productionplan.state
     *
     * @return the value of productionplan.state
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productionplan.state
     *
     * @param state the value for productionplan.state
     *
     * @mbg.generated Thu Dec 05 11:02:47 CST 2019
     */
    public void setState(Integer state) {
        this.state = state;
    }
}