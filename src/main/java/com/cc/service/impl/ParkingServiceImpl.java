package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.Parking;
import com.cc.domain.parm.ParkingParm;
import com.cc.mapper.ParkingMapper;
import com.cc.service.ParkingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车位管理service实现
 */
@Service
public class ParkingServiceImpl extends ServiceImpl<ParkingMapper, Parking> implements ParkingService {

    //分页查询
    @Override
    public IPage<Parking> getList(ParkingParm parkingParm) {
        //构造查询条件
        QueryWrapper<Parking> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(parkingParm.getParkName())){
            wrapper.lambda().like(Parking::getParkName,parkingParm.getParkName());
        }
        if (StringUtils.isNotEmpty(parkingParm.getParkStatus())){
            wrapper.lambda().eq(Parking::getParkStatus,parkingParm.getParkStatus());
        }
        if (StringUtils.isNotEmpty(parkingParm.getParkType())){
            wrapper.lambda().eq(Parking::getParkType,parkingParm.getParkType());
        }
        wrapper.lambda().orderByAsc(Parking::getParkNumber);
        //构造分页对象
        IPage<Parking> page = new Page<>();
        page.setCurrent(parkingParm.getCurrentPage());
        page.setSize(parkingParm.getPageSize());
        return baseMapper.selectPage(page,wrapper);
    }

    //删除车位
    @Override
    public boolean removeParking(Integer parkId) {
        //构造查询条件
        QueryWrapper<Parking> parkingWrapper = new QueryWrapper<>();
        parkingWrapper.lambda().eq(Parking::getParkId,parkId)
                .eq(Parking::getParkStatus,"1");
        Long count = baseMapper.selectCount(parkingWrapper);
        if (count <= 0){
            //如果车位未使用，则可以删除
            baseMapper.deleteById(parkId);
            return true;
        }
        return false;
    }

    //小区车位使用情况饼形图
    @Override
    public Map<String, Object> getParkStatus() {
        //返回结果容器
        Map<String,Object> resultMap = new HashMap<>();
        //示例容器
        List<String> parkingStatus = new ArrayList<>();
        parkingStatus.add("使用");
        parkingStatus.add("未使用");
        resultMap.put("parkingStatus",parkingStatus);
        //数据容器
        List<Map<String,Object>> parkingCount = new ArrayList<>();
        //查询未使用的数量
        QueryWrapper<Parking> notUseWrapper = new QueryWrapper<>();
        notUseWrapper.lambda().eq(Parking::getParkStatus,"0");
        Long notUseCount = baseMapper.selectCount(notUseWrapper);
        Map notUseMap = new HashMap();
        notUseMap.put("name","未使用");
        notUseMap.put("value",notUseCount);
        parkingCount.add(notUseMap);
        //查询使用的数量
        QueryWrapper<Parking> useWrapper = new QueryWrapper<>();
        useWrapper.lambda().eq(Parking::getParkStatus,"1");
        Long useCount = baseMapper.selectCount(useWrapper);
        Map useMap = new HashMap();
        useMap.put("name","使用");
        useMap.put("value",useCount);
        parkingCount.add(useMap);
        resultMap.put("parkingCount",parkingCount);
        return resultMap;
    }
}
