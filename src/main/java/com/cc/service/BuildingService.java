package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.Building;
import com.cc.domain.parm.BuildingParm;

/**
 * 楼宇service层
 */
public interface BuildingService extends IService<Building> {

    //分页查询楼宇信息
    IPage<Building> getList(BuildingParm buildingParm);

    //删除楼宇
    boolean deleteBuilding(Integer buildingId);
}
