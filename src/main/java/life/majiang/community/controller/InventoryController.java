package life.majiang.community.controller;

import life.majiang.community.dto.InventoryDTO;
import life.majiang.community.mapper.InventoryMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Inventory;
import life.majiang.community.model.InventoryExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    private InventoryMapper inventorymapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/inventory", method = RequestMethod.GET)
    public List<InventoryDTO> get() {
        InventoryExample example = new InventoryExample();
        example.createCriteria();
        List<Inventory> inventorys = inventorymapper.selectByExample(example);
        List<InventoryDTO> inventoryDTO = new ArrayList<InventoryDTO>();
        for (Inventory inventory : inventorys) {
            InventoryDTO temp = new InventoryDTO();
            BeanUtils.copyProperties(inventory, temp);
            inventoryDTO.add(temp);
        }
        return inventoryDTO;
    }
}
