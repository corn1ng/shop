package com.netease.shop;

import com.netease.shop.Entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class sessionAspect {

    private static final Logger logger = LoggerFactory.getLogger(sessionAspect.class);

    // 定义切点Pointcut
    @Pointcut("execution(* com.netease.shop.Controller.business.*.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        User user = (User) request.getSession().getAttribute("user");


        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();

        return result;
    }

}
