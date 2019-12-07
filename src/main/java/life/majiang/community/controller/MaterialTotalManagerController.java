package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.MaterialTotalDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.MaterialMapper;
import life.majiang.community.mapper.MaterialtotalMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MaterialTotalManagerController {
    @Autowired
    private MaterialtotalMapper materialtotalMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @RequestMapping(value = "/api/materialtotal", method = RequestMethod.GET)
    public List<MaterialTotalDTO> get() {
        MaterialtotalExample example = new MaterialtotalExample();
        example.createCriteria();
        List<Materialtotal> materialtotals = materialtotalMapper.selectByExample(example);
        List<MaterialTotalDTO> materialTotalDTO = new ArrayList<MaterialTotalDTO>();
        for (Materialtotal materialtotal : materialtotals) {
            MaterialTotalDTO temp = new MaterialTotalDTO();
            BeanUtils.copyProperties(materialtotal, temp);
            materialTotalDTO.add(temp);
        }
        return materialTotalDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/materialtotal", method = RequestMethod.POST)
    public Object post(@RequestBody Materialtotal materialtotal,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        materialtotalMapper.insert(materialtotal);
        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/materialtotal/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody Materialtotal materialtotal,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        MaterialtotalExample example = new MaterialtotalExample();
        example.createCriteria()
                .andIdEqualTo(id);
        materialtotalMapper.updateByExampleSelective(materialtotal, example);

        MaterialExample example1 = new MaterialExample();
        example1.createCriteria();
        List<Material> materials = materialMapper.selectByExample(example1);
        for (Material material:materials)
        {
            if (material.getMaterialid().equals(materialtotal.getId())) {
                material.setTotalPrice(material.getNum()*materialtotal.getPrice());
                materialMapper.updateByPrimaryKey(material);
            }
        }
        return materialtotal;
    }


    @RequestMapping(value = "/api/materialtotal/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id) {
        materialtotalMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
