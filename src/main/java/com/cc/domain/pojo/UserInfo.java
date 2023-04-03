package com.cc.domain.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 *登录获取用户信息实体类
 */

@Data
public class UserInfo implements Serializable {

    //用户id
    private Integer id;

    //用户名
    private String name;

    //头像
    private String avatar;

    //介绍
    private String introduction;

    //权限集合
    private Object[] roles;
}
