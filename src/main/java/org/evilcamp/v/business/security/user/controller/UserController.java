package org.evilcamp.v.business.security.user.controller;

import org.evilcamp.v.business.security.user.dao.hibernate.UserHibernateEntity;
import org.evilcamp.v.business.security.user.dao.hibernate.UserHibernateEntityDao;
import org.evilcamp.v.business.security.user.dto.UserDto;
import org.evilcamp.v.business.security.user.service.UserService;
import org.evilcamp.v.framework.common.FrameworkContext;
import org.evilcamp.v.framework.response.ReturnUtil;
import org.evilcamp.v.framework.utils.JsonUtil;
import org.evilcamp.v.framework.utils.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suxiaofei on 2016-05-23
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserHibernateEntityDao userHibernateEntityDao;

    @Autowired
    private UserService service;


    @RequestMapping("/add")
    @ResponseBody
    public String add(HttpServletRequest reqeust, HttpServletResponse response,
                        String userName, String password,String nickName){
        service.add(userName,password,nickName);
        return ReturnUtil.buildSuccessMsgStr();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(HttpServletRequest reqeust, HttpServletResponse response,
                        String id,String userName){
        if(!StringTool.hasText(id) && !StringTool.hasText(userName)){
            return ReturnUtil.buillFailedMsgStr();
        }
        if(StringTool.hasText(id)){
            service.delete(id);
            return ReturnUtil.buildSuccessMsgStr();
        }

        if(StringTool.hasText(userName)){
            UserDto dto = service.getByUserName(userName);
            if(dto!=null){
                service.delete(dto.getId());
                return ReturnUtil.buildSuccessMsgStr();
            }
        }
        return ReturnUtil.buillFailedMsgStr();
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(HttpServletRequest reqeust, HttpServletResponse response,
                         String id,String userName,String nickName){
        FrameworkContext.getRootConfigDir();
        Map<String,Object> queryParams = new HashMap<String,Object>();
        if(StringTool.hasText(userName)){
            queryParams.put("userName",userName);
        }
        if(StringTool.hasText(nickName)){
            queryParams.put("nickName",nickName);
        }
        if(queryParams.size()<1){
            return JsonUtil.getJsonString(service.queryALL());
        }else{
            return JsonUtil.getJsonString(service.queryByLike(queryParams));
        }

    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest reqeust, HttpServletResponse response,
                         String id,String userName, String password,String nickName){
        UserHibernateEntity entity = service.get(id);
        entity.setUserName(userName);
        entity.setPassword(password);
        entity.setNickName(nickName);
        service.update(entity);
        return ReturnUtil.buildSuccessMsgStr();
    }




}
