package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.ProducingDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.dto.RoundDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xiaot
 * 2019/12/6 9:26
 */
@RestController
public class ProducingController {
    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private ProducingMapper producingMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UnqualifiedMapper unqualifiedMapper;
    @RequestMapping(value = "/api/workshop/producing/stateChange",method = RequestMethod.POST)
    public Object getState0(@RequestParam(value = "id",required = false) Integer id,
                            @RequestParam(value = "state",required = false) Integer state,
                            HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(id==null || state == null) return CommonResult.success("更新失败，请确认参数！");
        Producing producing = producingMapper.selectByPrimaryKey(id);
        producing.setState(state);
        producingMapper.updateByPrimaryKey(producing);

        Unqualified unqualified = new Unqualified();
        unqualified.setState(state);
        unqualified.setProducing(producing.getId());
        unqualified.setHandler(user.getId());
        unqualified.setWay("");
        unqualified.setNote("");
        unqualified.setIsHandle(0);
        unqualifiedMapper.insert(unqualified);

        return producing;
    }



    @RequestMapping(value = "/api/workshop/producing/state0",method = RequestMethod.GET)
    public List<ProducingDTO> getState0(HttpServletRequest request){
        List<ProducingDTO> DTOs = get(request);
        List<ProducingDTO> part = new ArrayList<ProducingDTO>();
        for (ProducingDTO dto : DTOs) {
            if(dto.getState()==0) part.add(dto);
        }
        return part;
    }

    @RequestMapping(value = "/api/workshop/producing",method = RequestMethod.GET)
    public List<ProducingDTO> get(HttpServletRequest request){
        ProducingExample example = new ProducingExample();
        example.createCriteria();
        List<Producing> list = producingMapper.selectByExample(example);
        List<ProducingDTO> DTOs = new ArrayList<ProducingDTO>();
        for (Producing producing : list) {
            ProducingDTO temp = new ProducingDTO();
            BeanUtils.copyProperties(producing,temp);
            if(producing.getRound()!=null){
                Round round = roundMapper.selectByPrimaryKey(producing.getRound());
                temp.setRound(round);
                temp.setGood(goodsMapper.selectByPrimaryKey(producing.getGood()));
                temp.setGoodCount(producing.getGoodCount());
            }
            temp.setUser(userMapper.selectByPrimaryKey(producing.getHandler()));
            DTOs.add(temp);
        }
        return DTOs;
    }

    @ResponseBody
    @RequestMapping(value = "/api/workshop/producing",method = RequestMethod.POST)
    public Object post(@RequestBody Producing producing,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String dateNowStr = sdf.format(d);
        producing.setProduceDate(dateNowStr);
        producing.setHandler(user.getId());
        if(producing.getRound()!=null) {
            Round round = roundMapper.selectByPrimaryKey(producing.getRound());
            producing.setGood(round.getGoods());
            producing.setGoodCount(round.getCount());
        }
        producing.setState(0);
        producingMapper.insert(producing);
        return producing;
    }

    @ResponseBody
    @RequestMapping(value = "/api/workshop/producing/{id}",method = RequestMethod.PUT)
    public Object put(@RequestBody Producing producing,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        producing.setHandler(user.getId());
        producingMapper.updateByPrimaryKey(producing);
        return producing;
    }

    @RequestMapping(value = "/api/workshop/producing/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id){
        producingMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
