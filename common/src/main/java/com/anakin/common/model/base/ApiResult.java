package com.anakin.common.model.base;

import com.anakin.common.enums.CodeMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 接口返回Json类型数据实体
 * @author: Anakin
 * @date 23.1.16 16:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResult {

    protected boolean success;
    protected int code;
    protected String message;

    public ApiResult(CodeMsg codeMsg) {
        this.success = codeMsg.getSuccess();
        this.code = codeMsg.getCode();
        this.message = codeMsg.getMessage();
    }
}
