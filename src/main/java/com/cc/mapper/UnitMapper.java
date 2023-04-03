package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Unit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 单元DAO层
 */
@Repository
public interface UnitMapper extends BaseMapper<Unit> {

    //分页查询
    IPage<Unit> getList(IPage<Unit> page, @Param("unitName")String unitName,@Param("buildingName")String buildingName);
}
