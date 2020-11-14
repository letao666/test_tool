package com.fh.config;

import com.fh.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    //登陆拦截
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }



    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //登录拦截，/**拦截所有
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
    }


}
