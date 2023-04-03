package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.parm.RepairParm;
import com.cc.domain.pojo.Repair;
import com.cc.domain.vo.ResultVo;
import com.cc.service.RepairService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 维修管理控制层
 */
@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    //维修列表分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('repair:repairList:list')")
    public ResultVo getList(RepairParm repairParm){
        IPage<Repair> repairList = repairService.getRepairList(repairParm);
        return ResultUtils.success("查询成功！",repairList);
    }

    //添加
    @PostMapping
    @PreAuthorize("hasAuthority('repair:myRepair:add')")
    public ResultVo addRepair(@RequestBody Repair repair){
        boolean flag = repairService.saveRepair(repair);
        if(flag){
            return ResultUtils.success("添加成功！");
        }
        return ResultUtils.error("添加失败！");
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('repair:myRepair:edit')")
    public ResultVo editRepair(@RequestBody Repair repair){
        boolean flag = repairService.updateRepair(repair);
        if(flag){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("不可编辑，该报修已被处理！");
    }

    //删除
    @DeleteMapping("/{repairId}")
    @PreAuthorize("hasAuthority('repair:myRepair:delete')")
    public ResultVo deleteRepair(@PathVariable("repairId") Integer repairId){
        boolean flag = repairService.removeRepair(repairId);
        if (flag){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("不可删除，该报修已被处理！");
    }

    //我的报修
    @GetMapping("/myRepair")
    @PreAuthorize("hasAuthority('repair:myRepair:list')")
    public ResultVo getMyList(RepairParm repairParm){
        IPage<Repair> repairList = repairService.getMyRepairList(repairParm);
        return ResultUtils.success("查询成功！",repairList);
    }

    //处理报修
    @PutMapping("/overRepair")
    @PreAuthorize("hasAuthority('repair:repairList:slove')")
    public ResultVo overRepair(@RequestBody Repair repair){
        boolean flag = repairService.sloveRepair(repair);
        if (flag){
            return ResultUtils.success("处理成功！");
        }
        return ResultUtils.error("处理失败！");
    }

}
