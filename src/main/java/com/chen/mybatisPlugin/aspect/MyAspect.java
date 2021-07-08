package com.chen.mybatisPlugin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 陈俊宏
 */
@Component
@Aspect
@Slf4j
public class MyAspect {
    @Pointcut("@annotation(com.chen.mybatisPlugin.annotation.TimeCaculate)")
    public void pointCut(){
    }
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        log.info("执行用时{}",System.currentTimeMillis()-currentTimeMillis);
        return proceed;
    }

}
