package com.itheima.controller;

import com.itheima.domian.Role;
import com.itheima.domian.UserInfo;
import com.itheima.service.UserService;
import com.itheima.utils.BCryptPasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
/*//    加密对象
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView testFindAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list = userService.findAll();
        mv.addObject("userList", list);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String testSave(UserInfo userInfo) throws Exception {

        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView testFindById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show1");
        return mv;
    }

    /**
     * 根据userid查询用户和可添加的角色
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {

        ModelAndView mv = new ModelAndView();
//        根据userid查询用户；
        UserInfo user = userService.findById(userId);
//        根据userid查询用户可以添加的角色；
        List<Role> RoleList=userService.findRoleById(userId);
        mv.addObject("user",user);
        mv.addObject("roleList",RoleList);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required=true) String userId,@RequestParam(name = "ids",required = true) String[ ] roleIds) throws Exception {
       userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
