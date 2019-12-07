package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.CustomDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.dto.RoleDTO;
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
import java.util.Date;
import java.util.List;

/**
 * Created by xiaot
 * 2019/12/6 8:33
 */
@RestController
public class RoundController {
    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private ProductionplanMapper productionplanMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private Good_materialMapper good_materialMapper;

    @Autowired
    private RoundMaterialMapper roundMaterialMapper;

    @Autowired
    private UnqualifiedMapper unqualifiedMapper;

    @Autowired
    private ProducingMapper producingMapper;
    @RequestMapping(value = "/api/workshop/team/round/change",method = RequestMethod.PUT)
    public Object putRoundState(@RequestParam(value = "id",required = false) Integer id,
                                  @RequestParam(value = "state",required = false) Integer state,
                                  HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(id==null || state == null) return CommonResult.success("更新失败，请确认参数！");
        Round round = roundMapper.selectByPrimaryKey(id);
        round.setState(state);

        Producing producing = new Producing();
        producing.setRound(round.getId());
        producing.setState(0);
        producing.setGood(round.getGoods());
        producing.setGoodCount(round.getCount());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String dateNowStr = sdf.format(d);
        producing.setProduceDate(dateNowStr);
        producing.setHandler(user.getId());
        producingMapper.insert(producing);

        roundMapper.updateByPrimaryKey(round);
        return round;
    }

    @RequestMapping(value = "/api/workshop/round/productPlan",method = RequestMethod.GET)
    public List<RoundDTO> getProductionPlan(@RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request){
        List<RoundDTO> roundDTOS= get(request);
        List<RoundDTO> result = new ArrayList<RoundDTO>();
        for (RoundDTO roundDTO : roundDTOS) {
            if(roundDTO.getProductionPlan().getId()==id) result.add(roundDTO);
        }
        return  result;
    }

    @RequestMapping(value = "/api/workshop/round/productPlan",method = RequestMethod.POST)
    public Object postProductionPlan(@RequestParam(value = "id",required = false) Integer id,
                                     @RequestBody Round round,
                                     HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        round.setHandler(user.getId());
        round.setProductionPlan(id);
        round.setState(0);
        roundMapper.insert(round);

        RoundMaterial roundMaterial = new RoundMaterial();
        Integer sid = round.getId();
        Integer count = round.getCount();

        Good_materialExample example = new Good_materialExample();
        example.createCriteria().andGoodEqualTo(round.getGoods());
        List<Good_material> good_materials = good_materialMapper.selectByExample(example);

        for (Good_material good_material : good_materials) {
            roundMaterial.setRound(sid);
            roundMaterial.setCount(good_material.getCount() * count);
            roundMaterial.setMaterial(good_material.getMaterial());
            roundMaterial.setNote(good_material.getNote());
            roundMaterialMapper.insert(roundMaterial);
        }

        return CommonResult.success("创建成功！");
    }



    @ResponseBody
    @RequestMapping(value = "/api/workshop/round/productPlan/{id}",method = RequestMethod.PUT)
    public Object postProductionPlan(@RequestBody Round round,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        round.setHandler(user.getId());
        round.setProductionPlan(roundMapper.selectByPrimaryKey(round.getId()).getProductionPlan());
        roundMapper.updateByPrimaryKey(round);
        return round;
    }

    @RequestMapping(value = "/api/workshop/round/productPlan/{id}",method = RequestMethod.DELETE)
    public Object deleteProductionPlan(@PathVariable(name = "id") int id){
        roundMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }

    @RequestMapping(value = "/api/workshop/round/team",method = RequestMethod.GET)
    public List<RoundDTO> getTeam(@RequestParam(value = "id",required = false) Integer id,
                                            HttpServletRequest request){
        List<RoundDTO> roundDTOS= get(request);
        List<RoundDTO> result = new ArrayList<RoundDTO>();
        for (RoundDTO roundDTO : roundDTOS) {
            if(roundDTO.getTeam().getId()==id && roundDTO.getState()==0) result.add(roundDTO);
        }
        return  result;
    }

    @RequestMapping(value = "/api/workshop/round",method = RequestMethod.GET)
    public List<RoundDTO> get(HttpServletRequest request){
        RoundExample example = new RoundExample();
        example.createCriteria();
        List<Round> rounds = roundMapper.selectByExample(example);
        List<RoundDTO> roundDTOS = new ArrayList<RoundDTO>();
        for (Round round : rounds) {
            RoundDTO temp = new RoundDTO();
            BeanUtils.copyProperties(round,temp);
            if(round.getGoods()!=null){
                temp.setGoods(goodsMapper.selectByPrimaryKey(round.getGoods()));
            }
            if(round.getTeam()!=null){
                temp.setTeam(teamMapper.selectByPrimaryKey(round.getTeam()));
            }
            if(round.getProductionPlan()!=null){
                temp.setProductionPlan(productionplanMapper.selectByPrimaryKey(round.getProductionPlan()));
            }
            temp.setUser(userMapper.selectByPrimaryKey(round.getHandler()));
            roundDTOS.add(temp);
        }
        return roundDTOS;
    }

    @ResponseBody
    @RequestMapping(value = "/api/workshop/round",method = RequestMethod.POST)
    public Object post(@RequestBody Round round,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        round.setHandler(user.getId());
        round.setState(0);
        roundMapper.insert(round);

        RoundMaterial roundMaterial = new RoundMaterial();
        Integer id = round.getId();
        Integer count = round.getCount();

        Good_materialExample example = new Good_materialExample();
        example.createCriteria().andGoodEqualTo(round.getGoods());
        List<Good_material> good_materials = good_materialMapper.selectByExample(example);

        for (Good_material good_material : good_materials) {
            roundMaterial.setRound(id);
            roundMaterial.setCount(good_material.getCount() * count);
            roundMaterial.setMaterial(good_material.getMaterial());
            roundMaterial.setNote(good_material.getNote());
            roundMaterialMapper.insert(roundMaterial);
        }

        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/workshop/round/{id}",method = RequestMethod.PUT)
    public Object put(@RequestBody Round round,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        round.setHandler(user.getId());
        roundMapper.updateByPrimaryKey(round);
        return round;
    }

    @RequestMapping(value = "/api/workshop/round/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id){
        roundMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
