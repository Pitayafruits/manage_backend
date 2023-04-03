package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色实体类
 */
@Data
@TableName("tb_role")
public class Role implements Serializable {

    //角色ID
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    //角色名称
    private String roleName;

    //角色描述
    private String roleDescription;

}
