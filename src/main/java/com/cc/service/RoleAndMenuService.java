package com.cc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.RoleAndMenu;

import java.util.List;

/**
 * 角色菜单Service
 */
public interface RoleAndMenuService extends IService<RoleAndMenu> {

    //保存权限
    void saveRoleMenu(Integer roleId, List<Integer> menuIds);
}
