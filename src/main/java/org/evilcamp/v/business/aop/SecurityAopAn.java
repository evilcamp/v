package org.evilcamp.v.business.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 基于注解的aop配置
 */
@Component
@Aspect
@Order(1)
public class SecurityAopAn {
    /**
     * 扫描某包或子包下的方法.
     */
    @Pointcut("within(org.evilcamp.v.business..*)")
    public void inPackageAndSubPackage() {
        System.out.println("do inPackageAndSubPackage");
    }

    @Before("within(org.evilcamp.v.business..*)")
    public void doBefore(){
        System.out.println("UserAspect do before");
    }

    @After("within(org.evilcamp.v.business..*)")
    public void doAfter(){
        System.out.println("UserAspect do after");
    }

    @AfterThrowing("within(org.evilcamp.v.business..*)")
    public void doAfterThrow(){
        System.out.println("例外通知");
    }

    @Around("within(org.evilcamp.v.business..*)")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("aop-annotation:doAround start");
        long beginTime = System.currentTimeMillis();
        //执行该方法
        Object object = pjp.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("aop-annotation:doAround Method:<"+pjp.getSignature().getDeclaringTypeName()+"."+pjp.getSignature().getName()+">耗时"+(endTime-beginTime)+"ms");
        System.out.println("aop-annotation:doAround end");
        return object;
    }
}
