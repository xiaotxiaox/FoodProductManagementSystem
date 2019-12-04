package life.majiang.community.controller;

import life.majiang.community.dto.LoginDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.dto.UserInfoDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/api/user/userInfo", method = RequestMethod.GET)
    public Object login(HttpServletRequest request) {
        // 给一个User 对象 ，通过 UserInfoservice 获得 一个 USERINFDTO
        User user = (User) request.getSession().getAttribute("user");
//        User user = userMapper.selectByPrimaryKey((long)1);
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        return userInfoService.getInfo(user);
    }
}
