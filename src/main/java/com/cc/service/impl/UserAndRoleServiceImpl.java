package com.cc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.UserAndRole;
import com.cc.mapper.UserAndRoleMapper;
import com.cc.service.UserAndRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户-角色serviceImpl层
 */
@Service
public class UserAndRoleServiceImpl extends ServiceImpl<UserAndRoleMapper, UserAndRole> implements UserAndRoleService {
}
