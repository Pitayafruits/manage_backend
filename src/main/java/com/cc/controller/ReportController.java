package com.cc.controller;

import com.cc.domain.vo.ResultVo;
import com.cc.service.ComplaintService;
import com.cc.service.HouseService;
import com.cc.service.ParkingService;
import com.cc.service.RepairService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 报表统计控制器
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private ParkingService parkingService;

    //维修柱形图
    @GetMapping("/getRepairCount")
    public ResultVo getRepairCount(){
        Map<String,Object> resultMap = repairService.listForMonths();
        return ResultUtils.success("查询成功！",resultMap);
    }

    //投诉折线图
    @GetMapping("/getComplaintCount")
    public ResultVo getComplaintCount(){
        Map<String,Object> resultMap = complaintService.listforMonths();
        return ResultUtils.success("查询成功！",resultMap);
    }

    //入住率饼形图
    @GetMapping("/getHouseStatus")
    public ResultVo getHouseStatus(){
        Map<String,Object> resultMap = houseService.getHouseLiveStatus();
        return ResultUtils.success("查询成功！",resultMap);
    }

    //小区人数统计饼形图
    @GetMapping("/getLiverCount")
    public ResultVo getLiverCount(){
        Map<String,Object> resultMap = houseService.getAllLiverCount();
        return ResultUtils.success("查询成功！",resultMap);
    }

    //小区车位使用情况饼形图
    @GetMapping("/getParkingStatus")
    public ResultVo getParkingStatus(){
        Map<String,Object> resultMap = parkingService.getParkStatus();
        return ResultUtils.success("查询成功！",resultMap);
    }
}
