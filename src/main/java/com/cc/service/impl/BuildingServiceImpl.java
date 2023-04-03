package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.Building;
import com.cc.domain.pojo.Unit;
import com.cc.domain.parm.BuildingParm;
import com.cc.mapper.BuildingMapper;
import com.cc.service.BuildingService;
import com.cc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 楼宇接口实现
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {

    @Autowired
    private UnitService unitService;

    //分页查询楼宇信息
    @Override
    public IPage<Building> getList(BuildingParm buildingParm) {
        //构造查询条件
        QueryWrapper<Building> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(buildingParm.getBuildingName())){
            wrapper.lambda().like(Building::getBuildingName,buildingParm.getBuildingName());
        }
        if (StringUtils.isNotEmpty(buildingParm.getBuildingType())){
            wrapper.lambda().eq(Building::getBuildingType,buildingParm.getBuildingType());
        }
        wrapper.orderByAsc("building_number");
        //构造分页对象
        IPage<Building> page = new Page<>();
        page.setSize(buildingParm.getPageSize());
        page.setCurrent(buildingParm.getCurrentPage());
        return baseMapper.selectPage(page,wrapper);
    }

    //删除楼宇
    @Override
    public boolean deleteBuilding(Integer buildingId) {
        //构造查询条件
        QueryWrapper<Unit> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Unit::getBuildingId,buildingId);
        //判断查询结果
        long count = unitService.count(wrapper);
        if (count <= 0){
            this.removeById(buildingId);
            return true;
        }
        return false;
    }
}
