package life.majiang.community.dto;

import life.majiang.community.model.Materialtotal;
import lombok.Data;

@Data
public class OutBoundDTO {
    private Integer id;
    private Integer num;
    private String timeout;
    private Integer materialid;
}
