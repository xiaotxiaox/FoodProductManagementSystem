package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Good_material;
import life.majiang.community.model.Good_materialExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface Good_materialMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    long countByExample(Good_materialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    int deleteByExample(Good_materialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    int insert(Good_material record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    int insertSelective(Good_material record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    List<Good_material> selectByExampleWithRowbounds(Good_materialExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    List<Good_material> selectByExample(Good_materialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    Good_material selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Good_material record, @Param("example") Good_materialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Good_material record, @Param("example") Good_materialExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Good_material record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table good_material
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Good_material record);
}