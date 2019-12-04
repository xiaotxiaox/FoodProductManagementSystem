package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/4 15:15
 */
@Data
public class PolicyDTO {
    private Integer id;
    private String type;
    private Integer rate;
    private Integer handler;
    private Integer discount;
    private User user;
}
