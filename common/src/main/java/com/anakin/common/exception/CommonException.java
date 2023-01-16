package com.anakin.common.exception;


import com.anakin.common.enums.CodeMsg;

/**
 * @description: 自定义通用异常
 * @author: Anakin
 * @date 23.1.16 16:18
 */
public class CommonException extends RuntimeException {

    // 错误码
    private CodeMsg code;
    // 错误消息
    private String message;

    public CommonException(CodeMsg code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    public CommonException(CodeMsg codeMsg) {
        super(codeMsg.getMessage());
        this.code = codeMsg;
        this.message = codeMsg.getMessage();
    }

    public CodeMsg getCode() {
        return code;
    }

    public void setCode(CodeMsg code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
