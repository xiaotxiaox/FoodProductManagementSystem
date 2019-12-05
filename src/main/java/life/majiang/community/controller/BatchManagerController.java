package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.BatchDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.BatchMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Batch;
import life.majiang.community.model.BatchExample;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BatchManagerController {
    @Autowired
    private BatchMapper batchMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/batch", method = RequestMethod.GET)
    public List<BatchDTO> get() {
        BatchExample example = new BatchExample();
        example.createCriteria();
        List<Batch> batchs = batchMapper.selectByExample(example);
        List<BatchDTO> batchDTO = new ArrayList<BatchDTO>();
        for (Batch batch : batchs) {
            BatchDTO temp = new BatchDTO();
            BeanUtils.copyProperties(batch, temp);
            temp.setUser(userMapper.selectByPrimaryKey(batch.getPerson()));
            batchDTO.add(temp);
        }
        return batchDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/batch", method = RequestMethod.POST)
    public Object post(@RequestBody Batch batch,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        batch.setPerson(user.getId());
        batchMapper.insert(batch);
        return CommonResult.success("创建成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/api/batch/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody Batch batch,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        batch.setPerson(user.getId());
        BatchExample example = new BatchExample();
        example.createCriteria()
                .andIdEqualTo(id);
        batchMapper.updateByExampleSelective(batch, example);
        return batch;
    }


    @RequestMapping(value = "/api/batch/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable(name = "id") int id) {
        batchMapper.deleteByPrimaryKey(id);
        return CommonResult.success("删除成功！");
    }
}
