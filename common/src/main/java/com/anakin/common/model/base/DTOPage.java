package com.anakin.common.model.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 带分页传输DTO
 * @author: Anakin
 * @date 23.1.16 16:43
 */
@Setter
@Getter
public abstract class DTOPage {

    protected Integer pageNum;
    protected Integer pageSize;

}
