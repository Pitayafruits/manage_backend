package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.Unit;
import com.cc.domain.parm.UnitParm;

/**
 * 单元service
 */
public interface UnitService extends IService<Unit> {

    //查询单元列表
    IPage<Unit> getList(UnitParm unitParm);

    //删除单元
    boolean removeUnit(Integer unitId);
}
