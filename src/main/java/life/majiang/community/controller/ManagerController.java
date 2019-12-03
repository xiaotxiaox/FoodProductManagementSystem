package life.majiang.community.controller;

import life.majiang.community.mapper.EmployeeMapper;
import life.majiang.community.mapper.ManagerMapper;
import life.majiang.community.model.Employee;
import life.majiang.community.model.EmployeeExample;
import life.majiang.community.model.Manager;
import life.majiang.community.model.ManagerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class ManagerController {
    @Autowired
    private ManagerMapper managerMapper;

    @RequestMapping(value = "/api/stuff/manager",method = RequestMethod.GET)
    public List<Manager> get(){
        ManagerExample example = new ManagerExample();
        example.createCriteria();
        List<Manager> managers = managerMapper.selectByExample(example);
        return managers;
    }

    @RequestMapping(value = "/api/stuff/manager",method = RequestMethod.POST)
    public void post(@RequestBody Manager manager){
        managerMapper.insert(manager);
    }

    @RequestMapping(value = "//api/stuff/manager/{id}",method = RequestMethod.PUT)
    public Manager put(@RequestBody Manager manager,
                        @PathVariable(name = "id") long id){
        ManagerExample example = new ManagerExample();
        example.createCriteria()
                .andIdEqualTo(id);
        managerMapper.updateByExampleSelective(manager, example);
        return manager;
    }

    @RequestMapping(value = "/api/stuff/manager/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") long id){
        managerMapper.deleteByPrimaryKey(id);
    }
}
