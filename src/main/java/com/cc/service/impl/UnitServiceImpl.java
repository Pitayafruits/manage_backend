package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.House;
import com.cc.domain.pojo.Unit;
import com.cc.domain.parm.UnitParm;
import com.cc.mapper.UnitMapper;
import com.cc.service.HouseService;
import com.cc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 单元ServiceImpl
 */
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements UnitService {

    @Autowired
    private HouseService houseService;

    //查询单元列表
    @Override
    public IPage<Unit> getList(UnitParm unitParm) {
        //构造分页对象
        IPage<Unit> page = new Page<>();
        page.setSize(unitParm.getPageSize());
        page.setCurrent(unitParm.getCurrentPage());
        return baseMapper.getList(page,unitParm.getUnitName(),unitParm.getBuildingName());
    }

    //删除单元
    @Override
    public boolean removeUnit(Integer unitId) {
        //构造查询条件
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(House::getUnitId, unitId);
        long count = houseService.count(wrapper);
        if (count <= 0){
            this.removeById(unitId);
            return true;
        }
        return false;
    }
}
