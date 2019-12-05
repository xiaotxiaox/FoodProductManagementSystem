package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.BatchDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.dto.TimeFinishDTO;
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

    @ResponseBody
    @RequestMapping(value = "/api/batch/get", method = RequestMethod.GET)
    public List<TimeFinishDTO> getTime() {
        BatchExample example = new BatchExample();
        example.createCriteria();
        List<Batch> batchs = batchMapper.selectByExample(example);
        List<TimeFinishDTO> timeFinishDTOS = new ArrayList<TimeFinishDTO>();
        TimeFinishDTO timeFinishDTO = new TimeFinishDTO();
        for (Batch batch : batchs) {
            BatchDTO temp = new BatchDTO();
            BeanUtils.copyProperties(batch, temp);
            String[] strArr = temp.getTimefinish().split("\\-");
            int month = Integer.parseInt(strArr[1]);
            switch (month) {
                case 1:
                    int num1=timeFinishDTO.getNumJan();
                    num1+=1;
                    timeFinishDTO.setNumJan(num1);
                    break; //可选
                case 2:
                    int num2=timeFinishDTO.getNumFeb();
                    num2+=1;
                    timeFinishDTO.setNumFeb(num2);
                    break; //可选
                case 3:
                    int num3=timeFinishDTO.getNumMar();
                    num3+=1;
                    timeFinishDTO.setNumMar(num3);
                    break; //可选
                case 4:
                    int num4=timeFinishDTO.getNumApr();
                    num4+=1;
                    timeFinishDTO.setNumApr(num4);
                    break; //可选
                case 5:
                    int num5=timeFinishDTO.getNumMay();
                    num5+=1;
                    timeFinishDTO.setNumMay(num5);
                    break; //可选
                case 6:
                    int num6=timeFinishDTO.getNumJune();
                    num6+=1;
                    timeFinishDTO.setNumJune(num6);
                    break; //可选
                case 7:
                    int num7=timeFinishDTO.getNumJuly();
                    num7+=1;
                    timeFinishDTO.setNumJuly(num7);
                    break; //可选
                case 8:
                    int num8=timeFinishDTO.getNumAug();
                    num8+=1;
                    timeFinishDTO.setNumAug(num8);
                    break; //可选
                case 9:
                    int num9=timeFinishDTO.getNumSep();
                    num9+=1;
                    timeFinishDTO.setNumSep(num9);
                    break; //可选
                case 10:
                    int num10=timeFinishDTO.getNumOct();
                    num10+=1;
                    timeFinishDTO.setNumOct(num10);
                    break; //可选
                case 11:
                    int num11=timeFinishDTO.getNumNov();
                    num11+=1;
                    timeFinishDTO.setNumNov(num11);
                    break; //可选
                case 12:
                    int num12=timeFinishDTO.getNumDec();
                    num12+=1;
                    timeFinishDTO.setNumDec(num12);
                    break; //可选
                default: //可选
                    //语句
            }
        }
        return timeFinishDTOS;
    }
}
