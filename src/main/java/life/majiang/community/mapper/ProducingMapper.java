package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Producing;
import life.majiang.community.model.ProducingExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProducingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    long countByExample(ProducingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    int deleteByExample(ProducingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    int insert(Producing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    int insertSelective(Producing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    List<Producing> selectByExampleWithRowbounds(ProducingExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    List<Producing> selectByExample(ProducingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    Producing selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Producing record, @Param("example") ProducingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Producing record, @Param("example") ProducingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Producing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table producing
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Producing record);
}