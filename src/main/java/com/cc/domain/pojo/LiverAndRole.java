package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 业主角色实体
 */
@Data
@TableName("liver_role")
public class LiverAndRole implements Serializable {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer liverRoleId;

    //业主id
    private Integer liverId;

    //角色id
    private Integer roleId;
}
