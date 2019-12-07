package life.majiang.community.dto;

import life.majiang.community.model.Materialtotal;
import life.majiang.community.model.User;
import lombok.Data;

@Data
public class MaterialDTO {
    private Integer id;
    private Integer num;
    private String timeApply;
    private String timeHandle;
    private Integer state;
    private Integer totalPrice;
    private String timeprotect;
    private Materialtotal materialtotal;
    private User user;

}
