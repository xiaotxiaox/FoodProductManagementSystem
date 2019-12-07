package life.majiang.community.dto;

import life.majiang.community.model.Goods;
import life.majiang.community.model.Productionplan;
import life.majiang.community.model.Team;
import life.majiang.community.model.User;
import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/6 8:38
 */
@Data
public class RoundDTO {
    private Integer id;
    private String name;
    private Goods goods;
    private Integer count;
    private Productionplan productionPlan;
    private Team team;
    private int handler;
    private User user;
    private String introduction;
    private Integer state;
}
