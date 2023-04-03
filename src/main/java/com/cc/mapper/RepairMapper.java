package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Repair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 维修管理DAO层
 */
@Repository
public interface RepairMapper extends BaseMapper<Repair> {

    //维修列表分页查询
    IPage<Repair> getList(IPage<Repair> page, @Param("liverName") String liverName);

    //每月报修柱形图
    Integer findRepCountByMonths(@Param("beforeDate") String beforeDate, @Param("afterDate") String afterDate);
}
