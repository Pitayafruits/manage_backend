package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.parm.LiverElectricParm;
import com.cc.domain.parm.LiverParkParm;
import com.cc.domain.parm.LiverWaterParm;
import com.cc.domain.pojo.Electric;
import com.cc.domain.pojo.Park;
import com.cc.domain.pojo.Water;
import com.cc.domain.vo.ResultVo;
import com.cc.service.ElectricService;
import com.cc.service.ParkService;
import com.cc.service.WaterService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缴费记录控制器
 */
@RestController
@RequestMapping("/records")
public class RecordsController {

    @Autowired
    private WaterService waterService;

    @Autowired
    private ElectricService electricService;

    @Autowired
    private ParkService parkService;

    //水费记录查询
    @GetMapping("/waterList")
    @PreAuthorize("hasAuthority('records:myWaterList:list')")
    public ResultVo getMyWaterList(LiverWaterParm liverWaterParm){
        IPage<Water> liverWaterList = waterService.getLiverWaterList(liverWaterParm);
        return ResultUtils.success("查询成功！",liverWaterList);
    }

    //电费记录查询
    @GetMapping("/electricList")
    @PreAuthorize("hasAuthority('records:myElectricList:list')")
    public ResultVo getMyElectricList(LiverElectricParm liverElectricParm){
        IPage<Electric> liverElectricList = electricService.getLiverElectricList(liverElectricParm);
        return ResultUtils.success("查询成功！",liverElectricList);
    }

    //停车费记录查询
    @GetMapping("/parkList")
    @PreAuthorize("hasAuthority('records:myParkList:list')")
    public ResultVo getMyParkList(LiverParkParm liverParkParm){
        IPage<Park> liverParkList = parkService.getLiverParkList(liverParkParm);
        return ResultUtils.success("查询成功！",liverParkList);
    }
}
