package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.GoodsDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.GoodsMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Goods;
import life.majiang.community.model.GoodsExample;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodsManagerController {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/goods", method = RequestMethod.GET)
    public List<GoodsDTO> get() {
        GoodsExample example = new GoodsExample();
        example.createCriteria();
        List<Goods> goodss = goodsMapper.selectByExample(example);
        List<GoodsDTO> goodsDTO = new ArrayList<GoodsDTO>();
        for (Goods goods : goodss) {
            GoodsDTO temp = new GoodsDTO();
            BeanUtils.copyProperties(goods, temp);
            goodsDTO.add(temp);
        }
        return goodsDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/goods", method = RequestMethod.POST)
    public Object post(@RequestBody Goods goods,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        goodsMapper.insert(goods);
        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/goods/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody Goods goods,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        GoodsExample example = new GoodsExample();
        example.createCriteria()
                .andIdEqualTo(id);
        goodsMapper.updateByExampleSelective(goods, example);
        return goods;
    }


    @RequestMapping(value = "/api/goods/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id) {
        goodsMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
