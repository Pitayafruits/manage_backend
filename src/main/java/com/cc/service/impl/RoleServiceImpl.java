package com.cc.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.*;
import com.cc.domain.parm.RoleAssignParm;
import com.cc.domain.parm.RoleParm;
import com.cc.domain.parm.RolePermissionParm;
import com.cc.domain.vo.RolePermissionVo;
import com.cc.mapper.RoleMapper;
import com.cc.service.*;
import com.cc.utils.MyTreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 角色serviceImpl层
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserAndRoleService userAndRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleAndMenuService roleAndMenuService;

    @Autowired
    private LiverAndRoleService liverAndRoleService;

    //分页查询角色列表
    @Override
    public IPage<Role> list(RoleParm parm) {
        //构造查询条件
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(parm.getRoleName())) {
            wrapper.lambda().like(Role::getRoleName, parm.getRoleName());
        }
        //构造分页对象
        IPage<Role> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurrentPage());
        return baseMapper.selectPage(page, wrapper);
    }

    //删除角色
    @Override
    @Transactional
    public boolean removeRole(Integer roleId) {
        //初始三角色不可删除
        if(roleId == 2){
            return false;
        }
        if(roleId == 16){
            return false;
        }
        if(roleId == 19){
            return false;
        }
        //根据角色id查询是否有关联用户id 构造查询条件
        QueryWrapper<UserAndRole> userAndRoleWrapper = new QueryWrapper<>();
        QueryWrapper<LiverAndRole> liverAndRoleWrapper = new QueryWrapper<>();
        if (roleId != null) {
            userAndRoleWrapper.lambda().eq(UserAndRole::getRoleId, roleId);
        }
        if (roleId != null){
            liverAndRoleWrapper.lambda().eq(LiverAndRole::getRoleId,roleId);
        }
        BaseMapper<UserAndRole> userAndRoleMapper = userAndRoleService.getBaseMapper();
        Long userCount = userAndRoleMapper.selectCount(userAndRoleWrapper);
        BaseMapper<LiverAndRole> liverAndRoleMapper = liverAndRoleService.getBaseMapper();
        Long liverCount = liverAndRoleMapper.selectCount(liverAndRoleWrapper);
        //查询结果小于等于0说明该角色未分配用户可以删除
        if (userCount <= 0 && liverCount <= 0) {
                //清除角色表和角色权限关系表的数据
                this.baseMapper.deleteById(roleId);
                //构造角色权限条件
                QueryWrapper<RoleAndMenu> roleAndMenuWrapper = new QueryWrapper<>();
                roleAndMenuWrapper.lambda().eq(RoleAndMenu::getRoleId,roleId);
                roleAndMenuService.remove(roleAndMenuWrapper);
                //清除业主角色关系
                liverAndRoleService.remove(liverAndRoleWrapper);
                return true;
            }
        return false;
    }

    //分配权限回显
    @Override
    public RolePermissionVo getAssignTree(RoleAssignParm roleAssignParm) {
        //首先查询当前用户的信息
        User user = userService.getById(roleAssignParm.getUserId());
        //判断用户是否是超级管理员
        List<Menu> menusList = null;
        if (user.getIsAdmin().equals("1")) {
            //是则拥有全部权限
            menusList = menuService.list();
        } else {
            //不是根据用户id查询对应的角色
            menusList = menuService.getMenuByUserId(roleAssignParm.getUserId());
        }
        //拼装成树的格式
        List<Menu> menusTree = MyTreeUtils.makeTree(menusList, 0);
        //根据角色id查询拥有的权限
        List<Menu> roleMenuList = menuService.getMenuByRoleId(roleAssignParm.getRoleId());
        //查询出回显的部分，就是它们公有的部分
        List<Integer> ids = new ArrayList<>();
        Optional.ofNullable(menusList).orElse(new ArrayList<>())
                .stream().filter(item -> item != null).forEach(item -> {
                    Optional.ofNullable(roleMenuList).orElse(new ArrayList<>())
                            .stream().filter(res -> res != null).forEach(res -> {
                                //相等的部分放入集合
                                if (item.getMenuId().equals(res.getMenuId())) {
                                    ids.add(res.getMenuId());
                                    return;
                                }
                            });
                });
        RolePermissionVo rolePermissionVo = new RolePermissionVo();
        rolePermissionVo.setListMenu(menusTree);
        rolePermissionVo.setCheckList(ids.toArray());
        return rolePermissionVo;
    }

    //分配权限保存
    @Override
    @Transactional
    public void saveAssign(RolePermissionParm rolePermissionParm) {
        //首先删除原来的数据
        QueryWrapper<RoleAndMenu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(RoleAndMenu::getRoleId,rolePermissionParm.getRoleId());
        roleAndMenuService.remove(wrapper);
        //保存新的
        roleAndMenuService.saveRoleMenu(rolePermissionParm.getRoleId(),rolePermissionParm.getList());
    }
}
