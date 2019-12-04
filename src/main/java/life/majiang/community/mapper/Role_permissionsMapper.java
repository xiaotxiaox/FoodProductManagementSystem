package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Role_permissions;
import life.majiang.community.model.Role_permissionsExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface Role_permissionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    long countByExample(Role_permissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    int deleteByExample(Role_permissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    int insert(Role_permissions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    int insertSelective(Role_permissions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    List<Role_permissions> selectByExampleWithRowbounds(Role_permissionsExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    List<Role_permissions> selectByExample(Role_permissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    Role_permissions selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    int updateByExampleSelective(@Param("record") Role_permissions record, @Param("example") Role_permissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    int updateByExample(@Param("record") Role_permissions record, @Param("example") Role_permissionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    int updateByPrimaryKeySelective(Role_permissions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permissions
     *
     * @mbg.generated Wed Dec 04 19:30:29 CST 2019
     */
    int updateByPrimaryKey(Role_permissions record);
}