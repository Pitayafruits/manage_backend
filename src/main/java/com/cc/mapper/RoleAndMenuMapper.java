package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.domain.pojo.RoleAndMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色权限DAO
 */
@Repository
public interface RoleAndMenuMapper extends BaseMapper<RoleAndMenu> {

    //保存权限
    boolean saveRoleMenu(@Param("roleId") Integer roleId,@Param("menuIds")List<Integer> menuIds);
}
