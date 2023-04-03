package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.RoleAndMenu;
import com.cc.mapper.RoleAndMenuMapper;
import com.cc.service.RoleAndMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色菜单实现类
 */
@Service
public class RoleAndMenuServiceImpl extends ServiceImpl<RoleAndMenuMapper, RoleAndMenu> implements RoleAndMenuService {

    //保存权限
    @Override
    public void saveRoleMenu(Integer roleId, List<Integer> menuIds) {
        this.baseMapper.saveRoleMenu(roleId, menuIds);
    }
}
