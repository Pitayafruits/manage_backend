package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.parm.LiverParkParm;
import com.cc.domain.parm.ParkParm;
import com.cc.domain.pojo.Park;

/**
 * 停车费service层
 */
public interface ParkService extends IService<Park> {

    //新增
    boolean addPayPark(Park park);

    //编辑
    boolean editPayPark(Park park);

    //删除
    boolean deletePayPark(Integer parkingId);

    //分页查询
    IPage<Park> getList(ParkParm parkParm);

    //停车费记录查询
    IPage<Park> getLiverParkList(LiverParkParm liverParkParm);
}
