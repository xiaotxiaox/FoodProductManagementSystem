package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by xiaot
 * 2019/12/3 11:59
 */
@Data
public class UserInfoDTO {
    private Long id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
    private String password;
    private Integer role;
    private RoleDTO roleDTO;
}
