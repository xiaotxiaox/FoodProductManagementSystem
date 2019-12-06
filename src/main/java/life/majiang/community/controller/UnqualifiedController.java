package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.ProducingDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.dto.RoundDTO;
import life.majiang.community.dto.UnqualifiedDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xiaot
 * 2019/12/6 11:06
 */
@RestController
public class UnqualifiedController {
    @Autowired
    private UnqualifiedMapper unqualifiedMapper;

    @Autowired
    private ProducingMapper producingMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/workshop/unqualified",method = RequestMethod.GET)
    public List<UnqualifiedDTO> get(HttpServletRequest request){
        UnqualifiedExample example = new UnqualifiedExample();
        example.createCriteria();
        List<Unqualified> list = unqualifiedMapper.selectByExample(example);
        List<UnqualifiedDTO> DTOs = new ArrayList<UnqualifiedDTO>();
        for (Unqualified i : list) {
            UnqualifiedDTO temp = new UnqualifiedDTO();
            BeanUtils.copyProperties(i,temp);
            if(i.getProducing()!=null){
                temp.setProducing(producingMapper.selectByPrimaryKey(i.getProducing()));
            }
            temp.setUser(userMapper.selectByPrimaryKey(i.getHandler()));
            DTOs.add(temp);
        }
        return DTOs;
    }

    @ResponseBody
    @RequestMapping(value = "/api/workshop/unqualified",method = RequestMethod.POST)
    public Object post(@RequestBody Unqualified unqualified,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        unqualified.setHandler(user.getId());
        unqualifiedMapper.insert(unqualified);
        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/workshop/unqualified/{id}",method = RequestMethod.PUT)
    public Object put(@RequestBody Unqualified unqualified,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        unqualified.setHandler(user.getId());
        unqualifiedMapper.updateByPrimaryKey(unqualified);
        return unqualified;
    }

    @RequestMapping(value = "/api/workshop/unqualified/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id){
        unqualifiedMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
