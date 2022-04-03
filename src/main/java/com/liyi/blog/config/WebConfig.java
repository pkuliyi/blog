package com.liyi.blog.config;

import com.liyi.blog.hander.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //跨域配置，不可设置为*，不安全, 前后端分离项目，可能域名不一致
        //允许本机8080端口访问后段，8080是前端的端口。
        //本地测试 端口不一致 也算跨域
        //registry.addMapping("/**").allowedOrigins("http://localhost:8080");
        //允许所有访问
        registry.addMapping("/**").allowedOrigins("*");
    }

    //设置拦截器，使拦截器生效！！！！
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截test接口，后续实际遇到需要兰藏的接口时，在配置为真正的兰截接口
        registry.addInterceptor(loginInterceptor).addPathPatterns("/test");
    }

}
