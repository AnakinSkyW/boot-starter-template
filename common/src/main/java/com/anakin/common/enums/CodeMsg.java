package com.anakin.common.enums;

/**
 * @description: 错误码
 * @author: Anakin
 * @date 23.1.16 16:18
 */
public enum CodeMsg {
    SUCCESS(true,0,"操作成功！"),
    INVALID_PARAM(false,10003,"非法参数！"),
    FAIL(false,11111,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");
    ;
    private boolean success;
    private int code;
    private String message;

    CodeMsg(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
