package com.anakin.example.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description UserInfo DTO对象
 * @author Generator
 * @date 2021-03-09
 */
@Data
@Accessors(chain = true)
@ApiModel(value="UserInfo DTO对象", description="")
public class UserInfoDTO {


    @ApiModelProperty(value = "")
    private Long userId;

    @ApiModelProperty(value = "")
    private String loginId;

    @ApiModelProperty(value = "")
    private String mobileNo;

    @ApiModelProperty(value = "")
    private String email;

    @ApiModelProperty(value = "")
    private String loginPwd;

    @ApiModelProperty(value = "")
    private String payPwd;

    @ApiModelProperty(value = "")
    private String ssoType;

    @ApiModelProperty(value = "")
    private String ssoToken;

    @ApiModelProperty(value = "")
    private String nickName;

    @ApiModelProperty(value = "")
    private String avatar;

    @ApiModelProperty(value = "")
    private String openDate;

    @ApiModelProperty(value = "")
    private String openTime;

    @ApiModelProperty(value = "")
    private String loginTime;

    @ApiModelProperty(value = "0：启用；1：冻结；2：手机号失效；3：未激活")
    private String status;

    @ApiModelProperty(value = "1：APP；2：微信；3：网站；4第三方；5后台管理；6微信小程序")
    private String regChannel;

    @ApiModelProperty(value = "")
    private String unionid;

    @ApiModelProperty(value = "")
    private String openidanakinMp;

    @ApiModelProperty(value = "")
    private String openidApsaraMp;

    @ApiModelProperty(value = "IOS（appstore），Android（vivo、xiaomi、meizu、qutoutiao、huawei、zhihu、yingyongbao、360、weibo、baidu、jinritoutiao、oppo）")
    private String appChannel;


}
