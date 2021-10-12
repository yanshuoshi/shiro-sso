package com.example.user.controller;

import com.example.user.entity.User;
import com.example.user.service.UserService;
import com.example.user.vo.LoginVO;
import com.example.utils.RespMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description 统一登录层
 */
@Controller
//@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * @description 登录页面入口
     */
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * @description 首页入口
     */
    @RequestMapping("/index")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/loginOut")
    public String loginOut() {
        return "loginOut";
    }
    /**
     * @description shiro方式 登录操作
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespMessage doLogin(@RequestBody LoginVO loginVO) {
        RespMessage respMessage = new RespMessage();

        // 1、获取前端传的 用户名，密码
        String userName = loginVO.getUsername();    //request.getParameter("username");
        String password = loginVO.getPassword();    //request.getParameter("password");

        // 2、获取 UsernamePasswordToken, Subject
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 3、shiro登录，执行完 .login() 进入自定义realm
            currentUser.login(token);
        } catch (AuthenticationException e) {  //登录失败
            //ModelAndView mv = new ModelAndView("/login");
            respMessage.setRespCode("666");
            respMessage.setRespMsg("登录失败");
            return respMessage;
        }
        // session
        User user = userService.findByUserName(userName);
        Session session = currentUser.getSession();
        session.setAttribute("sessionUser", user.toString());

        respMessage.setRespCode("200");
        respMessage.setRespMsg("登录成功");
        return respMessage;
    }

    @GetMapping("loginOut1")
    @ResponseBody
    public RespMessage loginOut1() {
        SecurityUtils.getSubject().logout();
        RespMessage respMessage = new RespMessage();
        respMessage.setRespCode("200");
        respMessage.setRespMsg("登出成功");
        return respMessage;
    }

}
