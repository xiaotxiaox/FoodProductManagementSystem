package life.majiang.community.controller;

import life.majiang.community.mapper.EmployeeMapper;
import life.majiang.community.mapper.PolicyMapper;
import life.majiang.community.model.Employee;
import life.majiang.community.model.EmployeeExample;
import life.majiang.community.model.Policy;
import life.majiang.community.model.PolicyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class EmployeeManagerController {
    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping(value = "/api/stuff/normal",method = RequestMethod.GET)
    public List<Employee> get(){
        EmployeeExample example = new EmployeeExample();
        example.createCriteria();
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;
    }

    @RequestMapping(value = "/api/stuff/normal",method = RequestMethod.POST)
    public void post(@RequestBody Employee employee){
        employeeMapper.insert(employee);
    }

    @RequestMapping(value = "/api/stuff/normal/{id}",method = RequestMethod.PUT)
    public Employee put(@RequestBody Employee employee,
                      @PathVariable(name = "id") long id){
        EmployeeExample example = new EmployeeExample();
        example.createCriteria()
                .andIdEqualTo(id);
        employeeMapper.updateByExampleSelective(employee, example);
        return employee;
    }

    @RequestMapping(value = "/api/stuff/normal/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") long id){
        employeeMapper.deleteByPrimaryKey(id);
    }
}
