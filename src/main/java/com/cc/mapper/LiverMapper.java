package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Liver;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 业主DAO层
 */
@Repository
public interface LiverMapper extends BaseMapper<Liver> {

    //分页查询
    IPage<Liver> getLiverList(IPage<Liver> page, @Param("liverName")String liverName,@Param("liverPhone")String liverPhone);

    //编辑业主时的查询
    Liver getLiver(@Param("liverId")Integer liverId);
}
