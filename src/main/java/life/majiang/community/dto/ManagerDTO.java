package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ManagerDTO {
    private Integer id;
    private Integer gender;
    private String timein;
    private String name;
    private Integer pay;
    private String phone;
    private Integer department;
    private BigInteger person;
    private User user;
}