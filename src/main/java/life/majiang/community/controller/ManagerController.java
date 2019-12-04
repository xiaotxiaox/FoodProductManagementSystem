package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.ManagerDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.ManagerMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ManagerController {
    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/stuff/manager", method = RequestMethod.GET)
    public List<ManagerDTO> get() {
        ManagerExample example = new ManagerExample();
        example.createCriteria();
        List<Manager> Managers = managerMapper.selectByExample(example);
        List<ManagerDTO> ManagerDTO = new ArrayList<ManagerDTO>();
        for (Manager manager : Managers) {
            ManagerDTO temp = new ManagerDTO();
            BeanUtils.copyProperties(manager, temp);
            temp.setUser(userMapper.selectByPrimaryKey(manager.getPerson()));
            ManagerDTO.add(temp);
        }
        return ManagerDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/stuff/manager", method = RequestMethod.POST)
    public Object post(@RequestBody Manager manager,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        manager.setPerson(user.getId());
        managerMapper.insert(manager);
        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/stuff/manager/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody Manager manager,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        manager.setPerson(user.getId());
        ManagerExample example = new ManagerExample();
        example.createCriteria()
                .andIdEqualTo(id);
        managerMapper.updateByExampleSelective(manager, example);
        return manager;
    }

    @RequestMapping(value = "/api/stuff/manager/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id) {
        managerMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");

    }
}
