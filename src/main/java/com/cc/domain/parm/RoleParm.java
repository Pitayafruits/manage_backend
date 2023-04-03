package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色查询实体
 */
@Data
public class RoleParm implements Serializable {
    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;

    //角色名称
    private String roleName;
}
