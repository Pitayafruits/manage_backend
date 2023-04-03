package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.domain.pojo.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单DAO层
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    //根据用户id查询权限
    List<Menu> getMenuByUserId(@Param("userId") Integer userId);

    //根据业主id查询权限
    List<Menu> getMenuByLiverId(@Param("liverId") Integer liverId);

    //根据角色id查询权限
    List<Menu> getMenuByRoleId(@Param("roleId") Integer roleId);
}
