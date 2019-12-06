package life.majiang.community.controller;

import io.swagger.oas.models.security.SecurityScheme;
import life.majiang.api.CommonResult;
import life.majiang.community.dto.MaterialDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.dto.StateDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.InventoryMapper;
import life.majiang.community.mapper.MaterialMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Inventory;
import life.majiang.community.model.Material;
import life.majiang.community.model.MaterialExample;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MaterialManagerController {
    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InventoryMapper inventorymapper;

    @RequestMapping(value = "/api/material", method = RequestMethod.GET)
    public List<MaterialDTO> get() {
        MaterialExample example = new MaterialExample();
        example.createCriteria();
        List<Material> materials = materialMapper.selectByExample(example);
        List<MaterialDTO> materialDTO = new ArrayList<MaterialDTO>();
        for (Material material : materials) {
            MaterialDTO temp = new MaterialDTO();
            BeanUtils.copyProperties(material, temp);
            temp.setUser(userMapper.selectByPrimaryKey(material.getPerson()));
            materialDTO.add(temp);
        }
        return materialDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/material", method = RequestMethod.POST)
    public Object post(@RequestBody Material material,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        material.setPerson(user.getId());
        material.setTotalPrice(material.getPrice() * material.getNum());
        materialMapper.insert(material);
        Inventory inventory=new Inventory();
        inventory.setName(material.getName());
        inventory.setNum(material.getNum());
        inventory.setTimeprotect(material.getTimeprotect());
        inventorymapper.insert(inventory);
        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/material/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody Material material,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        material.setPerson(user.getId());
        MaterialExample example = new MaterialExample();
        example.createCriteria()
                .andIdEqualTo(id);
        materialMapper.updateByExampleSelective(material, example);
        return material;
    }


    @RequestMapping(value = "/api/material/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id) {
        materialMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/material/get", method = RequestMethod.GET)
    public StateDTO getNum() {
        MaterialExample example = new MaterialExample();
        example.createCriteria();
        List<Material> materials = materialMapper.selectByExample(example);
        StateDTO stateDTO = new StateDTO();
        List<MaterialDTO> materialDTO = new ArrayList<MaterialDTO>();
        for (Material material : materials) {
//            MaterialDTO temp = new MaterialDTO();
////            BeanUtils.copyProperties(material, temp);
////            materialDTO.add(temp);
            if (material.getState() == 1) {
                int t = stateDTO.getNumOne();
                t += 1;
                stateDTO.setNumOne(t);
            }
            if (material.getState() == 2) {
                int t = stateDTO.getNumTwo();
                t += 1;
                stateDTO.setNumTwo(t);
            }
            if (material.getState() == 3) {
                int t = stateDTO.getNumThree();
                t += 1;
                stateDTO.setNumThree(t);
            }
        }
        return stateDTO;
    }
}
