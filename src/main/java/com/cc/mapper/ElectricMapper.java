package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Electric;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 电费管理DAO层
 */
@Repository
public interface ElectricMapper extends BaseMapper<Electric> {

    //分页查询
    IPage<Electric> getElectricList(IPage<Electric> page, @Param("liverName") String liverName,@Param("houseNum")String houseNum);
}
