package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Building;
import com.cc.domain.parm.BuildingParm;
import com.cc.domain.vo.ResultVo;
import com.cc.service.BuildingService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 楼宇controller
 */
@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    //新增楼宇
    @PostMapping
    @PreAuthorize("hasAuthority('houseBuilding:building:add')")
    public ResultVo saveBuilding(@RequestBody Building building){
        boolean flag = buildingService.save(building);
        if (flag){
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("添加失败!");
    }

    //编辑楼宇
    @PutMapping
    @PreAuthorize("hasAuthority('houseBuilding:building:edit')")
    public ResultVo editBuilding(@RequestBody Building building){
        boolean flag = buildingService.updateById(building);
        if (flag){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除楼宇
    @DeleteMapping("/{buildingId}")
    @PreAuthorize("hasAuthority('houseBuilding:building:delete')")
    public ResultVo deleteBuilding(@PathVariable("buildingId") Integer buildingId){
        boolean flag = buildingService.deleteBuilding(buildingId);
        if (flag){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!该楼宇有单元绑定使用中");
    }

    //分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('houseBuilding:building:list')")
    public ResultVo getBuilding(BuildingParm buildingParm){
        IPage<Building> buildingList = buildingService.getList(buildingParm);
        return ResultUtils.success("查询成功!",buildingList);
    }

    //添加单元时的数据回显
    @GetMapping("/unitList")
    public ResultVo unitList(){
        List<Building> list = buildingService.list();
        return ResultUtils.success("查询成功!",list);
    }

}
