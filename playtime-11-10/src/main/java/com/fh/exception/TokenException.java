package com.fh.exception;

/**
 * 自己定义一个异常继承运行时异常
 * 在捕获异常里自己进行捕获，在前端获取到该状态码
 */
public class TokenException extends RuntimeException {
    public TokenException(String message) {
        super(message);
    }
}
