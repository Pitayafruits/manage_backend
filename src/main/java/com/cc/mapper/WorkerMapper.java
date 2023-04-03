package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.domain.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * 物业人员DAO层
 */
@Repository
public interface WorkerMapper extends BaseMapper<User> {
}
