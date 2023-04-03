package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_user_role")
public class UserAndRole implements Serializable {

    //设置主键自增
    @TableId(type = IdType.AUTO)
    private Integer userRoleId;

    //角色id
    private Integer roleId;

    //用户id
    private Integer userId;
}
