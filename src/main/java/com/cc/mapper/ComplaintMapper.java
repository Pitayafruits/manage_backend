package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Complaint;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 投诉DAO层
 */
@Repository
public interface ComplaintMapper extends BaseMapper<Complaint> {

    //分页查询
    IPage<Complaint> getList(IPage<Complaint> page,@Param("complaintTitle") String complaintTitle);

    //根据月份查询投诉数量
    Integer findComCountByMonths(@Param("beforeDate") String beforeDate,@Param("afterDate") String afterDate);
}
