package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.parm.LiverWaterParm;
import com.cc.domain.parm.WaterParm;
import com.cc.domain.pojo.Water;

import java.util.List;

/**
 * 水费管理Service层
 */
public interface WaterService extends IService<Water> {

    //新增
    boolean saveWater(Water water);

    //编辑
    boolean updateWater(Water water);

    //删除
    boolean removeWaterById(Integer waterId);

    //分页查询
    IPage<Water> getList(WaterParm waterParm);

    //水费记录查询
    IPage<Water> getLiverWaterList(LiverWaterParm liverWaterParm);
}
