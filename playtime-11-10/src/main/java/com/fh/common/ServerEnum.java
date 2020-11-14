package com.fh.common;

/**
 * 使用枚举封装状态码
 */
public enum ServerEnum {
    SUCCESS(200,"操作成功"),
    ERROR(1001,"操作失败"),
    LOGIN_ERROR(1002,"登录超时！"),
    TOKEN_ERROR(1003,"幂等性验证失败！"),
    ;

    private Integer code;
    private String msg;

    ServerEnum() {
    }

    ServerEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
