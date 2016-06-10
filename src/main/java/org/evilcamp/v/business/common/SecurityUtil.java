package org.evilcamp.v.business.common;

import org.evilcamp.v.business.db.hibernate.UserHibernateEntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityUtil {

    @Autowired
    private UserHibernateEntityDao userHibernateEntityDao;


    private static Map<String,Date> loginInfo = new HashMap<String,Date>();

    /**
     * 每次登录默认有效时间,单位为分钟.默认为10分钟
     */
    private static int defaultValidTime = 10*60*1000;



    public boolean isLogin(String userName){
        Date loginTime = loginInfo.get(userName);
        if(loginTime==null){
            return false;
        }
        long now = System.currentTimeMillis();
        long exptime = loginTime.getTime()+defaultValidTime;
        if(now< exptime){
            return true;
        }else{
            loginInfo.remove(userName);
            return false;
        }
    }

    /**
     * 认证,检查用户是否为合法用户
     * @param userName
     * @param password
     * @return
     */
    public boolean  authenticate(String userName,String password){
        return userHibernateEntityDao.isValidUser(userName,password);
    }

    /**
     * 授权,用户是否有操作权限
     * @param userName
     * @param path
     * @return
     */
    public boolean  authorize(String userName,String path){
        return true;
    }


    public boolean login(String userName){
        loginInfo.put(userName,new Date());
        return true;
    }

    public boolean logout(String userName){
        loginInfo.remove(userName);
        return true;
    }

}
