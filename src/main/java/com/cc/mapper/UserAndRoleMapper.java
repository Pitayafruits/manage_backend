package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.domain.pojo.UserAndRole;
import org.springframework.stereotype.Repository;

/**
 * 角色-用户DAO层
 */
@Repository
public interface UserAndRoleMapper extends BaseMapper<UserAndRole> {
}
