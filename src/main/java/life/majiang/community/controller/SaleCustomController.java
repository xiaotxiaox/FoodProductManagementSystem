package life.majiang.community.controller;

import life.majiang.community.dto.CustomDTO;
import life.majiang.community.mapper.CustomMapper;
import life.majiang.community.mapper.PolicyMapper;
import life.majiang.community.model.Custom;
import life.majiang.community.model.CustomExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaleCustomController {

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private PolicyMapper policyMapper;

    @RequestMapping(value = "/sale/custom",method = RequestMethod.GET)
    public List<CustomDTO> get(){
        CustomExample example = new CustomExample();
        example.createCriteria();
        List<Custom> customs = customMapper.selectByExample(example);
        List<CustomDTO> customDTO = null;
        for (Custom custom : customs) {
            CustomDTO temp = new CustomDTO();
            BeanUtils.copyProperties(custom,temp);
            if(custom.getType()!=null){
                temp.setPolicy(policyMapper.selectByPrimaryKey(custom.getType()));
            }
            customDTO.add(temp);
        }
        return customDTO;
    }

    @RequestMapping(value = "/sale/custom",method = RequestMethod.POST)
    public void post(@RequestBody Custom custom){
        customMapper.insert(custom);
    }

    @RequestMapping(value = "/sale/custom/{id}",method = RequestMethod.PUT)
    public Custom put(@RequestBody Custom custom,
                      @PathVariable(name = "id") int id){
        CustomExample example = new CustomExample();
        example.createCriteria()
                .andIdEqualTo(id);
        customMapper.updateByExampleSelective(custom, example);
        return custom;
    }

    @RequestMapping(value = "/sale/custom/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id){
        customMapper.deleteByPrimaryKey(id);
    }

}