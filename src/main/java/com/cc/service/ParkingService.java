package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.Parking;
import com.cc.domain.parm.ParkingParm;

import java.util.Map;

/**
 * 车位管理service
 */
public interface ParkingService extends IService<Parking> {

    //分页查询
    IPage<Parking> getList(ParkingParm parkingParm);

    //删除车位
    boolean removeParking(Integer parkId);

    //小区车位使用情况饼形图
    Map<String, Object> getParkStatus();
}
