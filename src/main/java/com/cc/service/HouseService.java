package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.House;
import com.cc.domain.parm.HouseParm;

import java.util.List;
import java.util.Map;

/**
 * 房屋service
 */
public interface HouseService extends IService<House> {

    //分页查询
    IPage<House> getList(HouseParm parm);

    //根据单元id获取房屋列表
    List getHouseList(House house);

   //删除
    boolean removeHouse(Integer houseId);

    //入住率饼形图
    Map<String, Object> getHouseLiveStatus();

    //小区人数统计饼形图
    Map<String, Object> getAllLiverCount();

}
