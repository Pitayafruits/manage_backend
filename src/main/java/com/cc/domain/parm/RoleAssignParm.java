package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 分配权限数据回显实体类
 */
@Data
public class RoleAssignParm implements Serializable {
    //用户id
    private Integer userId;
    //角色id
    private Integer roleId;
}
