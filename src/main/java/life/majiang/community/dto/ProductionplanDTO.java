package life.majiang.community.dto;

import life.majiang.community.model.Goods;
import life.majiang.community.model.User;
import lombok.Data;

@Data
public class ProductionplanDTO {
    private Integer id;
    private String timecreate;
    private Integer neednum;
    private String timelastest;
    private Integer state;
    private String timeproduce;
    private Integer person;
    private Integer goodsid;
    private User user;
    private Goods goods;

}
