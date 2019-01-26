package com.netease.shop.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 检查每个到来的请求对应的session域中是否有登录标识
        Object uid = request.getSession().getAttribute("user");
        if (null == uid) {
            // 未登录，重定向到登录页
            response.sendRedirect("/login");
            return false;
        }
        return true;

    }
}
