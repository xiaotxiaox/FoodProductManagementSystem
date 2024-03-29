package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Round;
import life.majiang.community.model.RoundExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoundMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    long countByExample(RoundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    int deleteByExample(RoundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    int insert(Round record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    int insertSelective(Round record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    List<Round> selectByExampleWithRowbounds(RoundExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    List<Round> selectByExample(RoundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    Round selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Round record, @Param("example") RoundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Round record, @Param("example") RoundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Round record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table round
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Round record);
}