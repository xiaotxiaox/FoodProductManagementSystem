package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.LoginDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.dto.UserInfoDTO;
//import life.majiang.community.dto.UserPasswordDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.RoleMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.mapper.User_userMapper;
import life.majiang.community.model.*;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import life.majiang.community.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

//    @Autowired
//    private UserPasswordDTO userPasswordDTO;

//    @RequestMapping(value = "/api/user/userInfo", method = RequestMethod.GET)
//    public Object login(HttpServletRequest request) {
//        // 给一个User 对象 ，通过 UserInfoservice 获得 一个 USERINFDTO
//        User user = (User) request.getSession().getAttribute("user");
////        User user = userMapper.selectByPrimaryKey((long)1);
//        if (user == null) {
//            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
//        }
//        return userInfoService.getInfo(user);
//    }

    @ResponseBody
    @RequestMapping(value = "/api/user/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user, HttpServletRequest request) {
//        UserExample userExample = new UserExample();
//        userExample.createCriteria()
//                .andIdEqualTo(user.getId());
//        List<User> users = userMapper.selectByExample(userExample);
//        if (users.size() == 0)
        userMapper.insert(user);
        return CommonResult.success("注册成功！");
    }


    @RequestMapping(value = "/api/user/get", method = RequestMethod.GET)
    public List<UserInfoDTO> get() {
        UserExample example = new UserExample();
        example.createCriteria();
        List<User> Users = userMapper.selectByExample(example);
        List<UserInfoDTO> UserDTO = new ArrayList<UserInfoDTO>();
        for (User User : Users) {
            UserInfoDTO temp = new UserInfoDTO();
            BeanUtils.copyProperties(User, temp);
            temp.setRole(roleMapper.selectByPrimaryKey(temp.getRoleId()));
            UserDTO.add(temp);
        }
        return UserDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/put/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody User users,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdEqualTo(id);
        userMapper.updateByExampleSelective(users, example);
        return users;
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/changePassword/{id}", method = RequestMethod.PUT)
    public Object putpassword(@RequestBody User users,
                      @PathVariable(name = "id") int id,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdEqualTo(id);
        userMapper.updateByExampleSelective(users, example);
        return users;
    }


}
