package life.majiang.community.dto;

import life.majiang.community.model.Materialtotal;
import life.majiang.community.model.User;
import lombok.Data;

@Data
public class MaterialDTO {
    private Integer id;
    private String name;
    private Integer num;
    private Integer state;
    private Integer person;
    private Integer total_price;
    private String time_apply;
    private String time_handle;//进货日期
    private String timeprotect;
    private Integer materialid;
    private Materialtotal materialtotal;
    private User user;

}
