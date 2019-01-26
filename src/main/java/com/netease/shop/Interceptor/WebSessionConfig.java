package com.netease.shop.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebSessionConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/s/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/c");
        loginRegistry.excludePathPatterns("/login");
        loginRegistry.excludePathPatterns("/loginout");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/css/*.css");
        loginRegistry.excludePathPatterns("/js/*.js");
        loginRegistry.excludePathPatterns("/image/*.png");
        loginRegistry.excludePathPatterns("/image/*.jpeg");
        loginRegistry.excludePathPatterns("/image/*.jpg");

    }


}
