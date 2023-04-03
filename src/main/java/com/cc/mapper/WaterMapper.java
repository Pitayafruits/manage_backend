package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Water;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 水费管理DAO层
 */
@Repository
public interface WaterMapper extends BaseMapper<Water> {

    //分页查询
    IPage<Water> getWaterList(IPage<Water> page, @Param("liverName") String liverName, @Param("houseNum")String houseNum);
}
