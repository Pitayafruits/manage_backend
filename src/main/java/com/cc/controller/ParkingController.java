package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Parking;
import com.cc.domain.parm.ParkingParm;
import com.cc.domain.vo.ResultVo;
import com.cc.service.ParkingService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 车位管理控制器
 */
@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    //分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('parking:parkingList:list')")
    public ResultVo getList(ParkingParm parkingParm){
        IPage<Parking> parkingList = parkingService.getList(parkingParm);
        return ResultUtils.success("查询成功！",parkingList);
    }

    //新增车位
    @PostMapping
    @PreAuthorize("hasAuthority('parking:parkingList:add')")
    public ResultVo addParking(@RequestBody Parking parking){
        boolean flag = parkingService.save(parking);
        if (flag){
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("添加失败!");
    }

    //编辑车位
    @PutMapping
    @PreAuthorize("hasAuthority('parking:parkingList:edit')")
    public ResultVo editParking(@RequestBody Parking parking){
        boolean flag = parkingService.updateById(parking);
        if (flag){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除车位
    @DeleteMapping("/{parkId}")
    @PreAuthorize("hasAuthority('parking:parkingList:delete')")
    public ResultVo deleteParking(@PathVariable("parkId")Integer parkId){
        boolean flag = parkingService.removeParking(parkId);
        if (flag){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!该车位目前在使用中");
    }

    //统计车位数量
    @GetMapping("/getParkingNum")
    public ResultVo getParkingNum(){
        long parkingCount = parkingService.count();
        return ResultUtils.success("查询成功！",parkingCount);
    }
}
