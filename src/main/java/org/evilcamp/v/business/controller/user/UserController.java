package org.evilcamp.v.business.controller.user;

import org.evilcamp.v.business.common.SecurityUtil;
import org.evilcamp.v.business.db.hibernate.UserHibernateEntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by suxiaofei on 2016-05-23
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserHibernateEntityDao userHibernateEntityDao;




    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping("/test")
    @ResponseBody
    public String showMe(String name){
        return name+" is welcome!";
    }


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
    public String login(HttpServletRequest reqeust,String userName, String password){
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
}
