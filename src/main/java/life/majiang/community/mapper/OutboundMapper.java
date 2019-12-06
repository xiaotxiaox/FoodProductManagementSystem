package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Outbound;
import life.majiang.community.model.OutboundExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OutboundMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    long countByExample(OutboundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    int deleteByExample(OutboundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    int insert(Outbound record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    int insertSelective(Outbound record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    List<Outbound> selectByExampleWithRowbounds(OutboundExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    List<Outbound> selectByExample(OutboundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    Outbound selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Outbound record, @Param("example") OutboundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Outbound record, @Param("example") OutboundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Outbound record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outbound
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Outbound record);
}