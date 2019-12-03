package life.majiang.community.service;

import life.majiang.community.dto.UserInfoDTO;
import life.majiang.community.mapper.RoleMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaot
 * 2019/12/3 12:01
 */
@Service
public class UserInfoService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    public UserInfoDTO getInfo(User user){
        UserInfoDTO userInfoDto = new UserInfoDTO();
        BeanUtils.copyProperties(user,userInfoDto);
        userInfoDto.setRoleDTO(roleService.getRole(roleMapper.selectByPrimaryKey(user.getRole())));
        return userInfoDto;
    }
}
