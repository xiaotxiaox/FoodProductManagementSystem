package life.majiang.community.dto;

import life.majiang.community.model.Producing;
import life.majiang.community.model.User;
import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/6 11:02
 */
@Data
public class UnqualifiedDTO {
    private Integer id;
    private Producing producing;
    private String way;
    private Integer handler;
    private User user;
    private Integer isHandle;
    private String note;
}
