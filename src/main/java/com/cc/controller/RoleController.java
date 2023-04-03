package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Role;
import com.cc.domain.parm.RoleAssignParm;
import com.cc.domain.parm.RoleParm;
import com.cc.domain.parm.RolePermissionParm;
import com.cc.domain.vo.ResultVo;
import com.cc.domain.vo.RolePermissionVo;
import com.cc.service.RoleService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色Controller
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //分页查询角色列表
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:role:list')")
    public ResultVo list(RoleParm parm){
        IPage<Role> list = roleService.list(parm);
        return ResultUtils.success("查询成功!",list);
    }

    //新增角色
    @PostMapping
    @PreAuthorize("hasAuthority('user:role:add')")
    public ResultVo addRole(@RequestBody Role role){
        boolean flag = roleService.save(role);
        if (flag){
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("添加失败");
    }

    //编辑角色
    @PutMapping
    @PreAuthorize("hasAuthority('user:role:edit')")
    public ResultVo editRole(@RequestBody Role role){
        boolean flag = roleService.updateById(role);
        if (flag){
            return ResultUtils.success("修改成功!");
        }
        return ResultUtils.error("修改失败!");
    }

    //删除角色
    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('user:role:delete')")
    public ResultVo deleteRole(@PathVariable ("roleId") Integer roleId){
        boolean flag = roleService.removeRole(roleId);
        if (flag){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败，该角色已分配用户!");
    }

    //分配权限回显
    @PostMapping ("/getAssignTree")
    public ResultVo getAssignTree(@RequestBody RoleAssignParm roleAssignParm){
        RolePermissionVo assignTree = roleService.getAssignTree(roleAssignParm);
        if (assignTree != null){
            return ResultUtils.success("查询成功",assignTree);
        }
        return ResultUtils.error("为查询到数据!");
    }

    //分配权限保存
    @PostMapping("/saveAssignTree")
    @PreAuthorize("hasAuthority('user:role:allocation')")
    public ResultVo saveAssignTree(@RequestBody RolePermissionParm rolePermissionParm){
        roleService.saveAssign(rolePermissionParm);
        return ResultUtils.success("分配权限成功!");
    }

    //获取角色列表不分页->添加业主数据回显
    @GetMapping("/roleList")
    public ResultVo getRoleList(){
        List<Role> roleList = roleService.list();
        return ResultUtils.success("获取成功!",roleList);
    }
}
