package life.majiang.community.controller;

import life.majiang.community.mapper.CustomMapper;
import life.majiang.community.mapper.PolicyMapper;
import life.majiang.community.model.Custom;
import life.majiang.community.model.CustomExample;
import life.majiang.community.model.Policy;
import life.majiang.community.model.PolicyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class SalePolicyController {
    @Autowired
    private PolicyMapper policyMapper;

    @RequestMapping(value = "/sale/policy",method = RequestMethod.GET)
    public List<Policy> get(){
        PolicyExample example = new PolicyExample();
        example.createCriteria();
        List<Policy> policies = policyMapper.selectByExample(example);
        return policies;
    }

    @RequestMapping(value = "/sale/policy",method = RequestMethod.POST)
    public void post(@RequestBody Policy policy){
        policyMapper.insert(policy);
    }

    @RequestMapping(value = "/sale/policy/{id}",method = RequestMethod.PUT)
    public Policy put(@RequestBody Policy policy,
                      @PathVariable(name = "id") int id){
        PolicyExample example = new PolicyExample();
        example.createCriteria()
                .andIdEqualTo(id);
        policyMapper.updateByExampleSelective(policy, example);
        return policy;
    }

    @RequestMapping(value = "/sale/policy/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id){
        policyMapper.deleteByPrimaryKey(id);
    }
}
