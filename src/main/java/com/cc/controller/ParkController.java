package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.parm.ParkParm;
import com.cc.domain.pojo.Park;
import com.cc.domain.pojo.Parking;
import com.cc.domain.vo.ResultVo;
import com.cc.service.ParkService;
import com.cc.service.ParkingService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 停车费管理控制器
 */
@RestController
@RequestMapping("/park")
public class ParkController {

    @Autowired
    private ParkService parkService;

    @Autowired
    private ParkingService parkingService;

    //新增
    @PostMapping
    @PreAuthorize("hasAuthority('pay:park:add')")
    public ResultVo  addPark(@RequestBody Park park){
        boolean flag = parkService.addPayPark(park);
        if (flag){
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("添加失败,当前车位暂未使用!");
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('pay:park:edit')")
    public ResultVo editPark(@RequestBody Park park){
        if(park.getParkingStatus().equals("1")){
            return ResultUtils.error("该账单已缴费，无法再编辑！");
        }
        boolean flag = parkService.editPayPark(park);
        if (flag){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败,当前车位暂未使用!");
    }

    //删除
    @DeleteMapping("/{parkingId}")
    @PreAuthorize("hasAuthority('pay:park:delete')")
    public ResultVo deletePark(@PathVariable("parkingId")Integer parkingId){
        boolean flag = parkService.deletePayPark(parkingId);
        if (flag){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("已经缴费，删除失败!");
    }

    //缴停车费
    @PostMapping("/payPark")
    @PreAuthorize("hasAuthority('pay:park:finish')")
    public ResultVo payPark(@RequestBody Park park){
        park.setParkingStatus("1");
        boolean flag = parkService.updateById(park);
        if (flag){
            return ResultUtils.success("缴费成功!");
        }
        return ResultUtils.error("缴费失败!");
    }

    //分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pay:park:list')")
    public ResultVo getList(ParkParm parkParm){
        IPage<Park> parkList = parkService.getList(parkParm);
        return ResultUtils.success("查询成功!",parkList);
    }

    //数据回显车位列表
    @GetMapping("/getListNoPage")
    public ResultVo getListNoPage(){
        List<Parking> parkListNoPage = parkingService.list();
        return ResultUtils.success("查询成功!",parkListNoPage);
    }

}
