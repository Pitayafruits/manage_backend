package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.domain.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * 用户登录DAO层
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
