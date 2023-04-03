package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实体类
 */

@Data
@TableName("tb_menu")
public class Menu implements Serializable {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer menuId;

    //父级菜单id
    private Integer parentId;

    //菜单名称
    private String menuLabel;

    //权限字段
    private String menuCode;

    //路由名称
    private String routeName;

    //路由地址
    private String routePath;

    //组件路径
    private String routeUrl;

    //菜单类型 0：目录 1：菜单 2：按钮
    private String type;

    //图标
    private String icon;

    //序号
    private Integer orderNum;

    //描述
    private String description;

    //上级菜单名称
    private String parentName;

    //不属于数据库表中的字段，需要排除
    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();

    //菜单是否展开
    @TableField(exist = false)
    private Boolean open;
}
