package life.majiang.community.model;

public class Employee {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.id
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.timein
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    private String timein;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.gender
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    private Integer gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.phone
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.pay
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    private Integer pay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.department
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    private Integer department;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.person
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    private Integer person;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.name
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.id
     *
     * @return the value of employee.id
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.id
     *
     * @param id the value for employee.id
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.timein
     *
     * @return the value of employee.timein
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public String getTimein() {
        return timein;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.timein
     *
     * @param timein the value for employee.timein
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public void setTimein(String timein) {
        this.timein = timein == null ? null : timein.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.gender
     *
     * @return the value of employee.gender
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.gender
     *
     * @param gender the value for employee.gender
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.phone
     *
     * @return the value of employee.phone
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.phone
     *
     * @param phone the value for employee.phone
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.pay
     *
     * @return the value of employee.pay
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public Integer getPay() {
        return pay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.pay
     *
     * @param pay the value for employee.pay
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public void setPay(Integer pay) {
        this.pay = pay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.department
     *
     * @return the value of employee.department
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public Integer getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.department
     *
     * @param department the value for employee.department
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public void setDepartment(Integer department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.person
     *
     * @return the value of employee.person
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public Integer getPerson() {
        return person;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.person
     *
     * @param person the value for employee.person
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public void setPerson(Integer person) {
        this.person = person;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.name
     *
     * @return the value of employee.name
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.name
     *
     * @param name the value for employee.name
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}