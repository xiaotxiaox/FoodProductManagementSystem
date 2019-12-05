package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class MaterialDTO {
    private int id;
    private String name;
    private int num;
    private int price;
    private int state;
    private int person;
    private String time_apply;
    private String time_handle;
    private User user;

}
