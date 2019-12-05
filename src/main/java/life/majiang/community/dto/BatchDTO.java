package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class BatchDTO {
    private Integer id;
    private String goodsname;
    private Integer goodsnum;
    private String timefinish;
    private String timeprotect;
    private Integer person;
    private User user;
}
