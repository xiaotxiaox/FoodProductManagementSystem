package life.majiang.community.controller;

import life.majiang.api.CommonResult;
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

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public void login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      HttpServletRequest request) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
//        if (users.size() == 0)
//            return CommonResult.failed("用户名不存在");
//        else{
//            if (users.get(0).getPassword().equals(password)) {
//                request.getSession().setAttribute("user", users.get(0));
//                return CommonResult.success("user");
//            }
//            else return CommonResult.failed("密码错误");
//        }
        if (users.get(0).getPassword().equals(password)) {
            request.getSession().setAttribute("user", users.get(0));
        }
    }
}
