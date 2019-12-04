package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Manager;
import life.majiang.community.model.ManagerExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ManagerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    long countByExample(ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int deleteByExample(ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int insert(Manager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int insertSelective(Manager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    List<Manager> selectByExampleWithRowbounds(ManagerExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    List<Manager> selectByExample(ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    Manager selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(Manager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager
     *
     * @mbg.generated Wed Dec 04 20:39:17 GMT+08:00 2019
     */
    int updateByPrimaryKey(Manager record);
}