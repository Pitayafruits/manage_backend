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
 * 用户实体类
 */
@Data
@TableName("tb_user")
public class User implements UserDetails,Serializable {

    //设置主键自增
    @TableId(type = IdType.AUTO)
    private Integer userId;

    //登录账户
    private String username;

    //用户真实姓名
    private String fullName;

    //密码
    private String password;

    //帐号状态（0正常 1停用)
    private String isStatus;

    //联系电话
    private String phoneNumber;

    //是否是管理员 0->不是 1->是
    private String isAdmin;

    //下面属于spring security的UserDetails的字段
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
