package com.fh.interceptor;

import com.fh.annotatio.Ignore;
import com.fh.common.SystemConst;
import com.fh.exception.AjaxException;
import com.fh.model.User;
import com.fh.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断注解是否需要拦截--加上@Ignore注解则不被拦截
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Ignore ignore = handlerMethod.getMethodAnnotation(Ignore.class);
        if(ignore != null){
            return true;
        }

        User user = (User) request.getSession().getAttribute(SystemConst.SESSION_USER);
        if(user == null){
            return false;
        }

      /*  //获取前台存入头信息中的token，进行判空--加密后的用户信息
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){
            throw new AjaxException();
        }

        //验证用户信息--解密后的用户信息
        String verify = JwtUtil.verify(token);
        if(StringUtils.isBlank(verify)){
            throw new AjaxException();
        }*/


        return true;
    }
}
