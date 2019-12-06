package life.majiang.community.dto;

import life.majiang.community.model.Goods;
import life.majiang.community.model.Round;
import life.majiang.community.model.User;
import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/6 9:23
 */
@Data
public class ProducingDTO {
    private Integer id;
    private Integer state; //是否合格，0为处理中，1为合格，2为不合格
    private Round round;
    private Goods good;
    private Integer goodCount;
    private String produceDate;
    private Integer handler;
    private User user;
}
