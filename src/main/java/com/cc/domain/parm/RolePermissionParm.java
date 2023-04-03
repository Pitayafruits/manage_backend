package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 权限保存实体类
 */
@Data
public class RolePermissionParm implements Serializable {
    private Integer roleId;
    List<Integer> list;
}
