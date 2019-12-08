package life.majiang.community.dto;

import life.majiang.community.model.Materialtotal;
import lombok.Data;

@Data
public class InventoryDTO {
    private Integer id;
    private String name;
    private Integer num;
    private String timeprotect;
    private Materialtotal materialtotal;
}
