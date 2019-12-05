package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.DepartmentDTO;
import life.majiang.community.dto.PolicyDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.DepartmentMapper;
import life.majiang.community.mapper.FactoryMapper;
import life.majiang.community.mapper.PolicyMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaot
 * 2019/12/5 11:23
 */
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FactoryMapper factoryMapper;

    @RequestMapping(value = "/api/workshop/department",method = RequestMethod.GET)
    public List<DepartmentDTO> get(){
        DepartmentExample example = new DepartmentExample();
        example.createCriteria();
        List<Department> departments = departmentMapper.selectByExample(example);
        List<DepartmentDTO> departmentDTOS = new ArrayList<DepartmentDTO>();
        for (Department department : departments) {
            DepartmentDTO temp = new DepartmentDTO();
            BeanUtils.copyProperties(department,temp);
            temp.setFactory(factoryMapper.selectByPrimaryKey(department.getFactory()));
            departmentDTOS.add(temp);
        }
        return departmentDTOS;
    }

    @RequestMapping(value = "/api/workshop/department",method = RequestMethod.POST)
    public Object post(@RequestBody Department d,
                       HttpServletRequest request){
        departmentMapper.insert(d);
        return CommonResult.success("创建成功！");
    }

    @RequestMapping(value = "/api/workshop/department/{id}",method = RequestMethod.PUT)
    public Object put(@RequestBody Department department,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        departmentMapper.updateByPrimaryKey(department);
        return department;
    }

    @RequestMapping(value = "/api/workshop/department/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id){
        departmentMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
