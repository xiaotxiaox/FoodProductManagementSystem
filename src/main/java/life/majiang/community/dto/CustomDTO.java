package life.majiang.community.dto;

import life.majiang.community.model.Policy;
import lombok.Data;

@Data
public class CustomDTO {
    private Integer id;
    private String name;
    private String telephone;
    private Integer count;
    private String handler;
    private Policy type;
}
