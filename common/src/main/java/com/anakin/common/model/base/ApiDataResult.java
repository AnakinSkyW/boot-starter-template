package com.anakin.common.model.base;

import com.anakin.common.enums.CodeMsg;

/**
 * @description: 返回响应体
 * @author: Anakin
 * @date 23.1.16 16:48
 */
public class ApiDataResult<T> extends ApiResult {

    private final T data;

    public ApiDataResult(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiDataResult(CodeMsg codeMsg, T data) {
        this.success = codeMsg.getSuccess();
        this.code = codeMsg.getCode();
        this.message = codeMsg.getMessage();
        this.data = data;
    }
}
