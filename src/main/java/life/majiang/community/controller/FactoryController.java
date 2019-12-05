package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.PolicyDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.FactoryMapper;
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
 * 2019/12/5 11:04
 */
@RestController
public class FactoryController {
    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "/api/workshop/factory",method = RequestMethod.GET)
    public List<Factory> get(){
        FactoryExample example = new FactoryExample();
        example.createCriteria();
        List<Factory> factories = factoryMapper.selectByExample(example);
        return factories;
    }

    @RequestMapping(value = "/api/workshop/factory",method = RequestMethod.POST)
    public Object post(@RequestBody Factory factory){
        factoryMapper.insert(factory);
        return CommonResult.success("创建成功！");
    }

    @RequestMapping(value = "/api/workshop/factory/{id}",method = RequestMethod.PUT)
    public Object put(@RequestBody Factory factory,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        factoryMapper.updateByPrimaryKey(factory);
        return factory;
    }

    @RequestMapping(value = "/api/workshop/factory/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id){
        factoryMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
