package life.majiang.community.controller;

import life.majiang.community.dto.CustomDTO;
import life.majiang.community.dto.RoundMaterialDTO;
import life.majiang.community.mapper.MaterialMapper;
import life.majiang.community.mapper.MaterialtotalMapper;
import life.majiang.community.mapper.RoundMaterialMapper;
import life.majiang.community.model.RoundMaterial;
import life.majiang.community.model.RoundMaterialExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaot
 * 2019/12/7 10:57
 */
@RestController
public class RoundMaterialController {
    @Autowired
    private RoundMaterialMapper roundMaterialMapper;

    @Autowired
    private MaterialtotalMapper materialtotalMapper;
    @RequestMapping(value = "/api/workshop/round/material",method = RequestMethod.GET)
    public List<RoundMaterialDTO> get(@RequestParam(value = "id",required = false) Integer id){
        List<RoundMaterialDTO> result = new ArrayList<RoundMaterialDTO>();
        RoundMaterialExample example = new RoundMaterialExample();
        example.createCriteria().andRoundEqualTo(id);
        List<RoundMaterial> all = roundMaterialMapper.selectByExample(example);
        for (RoundMaterial roundMaterial : all) {
            RoundMaterialDTO roundMaterialDTO = new RoundMaterialDTO();
            BeanUtils.copyProperties(roundMaterial,roundMaterialDTO);
            roundMaterialDTO.setMaterial(materialtotalMapper.selectByPrimaryKey(roundMaterial.getMaterial()));
            result.add(roundMaterialDTO);
        }
        return result;
    }
}
