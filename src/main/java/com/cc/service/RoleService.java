package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.Role;
import com.cc.domain.parm.RoleAssignParm;
import com.cc.domain.parm.RoleParm;
import com.cc.domain.parm.RolePermissionParm;
import com.cc.domain.vo.RolePermissionVo;

/**
 * 角色service层
 */
public interface RoleService extends IService<Role> {

    //分页查询角色列表
    IPage<Role> list(RoleParm parm);

    //删除角色
    boolean removeRole(Integer roleId);

    //分配权限回显
    RolePermissionVo getAssignTree(RoleAssignParm roleAssignParm);

    //分配权限保存
    void saveAssign(RolePermissionParm rolePermissionParm);
}
