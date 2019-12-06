package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.GoodMaterialDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.dto.UnqualifiedDTO;
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
 * 2019/12/6 11:37
 */
@RestController
public class GoodMaterialController {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private MaterialtotalMapper materialtotalMapper;

    @Autowired
    private Good_materialMapper good_materialMapper;

    @RequestMapping(value = "/api/productionPlan/goodMaterial",method = RequestMethod.GET)
    public List<GoodMaterialDTO> get(@RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request){
        if(id==null) return null;
        Good_materialExample example = new Good_materialExample();
        example.createCriteria().andGoodEqualTo(id);
        List<Good_material> list = good_materialMapper.selectByExample(example);
        List<GoodMaterialDTO> DTOs = new ArrayList<GoodMaterialDTO>();
        for (Good_material i : list) {
            GoodMaterialDTO temp = new GoodMaterialDTO();
            BeanUtils.copyProperties(i,temp);
            if(i.getMaterial()!=null){
                temp.setMaterial(materialtotalMapper.selectByPrimaryKey(i.getMaterial()));
            }
            DTOs.add(temp);
        }
        return DTOs;
    }

    @ResponseBody
    @RequestMapping(value = "/api/productionPlan/goodMaterial",method = RequestMethod.POST)
    public Object post(@RequestBody Good_material good_material,
                       @RequestParam(value = "id",required = false) Integer id,
                       HttpServletRequest request){
        good_material.setGood(id);
        good_materialMapper.insert(good_material);
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/api/productionPlan/goodMaterial/{id}",method = RequestMethod.PUT)
    public Object put(@RequestBody Good_material good_material,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        good_material.setGood(good_materialMapper.selectByPrimaryKey(good_material.getId()).getGood());
        good_materialMapper.updateByPrimaryKey(good_material);
        return good_material;
    }

    @RequestMapping(value = "/api/productionPlan/goodMaterial/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id){
        good_materialMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
