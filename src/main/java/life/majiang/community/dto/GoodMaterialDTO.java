package life.majiang.community.dto;

import life.majiang.community.model.Goods;
import life.majiang.community.model.Material;
import life.majiang.community.model.Materialtotal;
import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/6 15:25
 */
@Data
public class GoodMaterialDTO {
    private Integer id;
    private Goods good;
    private Materialtotal material;
    private Integer count;
    private String note;
}
