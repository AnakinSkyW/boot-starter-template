package com.anakin.example.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description UserInfo VO对象
 * @author Generator
 * @date 2021-03-09
 */
@Data
@Accessors(chain = true)
@ApiModel(value="UserInfo VO对象", description="")
public class UserInfoVO {

    @ApiModelProperty(value = "")
    private Long userId;

    @ApiModelProperty(value = "")
    private String mobileNo;

    @ApiModelProperty(value = "")
    private String email;

    @ApiModelProperty(value = "")
    private String nickName;

    @ApiModelProperty(value = "")
    private String avatar;
}
