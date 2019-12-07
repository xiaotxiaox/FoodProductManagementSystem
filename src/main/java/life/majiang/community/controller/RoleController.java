package life.majiang.community.controller;

import life.majiang.community.dto.RoleDTO;
import life.majiang.community.mapper.RoleMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Role;
import life.majiang.community.model.RoleExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleMapper RoleMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/stuff/role", method = RequestMethod.GET)
    public List<RoleDTO> get() {
        RoleExample example = new RoleExample();
        example.createCriteria();
        List<Role> Roles = RoleMapper.selectByExample(example);
        List<RoleDTO> RoleDTO = new ArrayList<RoleDTO>();
        for (Role Role : Roles) {
            RoleDTO temp = new RoleDTO();
            BeanUtils.copyProperties(Role, temp);
            RoleDTO.add(temp);
        }
        return RoleDTO;
    }
}
