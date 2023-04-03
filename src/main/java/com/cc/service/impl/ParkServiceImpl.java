package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.parm.LiverParkParm;
import com.cc.domain.parm.ParkParm;
import com.cc.domain.pojo.LiverAndParking;
import com.cc.domain.pojo.Park;
import com.cc.mapper.LiverAndParkingMapper;
import com.cc.mapper.ParkMapper;
import com.cc.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 停车费serviceImpl层
 */
@Service
public class ParkServiceImpl extends ServiceImpl<ParkMapper, Park> implements ParkService {

    @Autowired
    private LiverAndParkingMapper liverAndParkingMapper;

    //新增
    @Override
    public boolean addPayPark(Park park) {
        //查询当前正在使用车位的用户
        QueryWrapper<LiverAndParking> liverAndParkingWrapper = new QueryWrapper<>();
        liverAndParkingWrapper.lambda().eq(LiverAndParking::getParkId,park.getParkId())
                .eq(LiverAndParking::getLiverParkingStatus,"0");
        ;
        LiverAndParking liverAndParking = liverAndParkingMapper.selectOne(liverAndParkingWrapper);
        if (liverAndParking != null){
            //保存车位费用信息
            park.setLiverId(liverAndParking.getLiverId());
            baseMapper.insert(park);
            return true;
        }
        return false;
    }

    //编辑
    @Override
    public boolean editPayPark(Park park) {
        //查询当前正在使用车位的用户
        QueryWrapper<LiverAndParking> liverAndParkingWrapper = new QueryWrapper<>();
        liverAndParkingWrapper.lambda().eq(LiverAndParking::getParkId,park.getParkId())
                .eq(LiverAndParking::getLiverParkingStatus,"0");
        ;
        LiverAndParking liverAndParking = liverAndParkingMapper.selectOne(liverAndParkingWrapper);
        if (liverAndParking != null){
            //更新
            park.setLiverId(liverAndParking.getLiverId());
            baseMapper.updateById(park);
            return true;
        }
        return false;
    }

    //删除
    @Override
    public boolean deletePayPark(Integer parkingId) {
        //判断是否已经缴费
        QueryWrapper<Park> parkWrapper = new QueryWrapper<>();
        parkWrapper.lambda().eq(Park::getParkingId,parkingId)
                .eq(Park::getParkingStatus,"0");
        Long count = baseMapper.selectCount(parkWrapper);
        if ( count > 0){
            //如果未缴费，则可以删除
            baseMapper.deleteById(parkingId);
            return true;
        }
        return false;
    }

    //分页查询
    @Override
    public IPage<Park> getList(ParkParm parkParm) {
        //构造分页对象
        IPage<Park> page = new Page<>();
        page.setCurrent(parkParm.getCurrentPage());
        page.setSize(parkParm.getPageSize());
        return baseMapper.getParkList(page,parkParm.getLiverName(),parkParm.getParkName());
    }

    //停车费记录查询
    @Override
    public IPage<Park> getLiverParkList(LiverParkParm liverParkParm) {
        //构造分页对象
        IPage<Park> page = new Page<>();
        page.setCurrent(liverParkParm.getCurrentPage());
        page.setSize(liverParkParm.getPageSize());
        //构造条件构造器
        QueryWrapper<Park> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Park::getLiverId,liverParkParm.getLiverId());
        return baseMapper.selectPage(page,wrapper);
    }

}
