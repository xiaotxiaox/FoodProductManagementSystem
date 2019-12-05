package life.majiang.community.dto;

import life.majiang.community.model.Department;
import life.majiang.community.model.Factory;
import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/5 11:53
 */
@Data
public class TeamDTO {
    private Integer id;
    private Department department;
    private Factory factory;
    private String name;
    private String leader;
    private String leaderPhone;
}
