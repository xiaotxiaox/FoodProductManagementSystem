package life.majiang.community.controller;

import com.zaxxer.hikari.util.FastList;
import life.majiang.community.dto.FinanceStatisticDTO;
import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaot
 * 2019/12/6 18:44
 */
@RestController
public class FinanceStatisticController {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @RequestMapping(value = "/api/finance/statistic",method = RequestMethod.GET)
    public List<FinanceStatisticDTO> get(){
        List<FinanceStatisticDTO> financeStatisticDTOS = new ArrayList<FinanceStatisticDTO>();

        //计算工资，每个月都一样
        int salary = 0;
        EmployeeExample example = new EmployeeExample();
        example.createCriteria();
        List<Employee> employees = employeeMapper.selectByExample(example);
        for (Employee employee : employees) {
            if(employee.getPay()!=null) salary += employee.getPay();
        }
        ManagerExample example2 = new ManagerExample();
        example2.createCriteria();
        List<Manager> managers = managerMapper.selectByExample(example2);
        for (Manager manager : managers) {
            if(manager.getPay()!=null) salary += manager.getPay();
        }

        //初始十二个月记录么，并记录月份和工资
        for (int i = 1; i <= 12; i++) {
            FinanceStatisticDTO financeStatisticDTO = new FinanceStatisticDTO();
            financeStatisticDTO.setMonth(String.format("%s月",i));
            financeStatisticDTO.setSalary_outcome(salary);
            financeStatisticDTOS.add(financeStatisticDTO);
        }

        //计算订单表中的订单收入和退款支出
        OrderExample example3 = new OrderExample();
        example3.createCriteria();
        List<Order> orders = orderMapper.selectByExample(example3);
        for (Order order : orders) {
            //计算订单收入，（有结款日期的才算，为支付金额的累计）
            if(order.getPaidMoney()!=null || order.getFinalDate()!=null) {
                String[] temp = order.getFinalDate().split("-");
                int month = Integer.parseInt(temp[1]);
                if(month>=1 && month<=12) {
                    FinanceStatisticDTO financeStatisticDTO = financeStatisticDTOS.get(month-1);
                    financeStatisticDTO.setOrder_income(financeStatisticDTO.getOrder_income().add( order.getPaidMoney()));
                }
            }
            //退款花费,(退款日期不为空，++退款金额)
            if(order.getBackDate()!=null || order.getBackMoney()!=null) {
                String[] temp2 = order.getBackDate().split("-");
                int month = Integer.parseInt(temp2[1]);
                if(month>=1 && month<=12) {
                    FinanceStatisticDTO financeStatisticDTO = financeStatisticDTOS.get(month-1);
                    financeStatisticDTO.setBack_outcome(financeStatisticDTO.getBack_outcome().add( order.getBackMoney()));
                }
            }
        }

        //入库原材料花费(time_handle不为空，++total_price)
        MaterialExample example4 = new MaterialExample();
        example4.createCriteria();
        List<Material> materials = materialMapper.selectByExample(example4);
        for (Material material : materials) {
            if(material.getTimeHandle()==null || material.getTotalPrice() == null) continue;
            String[] temp = material.getTimeHandle().split("-");
            int month = Integer.parseInt(temp[1]);
            if(month>=1 && month<=12) {
                FinanceStatisticDTO financeStatisticDTO = financeStatisticDTOS.get(month-1);
                financeStatisticDTO.setPurchase_outcome(financeStatisticDTO.getPurchase_outcome().add(new BigDecimal(material.getTotalPrice())));
            }
        }

        for (int i = 0; i < 12; i++) {
            FinanceStatisticDTO temp = financeStatisticDTOS.get(i);
            temp.setTotal_income(temp.getOrder_income());
            temp.setTotal_outcome(temp.getBack_outcome().add(temp.getPurchase_outcome().add(new BigDecimal(temp.getSalary_outcome()))));
            temp.setProfit(temp.getTotal_income().subtract(temp.getTotal_outcome()));
        }
        return financeStatisticDTOS;
    }
}
