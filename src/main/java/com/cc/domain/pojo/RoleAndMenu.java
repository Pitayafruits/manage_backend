package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限实体类
 */
@Data
@TableName("tb_role_menu")
public class RoleAndMenu implements Serializable {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer roleMenuId;

    //角色id
    private Integer  roleId;

    //菜单id
    private Integer menuId;
}
