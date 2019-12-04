package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Material;
import life.majiang.community.model.MaterialExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MaterialMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    long countByExample(MaterialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    int deleteByExample(MaterialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    int insert(Material record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    int insertSelective(Material record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    List<Material> selectByExampleWithRowbounds(MaterialExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    List<Material> selectByExample(MaterialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    Material selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    int updateByExampleSelective(@Param("record") Material record, @Param("example") MaterialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    int updateByExample(@Param("record") Material record, @Param("example") MaterialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(Material record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material
     *
     * @mbg.generated Wed Dec 04 21:26:31 GMT+08:00 2019
     */
    int updateByPrimaryKey(Material record);
}