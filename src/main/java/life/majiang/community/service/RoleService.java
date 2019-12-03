package life.majiang.community.service;

import life.majiang.community.dto.RoleDTO;
import life.majiang.community.mapper.PermissionsMapper;
import life.majiang.community.mapper.Role_permissionsMapper;
import life.majiang.community.model.Permissions;
import life.majiang.community.model.Role;
import life.majiang.community.model.Role_permissions;
import life.majiang.community.model.Role_permissionsExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaot
 * 11:37
 */
@Service
public class RoleService {
    @Autowired
    private Role_permissionsMapper role_permissionsMapper;
    @Autowired
    private PermissionsMapper permissionsMapper;

    public RoleDTO getRole(Role role){
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role,roleDTO);
        Role_permissionsExample example = new Role_permissionsExample();
        example.createCriteria()
                .andRoleEqualTo(role.getId());
        List<Role_permissions> role_permissions = role_permissionsMapper.selectByExample(example);
        List<Permissions> permissions = null;
        for (Role_permissions rolePermission : role_permissions) {
            assert permissions != null;
            permissions.add(permissionsMapper.selectByPrimaryKey(rolePermission.getId()));
        }
        roleDTO.setPermissions(permissions);
        return roleDTO;
    }
}
