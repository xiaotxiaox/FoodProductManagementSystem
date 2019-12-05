package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.OrderCollectionDTO;
import life.majiang.community.dto.OrderStatisticsDTO;
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

    @RequestMapping(value = "/api/sale/order/statistics",method = RequestMethod.GET)
    public OrderStatisticsDTO statistics(){
        OrderStatisticsDTO orderStatisticsDTO = new OrderStatisticsDTO();
        OrderExample example = new OrderExample();
        example.createCriteria();
        orderStatisticsDTO.setAllNumber(orderMapper.countByExample(example));
        example.clear();
        example.createCriteria()
                .andStateEqualTo(1);
        orderStatisticsDTO.setState1Number(orderMapper.countByExample(example));
        example.clear();
        example.createCriteria()
                .andStateEqualTo(2);
        orderStatisticsDTO.setState2Number(orderMapper.countByExample(example));
        example.clear();
        example.createCriteria()
                .andStateEqualTo(3);
        orderStatisticsDTO.setState3Number(orderMapper.countByExample(example));
        example.clear();
        example.createCriteria()
                .andStateEqualTo(4);
        orderStatisticsDTO.setState4Number(orderMapper.countByExample(example));
        example.clear();
        example.createCriteria()
                .andStateEqualTo(5);
        orderStatisticsDTO.setState5Number(orderMapper.countByExample(example));
        example.clear();
        example.createCriteria()
                .andStateEqualTo(6);
        orderStatisticsDTO.setState6Number(orderMapper.countByExample(example));
        return orderStatisticsDTO;
    }

    @RequestMapping(value = "/api/sale/order/sell",method = RequestMethod.GET)
    public List<OrderCollectionDTO> getSell(){
        List<OrderCollectionDTO> all = getAll();
        List<OrderCollectionDTO> part = new ArrayList<OrderCollectionDTO>();
        for (OrderCollectionDTO orderCollectionDTO : all) {
            Integer state = orderCollectionDTO.getState();
            if(state == 1 || state ==2 || state ==4){
                part.add(orderCollectionDTO);
            }
        }
        return part;
    }

    @RequestMapping(value = "/api/sale/order/back",method = RequestMethod.GET)
    public List<OrderCollectionDTO> getBack(){
        List<OrderCollectionDTO> all = getAll();
        List<OrderCollectionDTO> part = new ArrayList<OrderCollectionDTO>();
        for (OrderCollectionDTO orderCollectionDTO : all) {
            Integer state = orderCollectionDTO.getState();
            if(state == 3 || state ==5 || state ==6){
                part.add(orderCollectionDTO);
            }
        }
        return part;
    }


    @RequestMapping(value = "/api/sale/order",method = RequestMethod.GET)
    public List<OrderCollectionDTO> getAll(){
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
