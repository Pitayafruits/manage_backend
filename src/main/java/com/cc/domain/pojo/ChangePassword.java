package com.cc.domain.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码实体类
 */
@Data
public class ChangePassword implements Serializable {

    private Integer userId;

    //旧密码
    private String oldPassword;

    //新密码
    private String newPassword;
}
