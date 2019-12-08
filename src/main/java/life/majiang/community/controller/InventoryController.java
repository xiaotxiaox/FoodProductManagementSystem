package life.majiang.community.controller;

import life.majiang.community.dto.InventoryDTO;
import life.majiang.community.dto.NumberOfTimeDTO;
import life.majiang.community.mapper.InventoryMapper;
import life.majiang.community.mapper.MaterialtotalMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Inventory;
import life.majiang.community.model.InventoryExample;
import life.majiang.community.model.Materialtotal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    private InventoryMapper inventorymapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MaterialtotalMapper materialtotalMapper;

    @RequestMapping(value = "/api/inventory/num", method = RequestMethod.GET)
    public NumberOfTimeDTO get() {
        InventoryExample example = new InventoryExample();
        example.createCriteria();
        List<Inventory> inventorys = inventorymapper.selectByExample(example);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String dateNowStr = sdf.format(d);//获取保质期
        NumberOfTimeDTO numberOfTimeDTO = new NumberOfTimeDTO();
        for (Inventory inventory : inventorys) {
            if (inventory.getTimeprotect().compareTo(dateNowStr) >= 0) {
                numberOfTimeDTO.setTimein(numberOfTimeDTO.getTimein() + 1);
            } else numberOfTimeDTO.setTimeout(numberOfTimeDTO.getTimeout() + 1);
        }
        return numberOfTimeDTO;
    }

    @RequestMapping(value = "/api/inventory/intime", method = RequestMethod.GET)
    public List<InventoryDTO> getInTime() {
        InventoryExample example = new InventoryExample();
        example.createCriteria();
        List<Inventory> inventorys = inventorymapper.selectByExample(example);
        List<InventoryDTO> inventoryDTO = new ArrayList<InventoryDTO>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String dateNowStr = sdf.format(d);//获取保质期
        for (Inventory inventory : inventorys) {
            InventoryDTO temp = new InventoryDTO();
            BeanUtils.copyProperties(inventory, temp);
            temp.setMaterialtotal(materialtotalMapper.selectByPrimaryKey(inventory.getMaterialid()));
            if (inventory.getTimeprotect().compareTo(dateNowStr) > 0) {
                inventoryDTO.add(temp);
            }
        }
        return inventoryDTO;
    }

    @RequestMapping(value = "/api/inventory/outtime", method = RequestMethod.GET)
    public List<InventoryDTO> getOutTime() {
        InventoryExample example = new InventoryExample();
        example.createCriteria();
        List<Inventory> inventorys = inventorymapper.selectByExample(example);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String dateNowStr = sdf.format(d);//获取保质期
        List<InventoryDTO> inventoryDTO = new ArrayList<InventoryDTO>();
        for (Inventory inventory : inventorys) {
            InventoryDTO temp = new InventoryDTO();
            BeanUtils.copyProperties(inventory, temp);
            temp.setMaterialtotal(materialtotalMapper.selectByPrimaryKey(inventory.getMaterialid()));
            if (inventory.getTimeprotect().compareTo(dateNowStr) < 0) {
                inventoryDTO.add(temp);
            }
        }
        return inventoryDTO;
    }
}
