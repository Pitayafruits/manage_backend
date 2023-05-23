package com.cc.controller;



import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cc.domain.pojo.ChangePassword;
import com.cc.domain.pojo.UserAndRole;
import com.cc.domain.parm.LoginParm;
import com.cc.domain.vo.LoginResult;
import com.cc.domain.pojo.User;
import com.cc.domain.pojo.UserInfo;
import com.cc.domain.vo.ResultVo;
import com.cc.domain.vo.RouterVO;
import com.cc.service.UserService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户登录控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginParm loginParm){
        //首先判断是否为空
        if (StringUtils.isEmpty(loginParm.getUsername())|| StringUtils.isEmpty(loginParm.getPassword()) || StringUtils.isEmpty(loginParm.getUserType())){
            return ResultUtils.error("用户名、密码、用户类型不能为空!");
        }
        //判断当前账户是否被禁用
        boolean flag = userService.isStatus(loginParm);
        if(!flag){
            return ResultUtils.error("登陆失败，当前账号被禁用！");
        }
        //不为空在进行登录
        LoginResult result = userService.loginUser(loginParm);
        if (result == null){
            return ResultUtils.error("用户名或密码错误!");
        }
        return ResultUtils.success("登录成功!",result);
    }

    //获取用户信息
    @GetMapping("/getInfo")
    public ResultVo getInfo(User user,HttpServletRequest request){
        UserInfo result = userService.getUserInfo(user,request);
        if (result == null){
            return ResultUtils.error("服务器错误!");
        }
        return ResultUtils.success("获取用户信息成功!",result);
    }

    //根据用户id查询角色id
    @GetMapping("/getRoleByUserId")
    public ResultVo getRoleByUserId(UserAndRole userAndRole){
        UserAndRole userRole = userService.getRoleByUserId(userAndRole);
        return ResultUtils.success("查询成功!",userRole);
    }

    //保存用户角色
    @PostMapping("/saveRole")
    @PreAuthorize("hasAuthority('user:worker:allocation')")
    public ResultVo saveRole(@RequestBody UserAndRole userAndRole){
        userService.saveRole(userAndRole);
        return ResultUtils.success("分配角色成功!");
    }

    //退出登录
    @PostMapping("/loginOut")
    public ResultVo logintOut(HttpServletRequest request, HttpServletResponse response){
        boolean flag = userService.quitUser(request,response);
        if(flag){
            return ResultUtils.success("退出成功！");
        }
        return ResultUtils.error("发生错误！");
    }

    //修改密码
    @PostMapping("/resetPassword")
    public ResultVo resetPassword(@RequestBody ChangePassword user, HttpServletRequest request){
        boolean flag = userService.resetPass(user,request);
        if (flag){
            return ResultUtils.success("修改成功！");
        }
        return ResultUtils.error("修改失败，旧密码错误！");
    }

    //获取菜单列表
    @GetMapping("/getMenuList")
    public ResultVo getMenuList(HttpServletRequest request){
        List<RouterVO> resultList = userService.getMenu(request);
        return ResultUtils.success("查询成功！",resultList);
    }
}
