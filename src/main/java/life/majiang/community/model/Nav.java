package life.majiang.community.model;

public class Nav {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nav.id
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nav.title
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nav.url
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nav.priority
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    private Integer priority;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nav.gmt_create
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nav.gmt_modified
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nav.status
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nav.id
     *
     * @return the value of nav.id
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nav.id
     *
     * @param id the value for nav.id
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nav.title
     *
     * @return the value of nav.title
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nav.title
     *
     * @param title the value for nav.title
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nav.url
     *
     * @return the value of nav.url
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nav.url
     *
     * @param url the value for nav.url
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nav.priority
     *
     * @return the value of nav.priority
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nav.priority
     *
     * @param priority the value for nav.priority
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nav.gmt_create
     *
     * @return the value of nav.gmt_create
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nav.gmt_create
     *
     * @param gmtCreate the value for nav.gmt_create
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nav.gmt_modified
     *
     * @return the value of nav.gmt_modified
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nav.gmt_modified
     *
     * @param gmtModified the value for nav.gmt_modified
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nav.status
     *
     * @return the value of nav.status
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nav.status
     *
     * @param status the value for nav.status
     *
     * @mbg.generated Tue Dec 03 21:02:46 GMT+08:00 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}