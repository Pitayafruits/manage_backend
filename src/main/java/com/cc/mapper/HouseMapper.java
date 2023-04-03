package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.House;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 房屋DAO层
 */
@Repository
public interface HouseMapper extends BaseMapper<House> {

    //分页查询
    IPage<House> getList(IPage<House> page, @Param("buildingName") String buildingName,@Param("unitName") String unitName,@Param("houseNum") String houseNum,@Param("houseStatus") String houseStatus);

    //查询每栋楼宇的住户的数量
    List<Map<String,Object>> getLiverCount();
}
