package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户实体类
 */
@Data
public class LoginParm implements Serializable {

    //用户名
    private String username;

    //密码
    private String password;

    //用户类型
    private String userType;
}
