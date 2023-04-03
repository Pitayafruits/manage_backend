package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.Menu;
import com.cc.mapper.MenuMapper;
import com.cc.service.MenuService;
import com.cc.utils.MyTreeUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 菜单ServiceImpl层
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    //父级菜单的查询
    @Override
    public List<Menu> getParentList() {
        //查询目录和菜单
        String[] types = {"0","1"};
        //构造查询条件
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        List<String> typesList = Arrays.asList(types);
        wrapper.lambda().in(Menu::getType,typesList).orderByAsc(Menu::getOrderNum);
        List<Menu> menus = baseMapper.selectList(wrapper);
        //构造一个顶级树形数据
        Menu menu = new Menu();
        menu.setMenuId(0);
        menu.setParentId(-1);
        menu.setMenuLabel("顶级菜单");
        menus.add(menu);
        //构造树形数据
        List<Menu> menuList = MyTreeUtils.makeTree(menus, -1);
        return menuList;
    }

    //查询菜单列表
    @Override
    public List<Menu> getMenuList() {
        //构造查询条件
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByAsc(Menu::getOrderNum);
        List<Menu> menus = baseMapper.selectList(wrapper);
        //构造树形数据
        List<Menu> menuList = MyTreeUtils.makeTree(menus, 0);
        return menuList;
    }

    //删除菜单
    @Override
    public boolean deleteMenu(Integer menuId) {
        //构造查询条件
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Menu::getParentId,menuId);
        List<Menu> menuList = baseMapper.selectList(wrapper);
        if (menuList.size() > 0){
            return false;
        }
        return true;
    }

    //根据用户id查询权限
    @Override
    public List<Menu> getMenuByUserId(Integer userId) {
        return this.baseMapper.getMenuByUserId(userId);
    }

    //根据业主id查询权限
    @Override
    public List<Menu> getMenuByLiverId(Integer liverId) {
        return baseMapper.getMenuByLiverId(liverId);
    }

    //根据角色id查询权限
    @Override
    public List<Menu> getMenuByRoleId(Integer roleId) {
        return this.baseMapper.getMenuByRoleId(roleId);
    }

}
