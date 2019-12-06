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
import java.util.ArrayList;
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
        roundMapper.insert(round);

        RoundMaterial roundMaterial = new RoundMaterial();
        Integer id = round.getId();
        Integer count = round.getCount();

        Good_materialExample example = new Good_materialExample();
        example.createCriteria().andGoodEqualTo(round.getGoods());
        List<Good_material> good_materials = good_materialMapper.selectByExample(example);

        for (Good_material good_material : good_materials) {
            roundMaterial.setRound(id);
            roundMaterial.setCount(good_material.getCount());
            roundMaterial.setMaterial(good_material.getMaterial());
            roundMaterial.setNote(good_material.getNote());
            roundMaterialMapper.insert(roundMaterial);
        }

        return null;
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
