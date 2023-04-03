package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * 业主实体
 */
@Data
@TableName("liver_user")
public class Liver implements UserDetails,Serializable {

    //业主id
    @TableId(type = IdType.AUTO)
    private Integer liverId;

    //业主姓名
    private String liverName;

    //业主电话
    private String liverPhone;

    //业主性别 0:男 1:女
    private String liverSex;

    //登录账号
    private String username;

    //登录密码
    private String password;

    //账号状态 0:启用 1:禁用
    private String liverStatus;

    //角色id
    @TableField(exist = false)
    private Integer roleId;

    //房屋id
    @TableField(exist = false)
    private Integer houseId;

    //房屋序号
    @TableField(exist = false)
    private String houseNum;

    //房屋面积
    @TableField(exist = false)
    private String houseArea;

    //单元名称
    @TableField(exist = false)
    private String unitName;

    //楼宇名称
    @TableField(exist = false)
    private String buildingName;

    //车位id
    @TableField(exist = false)
    private Integer parkId;

    //车位名称
    @TableField(exist = false)
    private String parkName;

    //业主房屋状态
    @TableField(exist = false)
    private String liverHouseStatus;

    //业主车位状态
    @TableField(exist = false)
    private String liverParkingStatus;

    //下面的字段，属于spring security的UserDetails的字段
    //帐户是否过期(1 未过期，0已过期)
    private boolean isAccountNonExpired = true;
    //帐户是否被锁定(1 未锁定，0已锁定)
    private boolean isAccountNonLocked = true;
    //密码是否过期(1 未过期，0已过期)
    private boolean isCredentialsNonExpired = true;
    //帐户是否可用(1 可用，0 删除用户)
    private boolean isEnabled = true;
    //由于authorities不是数据库里面的字段，所以要排除他，不然mybatis-plus找不到该字段会报错
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;
}
