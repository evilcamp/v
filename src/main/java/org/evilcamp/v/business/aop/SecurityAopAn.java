package org.evilcamp.v.business.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 基于注解的aop配置
 */
@Component
@Aspect
@Order(1)
public class SecurityAopAn {

    private static final Logger logger = LoggerFactory.getLogger(SecurityAopAn.class);
    /**
     * 扫描某包或子包下的方法.
     */
    @Pointcut("within(org.evilcamp.v.business..*)")
    public void inPackageAndSubPackage() {
        logger.info("do inPackageAndSubPackage");
    }

    @Before("within(org.evilcamp.v.business..*)")
    public void doBefore(){
        logger.info("UserAspect do before");
    }

    @After("within(org.evilcamp.v.business..*)")
    public void doAfter(){
        logger.info("UserAspect do after");
    }

    @AfterThrowing("within(org.evilcamp.v.business..*)")
    public void doAfterThrow(){
        logger.info("例外通知");
    }

    @Around("within(org.evilcamp.v.business..*)")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        logger.info("aop-annotation:doAround start");
        long beginTime = System.currentTimeMillis();
        //执行该方法
        Object object = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("aop-annotation:doAround Method:<"+pjp.getSignature().getDeclaringTypeName()+"."+pjp.getSignature().getName()+">耗时"+(endTime-beginTime)+"ms");
        logger.info("aop-annotation:doAround end");
        return object;
    }
}
