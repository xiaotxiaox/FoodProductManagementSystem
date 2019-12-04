package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.OrderCollectionDTO;
import life.majiang.community.dto.PolicyDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.CustomMapper;
import life.majiang.community.mapper.OrderMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaot
 * 2019/12/4 14:21
 */
@RestController
public class OrderCollectionController {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomMapper customMapper;
    @RequestMapping(value = "/api/sale/order",method = RequestMethod.GET)
    public List<OrderCollectionDTO> get(){
        OrderExample example = new OrderExample();
        example.createCriteria();
        List<Order> orders = orderMapper.selectByExample(example);
        List<OrderCollectionDTO> orderCollectionDTOS = new ArrayList<OrderCollectionDTO>();
        for (Order order : orders) {
            OrderCollectionDTO temp = new OrderCollectionDTO();
            BeanUtils.copyProperties(order,temp);
            if(order.getCustom()!= null){
                temp.setCustom(customMapper.selectByPrimaryKey(order.getCustom()));
            }
            temp.setUser(userMapper.selectByPrimaryKey(order.getHandler()));
            orderCollectionDTOS.add(temp);
        }
        return orderCollectionDTOS;
    }

    @RequestMapping(value = "/api/sale/order",method = RequestMethod.POST)
    public Object post(@RequestBody Order order,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        order.setHandler(user.getId());
        orderMapper.insert(order);
        return CommonResult.success("创建成功！");
    }

    @RequestMapping(value = "/api/sale/order/{id}",method = RequestMethod.PUT)
    public Object put(@RequestBody Order order,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        order.setHandler(user.getId());
        orderMapper.updateByPrimaryKey(order);
        return order;
    }

    @RequestMapping(value = "/api/sale/order/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id){
        orderMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
