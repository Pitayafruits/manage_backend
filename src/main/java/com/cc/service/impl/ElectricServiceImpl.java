package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.parm.LiverElectricParm;
import com.cc.domain.pojo.Electric;
import com.cc.domain.pojo.LiverAndHouse;
import com.cc.domain.parm.ElectricParm;
import com.cc.mapper.ElectricMapper;
import com.cc.mapper.LiverAndHouseMapper;
import com.cc.service.ElectricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 电费管理ServiceImpl层
 */
@Service
public class ElectricServiceImpl extends ServiceImpl<ElectricMapper, Electric> implements ElectricService {

    @Autowired
    private LiverAndHouseMapper liverAndHouseMapper;

    //新增
    @Override
    public boolean saveElectric(Electric electric) {
        //根据房屋id查询业主
        QueryWrapper<LiverAndHouse> liverAndHouseWrapper = new QueryWrapper<>();
        liverAndHouseWrapper.lambda().eq(LiverAndHouse::getHouseId,electric.getHouseId())
                .eq(LiverAndHouse::getLiverHouseStatus,"0");
        LiverAndHouse liverAndHouse = liverAndHouseMapper.selectOne(liverAndHouseWrapper);
        if (liverAndHouse != null){
            //查询出的业主id设置到电费里
            electric.setLiverId(liverAndHouse.getLiverId());
            //保存数据
            baseMapper.insert(electric);
            return true;
        }
        return false;
    }

    //编辑
    @Override
    public boolean updateElectric(Electric electric) {
        //根据房屋id查询业主
        QueryWrapper<LiverAndHouse> liverAndHouseWrapper = new QueryWrapper<>();
        liverAndHouseWrapper.lambda().eq(LiverAndHouse::getHouseId,electric.getHouseId())
                .eq(LiverAndHouse::getLiverHouseStatus,"0");
        LiverAndHouse liverAndHouse = liverAndHouseMapper.selectOne(liverAndHouseWrapper);
        if (liverAndHouse != null) {
            //查询出的业主id设置到电费里
            electric.setLiverId(liverAndHouse.getLiverId());
            //修改数据
            baseMapper.updateById(electric);
            return true;
        }
        return false;
    }

    //删除
    @Override
    public boolean removeElectricById(Integer electricId) {
        //如果已经缴费，就不能删除
        QueryWrapper<Electric> electricWrapper = new QueryWrapper<>();
        electricWrapper.lambda().eq(Electric::getElectricId,electricId)
                .eq(Electric::getElectricStatus,"1");
        Long count = baseMapper.selectCount(electricWrapper);
        if (count <= 0){
            //未缴费的记录可以执行删除
            baseMapper.deleteById(electricId);
            return true;
        }
        return false;
    }

    //分页查询
    @Override
    public IPage<Electric> getList(ElectricParm electricParm) {
        //构造分页对象
        IPage<Electric> page = new Page<>();
        page.setCurrent(electricParm.getCurrentPage());
        page.setSize(electricParm.getPageSize());
        return baseMapper.getElectricList(page,electricParm.getLiverName(),electricParm.getHouseNum());
    }

    //电费记录查询
    @Override
    public IPage<Electric> getLiverElectricList(LiverElectricParm liverElectricParm) {
        //构造分页对象
        IPage<Electric> page = new Page<>();
        page.setCurrent(liverElectricParm.getCurrentPage());
        page.setSize(liverElectricParm.getPageSize());
        //构造条件构造器
        QueryWrapper<Electric> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Electric::getLiverId,liverElectricParm.getLiverId());
        return baseMapper.selectPage(page,wrapper);
    }

}
