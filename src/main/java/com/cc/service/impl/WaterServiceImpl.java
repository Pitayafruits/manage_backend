package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.parm.LiverWaterParm;
import com.cc.domain.parm.WaterParm;
import com.cc.domain.pojo.LiverAndHouse;
import com.cc.domain.pojo.Water;
import com.cc.mapper.LiverAndHouseMapper;
import com.cc.mapper.WaterMapper;
import com.cc.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 水费管理ServiceImpl层
 */
@Service
public class WaterServiceImpl extends ServiceImpl<WaterMapper, Water> implements WaterService {

    @Autowired
    private LiverAndHouseMapper liverAndHouseMapper;

    //新增
    @Override
    public boolean saveWater(Water water) {
        //根据房屋id查询业主
        QueryWrapper<LiverAndHouse> liverAndHouseWrapper = new QueryWrapper<>();
        liverAndHouseWrapper.lambda().eq(LiverAndHouse::getHouseId,water.getHouseId())
                .eq(LiverAndHouse::getLiverHouseStatus,"0");
        LiverAndHouse liverAndHouse = liverAndHouseMapper.selectOne(liverAndHouseWrapper);
        if (liverAndHouse != null){
            //查询出的业主id设置到水费里
            water.setLiverId(liverAndHouse.getLiverId());
            //保存数据
            baseMapper.insert(water);
            return true;
        }
        return false;
    }

    //编辑
    @Override
    public boolean updateWater(Water water) {
        //根据房屋id查询业主
        QueryWrapper<LiverAndHouse> liverAndHouseWrapper = new QueryWrapper<>();
        liverAndHouseWrapper.lambda().eq(LiverAndHouse::getHouseId,water.getHouseId())
                .eq(LiverAndHouse::getLiverHouseStatus,"0");
        LiverAndHouse liverAndHouse = liverAndHouseMapper.selectOne(liverAndHouseWrapper);
        if (liverAndHouse != null) {
            //查询出的业主id设置到水费里
            water.setLiverId(liverAndHouse.getLiverId());
            //修改数据
            baseMapper.updateById(water);
            return true;
        }
        return false;
    }

    //删除
    @Override
    public boolean removeWaterById(Integer waterId) {
        //如果已经缴费，就不能删除
        QueryWrapper<Water> waterWrapper = new QueryWrapper<>();
        waterWrapper.lambda().eq(Water::getWaterId,waterId)
                .eq(Water::getWaterStatus,"1");
        Long count = baseMapper.selectCount(waterWrapper);
        if (count <= 0){
            //未缴费的记录可以执行删除
            baseMapper.deleteById(waterId);
            return true;
        }
        return false;
    }

    //分页查询
    @Override
    public IPage<Water> getList(WaterParm waterParm) {
        //构造分页对象
        IPage<Water> page = new Page<>();
        page.setCurrent(waterParm.getCurrentPage());
        page.setSize(waterParm.getPageSize());
        return baseMapper.getWaterList(page, waterParm.getLiverName(), waterParm.getHouseNum());
    }

    //水费记录查询
    @Override
    public IPage<Water> getLiverWaterList(LiverWaterParm liverWaterParm) {
        //后遭分页对象
        IPage<Water> page = new Page<>();
        page.setCurrent(liverWaterParm.getCurrentPage());
        page.setSize(liverWaterParm.getPageSize());
        //构造条件构造器
        QueryWrapper<Water> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Water::getLiverId,liverWaterParm.getLiverId());
        return baseMapper.selectPage(page,wrapper);
    }
}
