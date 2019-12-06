package life.majiang.community.controller;

import io.swagger.oas.models.security.SecurityScheme;
import life.majiang.api.CommonResult;
import life.majiang.community.dto.OutBoundDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.InventoryMapper;
import life.majiang.community.mapper.MaterialtotalMapper;
import life.majiang.community.mapper.OutboundMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class OutBoundManagerController {
    @Autowired
    private OutboundMapper outboundmapper;

    @Autowired
    private MaterialtotalMapper materialtotalmapper;

    @Autowired
    private InventoryMapper inventorymapper;

    @RequestMapping(value = "/api/outbound", method = RequestMethod.GET)
    public List<OutBoundDTO> get() {
        OutboundExample example = new OutboundExample();
        example.createCriteria();
        List<Outbound> outbounds = outboundmapper.selectByExample(example);
        List<OutBoundDTO> outBoundDTO = new ArrayList<OutBoundDTO>();
        for (Outbound outbound : outbounds) {
            OutBoundDTO temp = new OutBoundDTO();
            BeanUtils.copyProperties(outbound, temp);
            outBoundDTO.add(temp);
        }
        return outBoundDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/outbound", method = RequestMethod.POST)
    public Object post(@RequestBody Outbound outbound,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        InventoryExample example = new InventoryExample();
        example.createCriteria().andMaterialidEqualTo(outbound.getMaterialid());
        List<Inventory> inventorys=inventorymapper.selectByExample(example);
        CompareInventory compareInventory=new CompareInventory();
        Collections.sort(inventorys,compareInventory);
        int cnt=outbound.getNum();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String dateNowStr = sdf.format(d);//获取保质期
        for (Inventory inventory : inventorys) {
            if (inventory.getNum()<cnt&&inventory.getTimeprotect().compareTo(dateNowStr)>0) {//数量不够并且保质期在范围之内
                cnt-=inventory.getNum();
                inventorymapper.deleteByPrimaryKey(inventory.getId());
            }
            else{
                if (inventory.getTimeprotect().compareTo(dateNowStr)>0){
                    inventory.setNum(inventory.getNum()-cnt);
                    inventorymapper.updateByPrimaryKey(inventory);
                }
            }
            if (inventory.getNum()==0){
                inventorymapper.deleteByPrimaryKey(inventory.getId());
            }
        }
        outboundmapper.insert(outbound);
        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/outbound/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody Outbound outbound,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        OutboundExample example = new OutboundExample();
        example.createCriteria()
                .andIdEqualTo(id);
        outboundmapper.updateByExampleSelective(outbound, example);
        return outbound;
    }


    @RequestMapping(value = "/api/outbound/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id) {
        outboundmapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
