package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Batch;
import life.majiang.community.model.BatchExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BatchMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    long countByExample(BatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    int deleteByExample(BatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    int insert(Batch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    int insertSelective(Batch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    List<Batch> selectByExampleWithRowbounds(BatchExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    List<Batch> selectByExample(BatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    Batch selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    int updateByExampleSelective(@Param("record") Batch record, @Param("example") BatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    int updateByExample(@Param("record") Batch record, @Param("example") BatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(Batch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Wed Dec 04 22:12:21 GMT+08:00 2019
     */
    int updateByPrimaryKey(Batch record);
}