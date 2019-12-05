package life.majiang.community.dto;

import life.majiang.community.model.Factory;
import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/5 11:23
 */
@Data
public class DepartmentDTO {
    private Integer id;
    private String name;
    private Factory factory;
    private String leader;
    private String leaderNumber;
}
