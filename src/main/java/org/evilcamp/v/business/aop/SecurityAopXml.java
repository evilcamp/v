package org.evilcamp.v.business.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.evilcamp.v.business.common.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 基于配置文件的aop配置
 * 此aop目前仅扫描业务controller
 */
@Component
public class SecurityAopXml {

    @Autowired
    private SecurityUtil securityUtil;


    private static final Logger logger = LoggerFactory.getLogger(SecurityAopXml.class);

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        logger.debug("aop-xml:doAround start");
        logger.info("aop-xml:doAround Method:<"+pjp.getSignature().getDeclaringTypeName()+"."+pjp.getSignature().getName()+"> will do");

        // 在业务controller 中对登录退出以外的方法检查用户是否登录
        if(pjp.getSignature().getName()!="login"  && pjp.getSignature().getName() !="logout"){
            //访问目标方法的参数：
            Object[] args = pjp.getArgs();
            if (args != null && args.length > 0 && args[0].getClass() == String.class) {
                String userName = (String)args[0];
                logger.info("抓取用户名:"+userName);
                if(securityUtil.isLogin(userName)){
                    logger.info("用户"+userName+"已登录");
                }else{
                    logger.info("用户"+userName+"未登录");
                }
            }
        }



        Object result = null;


        try {
            long beginTime = System.currentTimeMillis();
            //执行该方法
            result = pjp.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("aop-xml:doAround Method:<"+pjp.getSignature().getDeclaringTypeName()+"."+pjp.getSignature().getName()+">耗时"+(endTime-beginTime)+"ms");
        } catch (Exception e) {
            logger.info("SecurityAopXml aroud捕获异常");
            e.printStackTrace();
        }
        logger.info("aop-xml:doAround end");
        return result;
    }
}
