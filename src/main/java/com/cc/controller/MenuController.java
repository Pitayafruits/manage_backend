package com.cc.controller;

import com.cc.domain.pojo.Menu;
import com.cc.domain.vo.ResultVo;
import com.cc.service.MenuService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单Controller
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //添加菜单
    @PostMapping
    @PreAuthorize("hasAuthority('user:menu:add')")
    public ResultVo addMenu(@RequestBody Menu menu){
        boolean flag = menuService.save(menu);
        if (flag){
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("添加失败！");
    }

    //编辑菜单
    @PutMapping
    @PreAuthorize("hasAuthority('user:menu:edit')")
    public ResultVo editMenu(@RequestBody Menu menu){
        boolean flag = menuService.updateById(menu);
        if (flag){
            return ResultUtils.success("修改成功!");
        }
        return ResultUtils.error("修改失败！");
    }

    //删除菜单
    @DeleteMapping("/{menuId}")
    @PreAuthorize("hasAuthority('user:menu:delete')")
    public ResultVo deleteMenu(@PathVariable("menuId") Integer menuId){
        boolean flag = menuService.deleteMenu(menuId);
        if (flag){
            return ResultUtils.success("删除成功！");
        }
        return  ResultUtils.error("删除失败，当前删除对象存在下级!");
    }

    //父级菜单的查询
    @GetMapping("/parent")
    public ResultVo getParent(){
        List<Menu> parentList = menuService.getParentList();
        return ResultUtils.success("查询成功",parentList);
    }

    //树形数据的查询
    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('user:menu:show')")
    public ResultVo getTree(){
        List<Menu> menuList = menuService.getMenuList();
        return ResultUtils.success("查询成功",menuList);
    }
}
