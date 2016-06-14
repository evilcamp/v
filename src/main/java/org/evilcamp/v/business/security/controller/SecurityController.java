package org.evilcamp.v.business.security.controller;

import org.evilcamp.v.business.common.SecurityUtil;
import org.evilcamp.v.business.security.user.service.UserService;
import org.evilcamp.v.framework.response.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/security")

public class SecurityController {

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private UserService service;

    /**
     * 前端通过写入cookie,
     * 写入设备号和session
     * 有效期暂定一星期
     * 记住登录持续时间
     * ip,sessionId,设备号
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest reqeust, String userName, String password){
        boolean authenticateSuccess = securityUtil.authenticate(userName,password);
        if(authenticateSuccess){
            securityUtil.login(userName);
            return "success";
        }else{
            return "failed";
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(String userName){
        securityUtil.logout(userName);
        return "success";
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(String userName,String password,String nickName){
        if(service.add(userName,password,nickName)!=null){
            securityUtil.login(userName);
            return ReturnUtil.buildSuccessMsgStr();
        }else{
            return ReturnUtil.buillFailedMsgStr();
        }
    }
}
