package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Permissions;
import life.majiang.community.model.PermissionsExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PermissionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    long countByExample(PermissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int deleteByExample(PermissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int insert(Permissions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int insertSelective(Permissions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    List<Permissions> selectByExampleWithRowbounds(PermissionsExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    List<Permissions> selectByExample(PermissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    Permissions selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int updateByExampleSelective(@Param("record") Permissions record, @Param("example") PermissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int updateByExample(@Param("record") Permissions record, @Param("example") PermissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(Permissions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int updateByPrimaryKey(Permissions record);
}