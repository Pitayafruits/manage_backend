package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.parm.WaterParm;
import com.cc.domain.pojo.Water;
import com.cc.domain.vo.ResultVo;
import com.cc.service.WaterService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 水费管理控制器
 */
@RestController
@RequestMapping("/water")
public class WaterController {

    @Autowired
    private WaterService waterService;

    //新增
    @PostMapping
    @PreAuthorize("hasAuthority('pay:water:add')")
    public ResultVo addWater(@RequestBody Water water){
        boolean flag = waterService.saveWater(water);
        if (flag){
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("添加失败，当前房屋暂未居住，不能收费!");
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('pay:water:edit')")
    public ResultVo editWater(@RequestBody Water water){
        if(water.getWaterStatus().equals("1")){
            return ResultUtils.error("该账单已缴费，无法再编辑！");
        }
        boolean flag = waterService.updateWater(water);
        if (flag){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败，当前房屋暂未居住，不能收费!");
    }

    //删除
    @DeleteMapping("/{waterId}")
    @PreAuthorize("hasAuthority('pay:water:delete')")
    public ResultVo deleteWater(@PathVariable("waterId")Integer waterId){
        boolean flag = waterService.removeWaterById(waterId);
        if (flag){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("不能删除,该费用已经缴清!");
    }

    //分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pay:water:list')")
    public ResultVo getList(WaterParm waterParm){
        IPage<Water> waterList = waterService.getList(waterParm);
        return ResultUtils.success("查询成功!",waterList);
    }

    //缴水费
    @PostMapping("/payWater")
    @PreAuthorize("hasAuthority('pay:water:finish')")
    public ResultVo payWater(@RequestBody Water water){
        boolean flag = waterService.updateById(water);
        if (flag){
            return ResultUtils.success("缴费成功!");
        }
        return ResultUtils.error("缴费失败!");
    }

}
