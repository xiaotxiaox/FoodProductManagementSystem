package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.ProductionplanDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.dto.StateDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.GoodsMapper;
import life.majiang.community.mapper.ProductionplanMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Productionplan;
import life.majiang.community.model.ProductionplanExample;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductionPlanController {
    @Autowired
    private ProductionplanMapper productionplanMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @RequestMapping(value = "/api/productionplan", method = RequestMethod.GET)
    public List<ProductionplanDTO> get() {
        ProductionplanExample example = new ProductionplanExample();
        example.createCriteria();
        List<Productionplan> productionplans = productionplanMapper.selectByExample(example);
        List<ProductionplanDTO> productionplanDTO = new ArrayList<ProductionplanDTO>();
        for (Productionplan productionplan : productionplans) {
            ProductionplanDTO temp = new ProductionplanDTO();
            temp.setUser(userMapper.selectByPrimaryKey(productionplan.getPerson()));
            temp.setGoods(goodsMapper.selectByPrimaryKey(productionplan.getGoodsid()));
            BeanUtils.copyProperties(productionplan, temp);
            productionplanDTO.add(temp);
        }
        return productionplanDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/productionplan", method = RequestMethod.POST)
    public Object post(@RequestBody Productionplan productionplan,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        productionplan.setPerson(user.getId());
        productionplanMapper.insert(productionplan);
        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/productionplan/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody Productionplan productionplan,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
//        productionplan.setPerson(user.getId());
        ProductionplanExample example = new ProductionplanExample();
        example.createCriteria()
                .andIdEqualTo(id);
        productionplanMapper.updateByExampleSelective(productionplan, example);
        return productionplan;
    }


    @RequestMapping(value = "/api/productionplan/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id) {
        productionplanMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/productionplan/get", method = RequestMethod.GET)
    public StateDTO getNum() {
        ProductionplanExample example = new ProductionplanExample();
        example.createCriteria();
        List<Productionplan> productionplans = productionplanMapper.selectByExample(example);
        StateDTO stateDTO = new StateDTO();
        for (Productionplan productionplan : productionplans) {
            if (productionplan.getState() == 1) {
                int t = stateDTO.getNumOne();
                t += 1;
                stateDTO.setNumOne(t);
            }
            if (productionplan.getState() == 2) {
                int t = stateDTO.getNumTwo();
                t += 1;
                stateDTO.setNumTwo(t);
            }
            if (productionplan.getState() == 3) {
                int t = stateDTO.getNumThree();
                t += 1;
                stateDTO.setNumThree(t);
            }
        }
        return stateDTO;
    }
}
