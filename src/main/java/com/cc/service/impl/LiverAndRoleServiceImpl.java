package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.LiverAndRole;
import com.cc.mapper.LiverAndRoleMapper;
import com.cc.service.LiverAndRoleService;
import org.springframework.stereotype.Service;

/**
 * 业主角色service实现层
 */
@Service
public class LiverAndRoleServiceImpl extends ServiceImpl<LiverAndRoleMapper, LiverAndRole> implements LiverAndRoleService {
}
