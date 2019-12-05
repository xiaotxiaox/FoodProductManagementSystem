package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Custom;
import life.majiang.community.model.CustomExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CustomMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    long countByExample(CustomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    int deleteByExample(CustomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    int insert(Custom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    int insertSelective(Custom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    List<Custom> selectByExampleWithRowbounds(CustomExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    List<Custom> selectByExample(CustomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    Custom selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    int updateByExampleSelective(@Param("record") Custom record, @Param("example") CustomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    int updateByExample(@Param("record") Custom record, @Param("example") CustomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    int updateByPrimaryKeySelective(Custom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom
     *
     * @mbg.generated Thu Dec 05 11:21:39 CST 2019
     */
    int updateByPrimaryKey(Custom record);
}