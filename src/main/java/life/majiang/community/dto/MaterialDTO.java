package life.majiang.community.dto;

import life.majiang.community.model.Materialtotal;
import life.majiang.community.model.User;
import lombok.Data;

@Data
public class MaterialDTO {
    private Integer id;
    private String name;
    private Integer num;
    private String timeApply;
    private String timeHandle;
    private Integer state;
    private Integer person;
    private Integer totalPrice;
    private Integer materialid;
    private String timeprotect;
    private Materialtotal materialtotal;
    private User user;

}
