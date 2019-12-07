package life.majiang.community.dto;

import life.majiang.community.model.Material;
import life.majiang.community.model.Materialtotal;
import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/7 10:58
 */
@Data
public class RoundMaterialDTO {
    private Integer id;
    private Materialtotal material;
    private Integer count;
    private String note;
}
