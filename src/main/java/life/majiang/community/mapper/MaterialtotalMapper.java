package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Materialtotal;
import life.majiang.community.model.MaterialtotalExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MaterialtotalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    long countByExample(MaterialtotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    int deleteByExample(MaterialtotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    int insert(Materialtotal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    int insertSelective(Materialtotal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    List<Materialtotal> selectByExampleWithRowbounds(MaterialtotalExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    List<Materialtotal> selectByExample(MaterialtotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    Materialtotal selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Materialtotal record, @Param("example") MaterialtotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Materialtotal record, @Param("example") MaterialtotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Materialtotal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table materialtotal
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Materialtotal record);
}