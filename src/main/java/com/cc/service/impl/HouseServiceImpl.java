package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.House;
import com.cc.domain.parm.HouseParm;
import com.cc.mapper.HouseMapper;
import com.cc.service.HouseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 房屋service实现类
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    //分页查询
    @Override
    public IPage<House> getList(HouseParm parm) {
        //构造分页对象
        IPage<House> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurrentPage());
        return baseMapper.getList(page,parm.getBuildingName(),parm.getUnitName(),parm.getHouseNum(),parm.getHouseStatus());
    }

    //根据单元id获取房屋列表
    @Override
    public List getHouseList(House house) {
        //构造查询条件
        QueryWrapper<House> houseWrapper = new QueryWrapper<>();
        houseWrapper.lambda().eq(House::getUnitId,house.getUnitId());
        List<House> houseList = baseMapper.selectList(houseWrapper);
        return houseList;
    }

    //删除
    @Override
    public boolean removeHouse(Integer houseId) {
        //构造查询条件
        QueryWrapper<House> houseWrapper = new QueryWrapper<>();
        houseWrapper.lambda().eq(House::getHouseId,houseId)
                .eq(House::getHouseStatus,"0");
        Long count = baseMapper.selectCount(houseWrapper);
        if (count > 0){
            //如果房屋未使用才可以删除
            baseMapper.deleteById(houseId);
            return true;
        }
        return false;
    }

    //入住率饼形图
    @Override
    public Map<String, Object> getHouseLiveStatus() {
        //返回结果容器
        Map<String,Object> resultMap = new HashMap<>();
        //示例容器
        List<String> houseStatus = new ArrayList<>();
        houseStatus.add("入住");
        houseStatus.add("未入住");
        resultMap.put("houseStatus",houseStatus);
        //数据容器
        List<Map<String,Object>> houseCount = new ArrayList<>();
        //查询未入住的数量
        QueryWrapper<House> notUseWrapper = new QueryWrapper<>();
        notUseWrapper.lambda().eq(House::getHouseStatus,"0");
        Long notUseCount = baseMapper.selectCount(notUseWrapper);
        Map notUseMap = new HashMap();
        notUseMap.put("name","未入住");
        notUseMap.put("value",notUseCount);
        houseCount.add(notUseMap);
        //查询入住的数量
        QueryWrapper<House> useWrapper = new QueryWrapper<>();
        useWrapper.lambda().eq(House::getHouseStatus,"1");
        Long useCount = baseMapper.selectCount(useWrapper);
        Map useMap = new HashMap();
        useMap.put("name","入住");
        useMap.put("value",useCount);
        houseCount.add(useMap);
        resultMap.put("houseCount",houseCount);
        return resultMap;
    }

    //小区人数统计饼形图
    @Override
    public Map<String, Object> getAllLiverCount() {
        //返回结果容器
        Map<String,Object> resultMap = new HashMap<>();
        //查询每栋楼宇的住户的数量
        List<Map<String,Object>> liverByBuildCount = baseMapper.getLiverCount();
        resultMap.put("liverByBuildCount",liverByBuildCount);
        //示例容器
        List<String> liverBuildList = new ArrayList<>();
        //小区所有的楼宇
        for (Map<String, Object> map : liverByBuildCount) {
            String name = (String) map.get("name");
            liverBuildList.add(name);
        }
        resultMap.put("liverBuildList",liverBuildList);
        return resultMap;
    }

}
