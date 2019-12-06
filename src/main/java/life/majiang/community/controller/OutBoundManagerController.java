package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.OutBoundDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.MaterialtotalMapper;
import life.majiang.community.mapper.OutboundMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Materialtotal;
import life.majiang.community.model.Outbound;
import life.majiang.community.model.OutboundExample;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class OutBoundManagerController {
    @Autowired
    private OutboundMapper outboundmapper;

    @Autowired
    private MaterialtotalMapper materialtotalmapper;

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
