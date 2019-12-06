package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.LoginDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/user/login", method = RequestMethod.POST)
    public Object login(@RequestBody LoginDTO loginDTO,
                      HttpServletRequest request) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andNameEqualTo(loginDTO.getUsername());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0)
            return CommonResult.failed("用户名不存在");

        if (users.get(0).getPassword().equals(loginDTO.getPassword())) {
            request.getSession().setAttribute("user", users.get(0));
            return CommonResult.success("登陆成功");
        }
        else{
            return CommonResult.failed("密码错误");
        }

    }
}
