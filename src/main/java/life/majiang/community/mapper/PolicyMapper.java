package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Policy;
import life.majiang.community.model.PolicyExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PolicyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    long countByExample(PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    int deleteByExample(PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    int insert(Policy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    int insertSelective(Policy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    List<Policy> selectByExampleWithRowbounds(PolicyExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    List<Policy> selectByExample(PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    Policy selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    int updateByExampleSelective(@Param("record") Policy record, @Param("example") PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    int updateByExample(@Param("record") Policy record, @Param("example") PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    int updateByPrimaryKeySelective(Policy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Mon Dec 02 21:18:44 CST 2019
     */
    int updateByPrimaryKey(Policy record);
}