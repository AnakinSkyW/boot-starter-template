package com.anakin.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description UserInfo实体对象
 * @author Generator
 * @date 2021-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("USER_INFO")
@ApiModel(value="UserInfo实体对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;

    @TableField("LOGIN_ID")
    private String loginId;

    @TableField("MOBILE_NO")
    private String mobileNo;

    @TableField("EMAIL")
    private String email;

    @TableField("LOGIN_PWD")
    private String loginPwd;

    @TableField("PAY_PWD")
    private String payPwd;

    @TableField("SSO_TYPE")
    private String ssoType;

    @TableField("SSO_TOKEN")
    private String ssoToken;

    @TableField("NICK_NAME")
    private String nickName;

    @TableField("AVATAR")
    private String avatar;

    @TableField("OPEN_DATE")
    private String openDate;

    @TableField("OPEN_TIME")
    private String openTime;

    @TableField("LOGIN_TIME")
    private String loginTime;

    @ApiModelProperty(value = "0：启用；1：冻结；2：手机号失效；3：未激活")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "1：APP；2：微信；3：网站；4第三方；5后台管理；6微信小程序")
    @TableField("REG_CHANNEL")
    private String regChannel;

    @TableField("UNIONID")
    private String unionid;

    @TableField("OPENID_anakin_MP")
    private String openidanakinMp;

    @TableField("OPENID_APSARA_MP")
    private String openidApsaraMp;

    @ApiModelProperty(value = "IOS（appstore），Android（vivo、xiaomi、meizu、qutoutiao、huawei、zhihu、yingyongbao、360、weibo、baidu、jinritoutiao、oppo）")
    @TableField("APP_CHANNEL")
    private String appChannel;


}
