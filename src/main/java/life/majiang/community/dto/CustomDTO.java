package life.majiang.community.dto;

import life.majiang.community.model.Policy;
import life.majiang.community.model.User;
import lombok.Data;

@Data
public class CustomDTO {
    private Integer id;
    private String name;
    private String telephone;
    private Integer count;
    private int handler;
    private User user;
    private Policy type;
}
