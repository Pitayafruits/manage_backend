package com.cc.domain.vo;

import com.cc.domain.pojo.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限回显结果实体
 */
@Data
public class RolePermissionVo {
    //当前登录系统用户的菜单数据
    List<Menu> listMenu = new ArrayList<>();
    //原来分配的菜单
    private Object[] checkList;
}
