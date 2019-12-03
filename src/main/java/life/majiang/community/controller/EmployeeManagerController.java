package life.majiang.community.controller;

import life.majiang.community.mapper.PolicyMapper;
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
    private PolicyMapper policyMapper;

    @RequestMapping(value = "/api/sale/policy",method = RequestMethod.GET)
    public List<Policy> get(){
        PolicyExample example = new PolicyExample();
        example.createCriteria();
        List<Policy> policies = policyMapper.selectByExample(example);
        return policies;
    }

    @RequestMapping(value = "/api/sale/policy",method = RequestMethod.POST)
    public void post(@RequestBody Policy policy){
        policyMapper.insert(policy);
    }

    @RequestMapping(value = "/api/sale/policy/{id}",method = RequestMethod.PUT)
    public Policy put(@RequestBody Policy policy,
                      @PathVariable(name = "id") int id){
        PolicyExample example = new PolicyExample();
        example.createCriteria()
                .andIdEqualTo(id);
        policyMapper.updateByExampleSelective(policy, example);
        return policy;
    }

    @RequestMapping(value = "/api/sale/policy/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id){
        policyMapper.deleteByPrimaryKey(id);
    }
}
