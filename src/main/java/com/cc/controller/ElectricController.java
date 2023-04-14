package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Electric;
import com.cc.domain.parm.ElectricParm;
import com.cc.domain.vo.ResultVo;
import com.cc.service.ElectricService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 电费管理控制器
 */
@RestController
@RequestMapping("/electric")
public class ElectricController {

    @Autowired
    private ElectricService electricService;

    //新增
    @PostMapping
    @PreAuthorize("hasAuthority('pay:electric:add')")
    public ResultVo addElectric(@RequestBody Electric electric){
        boolean flag = electricService.saveElectric(electric);
        if (flag){
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("添加失败，当前房屋暂未居住，不能收费!");
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('pay:electric:edit')")
    public ResultVo editElectric(@RequestBody Electric electric){
        if(electric.getElectricStatus().equals("1")){
            return ResultUtils.error("该费用已经缴清，不能再次编辑！");
        }
        boolean flag = electricService.updateElectric(electric);
        if (flag){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败，当前房屋暂未居住，不能收费!");
    }

    //删除
    @DeleteMapping("/{electricId}")
    @PreAuthorize("hasAuthority('pay:electric:delete')")
    public ResultVo deleteElectric(@PathVariable("electricId")Integer electricId){
        boolean flag = electricService.removeElectricById(electricId);
        if (flag){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("不能删除,该费用已经缴清!");
    }

    //分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pay:electric:list')")
    public ResultVo getList(ElectricParm electricParm){
        IPage<Electric> electricList = electricService.getList(electricParm);
        return ResultUtils.success("查询成功!",electricList);
    }

    //缴电费
    @PostMapping("/payElectric")
    @PreAuthorize("hasAuthority('pay:electric:finish')")
    public ResultVo payElectric(@RequestBody Electric electric){
        boolean flag = electricService.updateById(electric);
        if (flag){
            return ResultUtils.success("缴费成功!");
        }
        return ResultUtils.error("缴费失败!");
    }
}
