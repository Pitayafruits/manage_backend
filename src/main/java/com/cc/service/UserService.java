package com.cc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.ChangePassword;
import com.cc.domain.pojo.UserAndRole;
import com.cc.domain.parm.LoginParm;
import com.cc.domain.vo.LoginResult;
import com.cc.domain.pojo.User;
import com.cc.domain.pojo.UserInfo;
import com.cc.domain.vo.RouterVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户登录service层
 */
public interface UserService extends IService<User> {

    //用户登录
    LoginResult loginUser(LoginParm loginParm);

    //获取用户信息
    UserInfo getUserInfo(User user,HttpServletRequest request);

    //根据用户id查询角色id
    UserAndRole getRoleByUserId(UserAndRole userAndRole);

    //保存角色
    void saveRole(UserAndRole userAndRole);

    //根据登录名查询用户信息
    User loadUser(String username);

    //获取菜单列表
    List<RouterVO> getMenu(HttpServletRequest request);

    //退出登录
    boolean quitUser(HttpServletRequest request, HttpServletResponse response);

    //修改密码
    boolean resetPass(ChangePassword user, HttpServletRequest request);

    //判断当前账户状态
    boolean isStatus(LoginParm loginParm);
}