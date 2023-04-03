package com.cc.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录结果实体类
 */
@Data
public class LoginResult implements Serializable {

    //用户ID
    private Integer userId;

    //token
    private String token;

    //token过期时间
    private Long lostTime;
}
