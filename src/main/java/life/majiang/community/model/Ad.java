package life.majiang.community.model;

public class Ad {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.id
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.title
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.url
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.image
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private String image;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.gmt_start
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private Long gmtStart;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.gmt_end
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private Long gmtEnd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.gmt_create
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.gmt_modified
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.status
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad.pos
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    private String pos;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.id
     *
     * @return the value of ad.id
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.id
     *
     * @param id the value for ad.id
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.title
     *
     * @return the value of ad.title
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.title
     *
     * @param title the value for ad.title
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.url
     *
     * @return the value of ad.url
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.url
     *
     * @param url the value for ad.url
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.image
     *
     * @return the value of ad.image
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.image
     *
     * @param image the value for ad.image
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.gmt_start
     *
     * @return the value of ad.gmt_start
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public Long getGmtStart() {
        return gmtStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.gmt_start
     *
     * @param gmtStart the value for ad.gmt_start
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setGmtStart(Long gmtStart) {
        this.gmtStart = gmtStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.gmt_end
     *
     * @return the value of ad.gmt_end
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public Long getGmtEnd() {
        return gmtEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.gmt_end
     *
     * @param gmtEnd the value for ad.gmt_end
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setGmtEnd(Long gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.gmt_create
     *
     * @return the value of ad.gmt_create
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.gmt_create
     *
     * @param gmtCreate the value for ad.gmt_create
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.gmt_modified
     *
     * @return the value of ad.gmt_modified
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.gmt_modified
     *
     * @param gmtModified the value for ad.gmt_modified
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.status
     *
     * @return the value of ad.status
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.status
     *
     * @param status the value for ad.status
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.pos
     *
     * @return the value of ad.pos
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public String getPos() {
        return pos;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.pos
     *
     * @param pos the value for ad.pos
     *
     * @mbg.generated Wed Dec 04 20:42:34 GMT+08:00 2019
     */
    public void setPos(String pos) {
        this.pos = pos == null ? null : pos.trim();
    }
}