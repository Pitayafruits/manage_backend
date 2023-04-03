package com.cc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.Menu;

import java.util.List;

/**
 * 菜单Service层
 */
public interface MenuService extends IService<Menu> {

    //父级菜单的查询
    List<Menu> getParentList();

    //查询菜单列表
    List<Menu> getMenuList();

    //删除菜单
    boolean deleteMenu(Integer menuId);

    //根据用户id查询权限
    List<Menu> getMenuByUserId(Integer userId);

    //根据业主id查询权限
    List<Menu> getMenuByLiverId(Integer liverId);

    //根据角色id查询权限
    List<Menu> getMenuByRoleId(Integer roleId);

}
