package com.fh.exception;

import com.fh.common.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一捕获异常
 * param: Exception 总异常
 * return: 打印错误信息
 */
@RestControllerAdvice
public class GlobleHandlerException {

    //捕获登录异常
    @ExceptionHandler(AjaxException.class)
    public ServerResponse handlerAjaxException(AjaxException e){
        return ServerResponse.loginError();
    }

    //捕获token异常
    @ExceptionHandler(TokenException.class)
    public ServerResponse handlerAjaxException(TokenException e){
        return ServerResponse.tokenError(e.getMessage());
    }

    //捕获总异常
    @ExceptionHandler(Exception.class)
    public ServerResponse handlerException(Exception e){
        e.printStackTrace();
        return ServerResponse.error(e.getMessage());
    }
}
