package life.majiang.community.controller;

import life.majiang.community.dto.CustomDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.CustomMapper;
import life.majiang.community.mapper.PolicyMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Custom;
import life.majiang.community.model.CustomExample;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SaleCustomController {

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private PolicyMapper policyMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/sale/custom",method = RequestMethod.GET)
    public List<CustomDTO> get(HttpServletRequest request){
        CustomExample example = new CustomExample();
        example.createCriteria();
        List<Custom> customs = customMapper.selectByExample(example);
        List<CustomDTO> customDTO = new ArrayList<CustomDTO>();
        for (Custom custom : customs) {
            CustomDTO temp = new CustomDTO();
            BeanUtils.copyProperties(custom,temp);
            if(custom.getType()!=null){
                temp.setType(policyMapper.selectByPrimaryKey(custom.getType()));
            }
            temp.setUser(userMapper.selectByPrimaryKey(custom.getHandler()));
            customDTO.add(temp);
        }
        return customDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/sale/custom",method = RequestMethod.POST)
    public Object post(@RequestBody Custom custom,
                     HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        custom.setHandler(user.getId());
        customMapper.insert(custom);
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/api/sale/custom/{id}",method = RequestMethod.PUT)
    public Object put(@RequestBody Custom custom,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        custom.setHandler(user.getId());
        CustomExample example = new CustomExample();
        example.createCriteria()
                .andIdEqualTo(id);
        customMapper.updateByExampleSelective(custom, example);
        return custom;
    }

    @RequestMapping(value = "/api/sale/custom/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id){
        customMapper.deleteByPrimaryKey(id);
    }

}
