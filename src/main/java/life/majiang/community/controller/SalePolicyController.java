package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.PolicyDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.CustomMapper;
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
public class SalePolicyController {
    @Autowired
    private PolicyMapper policyMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/sale/policy",method = RequestMethod.GET)
    public List<Policy> get(){
        PolicyExample example = new PolicyExample();
        example.createCriteria();
        List<Policy> policies = policyMapper.selectByExample(example);
        List<PolicyDTO> policyDto = new ArrayList<PolicyDTO>();
        for (Policy policy : policies) {
            PolicyDTO temp = new PolicyDTO();
            BeanUtils.copyProperties(policy,temp);
            temp.setUser(userMapper.selectByPrimaryKey(policy.getHandler()));
            policyDto.add(temp);
        }
        return policies;
    }

    @RequestMapping(value = "/api/sale/policy",method = RequestMethod.POST)
    public Object post(@RequestBody Policy policy,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        policy.setHandler(user.getId());
        policyMapper.insert(policy);
        return CommonResult.success("创建成功！");
    }

    @RequestMapping(value = "/api/sale/policy/{id}",method = RequestMethod.PUT)
    public Object put(@RequestBody Policy policy,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        policy.setHandler(user.getId());
        policyMapper.updateByPrimaryKey(policy);
        return policy;
    }

    @RequestMapping(value = "/api/sale/policy/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id){
        policyMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
