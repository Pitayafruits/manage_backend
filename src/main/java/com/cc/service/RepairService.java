package com.cc.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.parm.RepairParm;
import com.cc.domain.pojo.Repair;

import java.util.Map;

/**
 * 维修管理Service层
 */
public interface RepairService extends IService<Repair> {

    //维修列表分页查询
    IPage<Repair> getRepairList(RepairParm repairParm);

    //添加
    boolean saveRepair(Repair repair);

    //编辑
    boolean updateRepair(Repair repair);

    //删除
    boolean removeRepair(Integer repairId);

    //我的报修
    IPage<Repair> getMyRepairList(RepairParm repairParm);

    //处理报修
    boolean sloveRepair(Repair repair);

    //每月报修柱形图
    Map<String, Object> listForMonths();
}
