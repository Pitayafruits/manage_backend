package com.cc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Unit;
import com.cc.domain.parm.UnitParm;
import com.cc.domain.vo.ResultVo;
import com.cc.service.UnitService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 单元控制器
 */
@RestController
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private UnitService unitService;

    //查询单元列表
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('houseBuilding:unit:list')")
    public ResultVo getUnitList(UnitParm unitParm){
        IPage<Unit> unitList = unitService.getList(unitParm);
        return ResultUtils.success("查询成功!",unitList);
    }

    //新增单元
    @PostMapping
    @PreAuthorize("hasAuthority('houseBuilding:unit:add')")
    public ResultVo addUnit(@RequestBody Unit unit){
        boolean flag = unitService.save(unit);
        if (flag){
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("添加失败!");
    }

    //编辑单元
    @PutMapping
    @PreAuthorize("hasAuthority('houseBuilding:unit:edit')")
    public ResultVo editUnit(@RequestBody Unit unit){
        boolean flag = unitService.updateById(unit);
        if (flag){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除单元
    @DeleteMapping("/{unitId}")
    @PreAuthorize("hasAuthority('houseBuilding:unit:delete')")
    public ResultVo deleteUnit(@PathVariable("unitId") Integer unitId){
        boolean flag = unitService.removeUnit(unitId);
        if (flag){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!该单元被房屋绑定中");
    }

    //根据楼宇id查询单元列表
    @GetMapping("/getUnitListByBuildingId")
    public ResultVo getUnitListByBuildingId(Unit unit){
        //构造查询条件
        QueryWrapper<Unit> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Unit::getBuildingId,unit.getBuildingId());
        List<Unit> unitList = unitService.list(wrapper);
        return ResultUtils.success("查询成功!",unitList);
    }
}
