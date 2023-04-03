package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 重置密码参数类
 */
@Data
public class ResetPassParm implements Serializable {

    //电话号码
    private String phone;

    //验证码
    private String code;

}
