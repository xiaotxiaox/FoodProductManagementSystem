package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.CustomDTO;
import life.majiang.community.dto.EmployeeDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.EmployeeMapper;
import life.majiang.community.mapper.PolicyMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeManagerController {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/stuff/normal", method = RequestMethod.GET)
    public List<EmployeeDTO> get() {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria();
        List<Employee> employees = employeeMapper.selectByExample(example);
        List<EmployeeDTO> employeeDTO = new ArrayList<EmployeeDTO>();
        for (Employee employee : employees) {
            EmployeeDTO temp = new EmployeeDTO();
            BeanUtils.copyProperties(employee, temp);
            temp.setUser(userMapper.selectByPrimaryKey(employee.getPerson()));
            employeeDTO.add(temp);
        }
        return employeeDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/stuff/normal", method = RequestMethod.POST)
    public Object post(@RequestBody Employee employee,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        employee.setPerson(user.getId());
        employeeMapper.insert(employee);
        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/stuff/normal/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody Employee employee,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        employee.setPerson(user.getId());
        EmployeeExample example = new EmployeeExample();
        example.createCriteria()
                .andIdEqualTo(id);
        employeeMapper.updateByExampleSelective(employee, example);
        return employee;
    }


    @RequestMapping(value = "/api/stuff/normal/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id) {
        employeeMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
