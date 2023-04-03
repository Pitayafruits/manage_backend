package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.parm.LiverElectricParm;
import com.cc.domain.pojo.Electric;
import com.cc.domain.parm.ElectricParm;

/**
 * 电费管理Service层
 */
public interface ElectricService extends IService<Electric> {

    //新增
    boolean saveElectric(Electric electric);

    //编辑
    boolean updateElectric(Electric electric);

    //删除
    boolean removeElectricById(Integer electricId);

    //分页查询
    IPage<Electric> getList(ElectricParm electricParm);

    //电费记录查询
    IPage<Electric> getLiverElectricList(LiverElectricParm liverElectricParm);
}
