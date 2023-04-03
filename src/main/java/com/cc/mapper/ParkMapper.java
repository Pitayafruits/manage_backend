package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Park;
import com.cc.domain.pojo.Water;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 停车费管理DAO层
 */
@Repository
public interface ParkMapper extends BaseMapper<Park> {

    //分页查询
    IPage<Park> getParkList(IPage<Park> page, @Param("liverName")String liverName,@Param("parkName")String parkName);
}
