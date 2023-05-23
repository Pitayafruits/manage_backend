package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.*;
import com.cc.domain.parm.LoginParm;
import com.cc.domain.vo.LoginResult;
import com.cc.domain.vo.RouterVO;
import com.cc.mapper.LiverMapper;
import com.cc.mapper.UserAndRoleMapper;
import com.cc.mapper.UserMapper;
import com.cc.service.LiverService;
import com.cc.service.MenuService;
import com.cc.service.UserService;
import com.cc.utils.JwtUtils;
import com.cc.utils.MyTreeUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户登录ServiceImpl层
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserAndRoleMapper userAndRoleMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MenuService menuService;

    @Autowired
    private LiverService liverService;

    @Autowired
    private UserService userService;

    @Autowired
    private LiverMapper liverMapper;

    //用户登录
    @Override
    public LoginResult loginUser(LoginParm loginParm) {
        //加密密码
        String encode = passwordEncoder.encode(loginParm.getPassword());
        //生成spring security需要的token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginParm.getUsername() + ":" + loginParm.getUserType(), loginParm.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //生成的token交给spring security的上下文
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        //根据用户类型做不同处理
        if (loginParm.getUserType().equals("0")) {
            Liver liver = (Liver) authenticate.getPrincipal();
            //生成token返回前端以及token过期时间
            String token = jwtUtils.generateToken(loginParm.getUsername(), loginParm.getUserType());
            Long time = jwtUtils.getExpireTime(token);
            LoginResult result = new LoginResult();
            result.setUserId(liver.getLiverId());
            result.setToken(token);
            result.setLostTime(time);
            return result;
        } else {
            User user = (User) authenticate.getPrincipal();
            //生成token返回前端以及token过期时间
            String token = jwtUtils.generateToken(loginParm.getUsername(), loginParm.getUserType());
            Long time = jwtUtils.getExpireTime(token);
            LoginResult result = new LoginResult();
            result.setUserId(user.getUserId());
            result.setToken(token);
            result.setLostTime(time);
            return result;
        }
    }

    //获取用户信息
    @Override
    public UserInfo getUserInfo(User user, HttpServletRequest request) {
        //从token解析用户类型
        String token = request.getHeader("token");
        Claims claims = jwtUtils.getClaimsFromToken(token);
        Object userType = claims.get("userType");
        if (userType.equals("0")) { //业主
            Liver liver = liverService.getById(user.getUserId());
            UserInfo userInfo = new UserInfo();
            userInfo.setId(liver.getLiverId());
            userInfo.setName(liver.getLiverName());
            userInfo.setAvatar("https://cdn.staticaly.com/gh/Pitayafruits/myPicRep@main/PropertyManage/202302211504129.png");
            //查询业主权限
            List<Menu> liverMenuList = menuService.getMenuByLiverId(liver.getLiverId());
            //获取权限字段
            List<String> menuList = liverMenuList.stream()
                    .filter(item -> item != null).map(item -> item.getMenuCode())
                    .filter(item -> item != null).collect(Collectors.toList());
            //转数组
            String[] menuArray = menuList.toArray(new String[menuList.size()]);
            userInfo.setRoles(menuArray);
            return userInfo;
        } else { //物业
            User usr = baseMapper.selectById(user.getUserId());
            if (usr != null) {
                //构造返回结果
                UserInfo userInfo = new UserInfo();
                userInfo.setId(usr.getUserId());
                userInfo.setName(usr.getUsername());
                userInfo.setAvatar("https://cdn.staticaly.com/gh/Pitayafruits/myPicRep@main/PropertyManage/202302211504129.png");
                //根据用户id查询权限
                //查询物业权限
                List<Menu> userMenuList = menuService.getMenuByUserId(user.getUserId());
                //获取权限字段
                List<String> menuList = userMenuList.stream()
                        .filter(item -> item != null).map(item -> item.getMenuCode())
                        .filter(item -> item != null).collect(Collectors.toList());
                //转数组
                String[] menuArray = menuList.toArray(new String[menuList.size()]);
                userInfo.setRoles(menuArray);
                return userInfo;
            }
        }
        return null;
    }

    //根据用户id查询角色id
    @Override
    public UserAndRole getRoleByUserId(UserAndRole userAndRole) {
        //构造查询条件
        QueryWrapper<UserAndRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserAndRole::getUserId, userAndRole.getUserId());
        return userAndRoleMapper.selectOne(wrapper);
    }

    //保存角色
    @Override
    @Transactional
    public void saveRole(UserAndRole userAndRole) {
        //先删除原来的角色
        QueryWrapper<UserAndRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserAndRole::getUserId, userAndRole.getUserId());
        userAndRoleMapper.delete(wrapper);
        //保存新的角色
        userAndRoleMapper.insert(userAndRole);
    }

    //根据登录名查询用户信息
    @Override
    public User loadUser(String username) {
        //构造查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername, username);
        return baseMapper.selectOne(wrapper);
    }

    //获取菜单列表
    @Override
    public List<RouterVO>  getMenu(HttpServletRequest request) {
        //获取token
        String token = request.getHeader("token");
        //获取用户名
        String username = jwtUtils.getUsernameFromToken(token);
        //获取用户类型
        Claims claims = jwtUtils.getClaimsFromToken(token);
        Object userType = claims.get("userType");
        if(userType.equals("0")){ //业主
            //查询业主权限
            Liver liver = liverService.loadUser(username);
            List<Menu> liverMenuList = menuService.getMenuByLiverId(liver.getLiverId());
            List<Menu> collect = liverMenuList.stream().filter(item -> item != null && !item.getType().equals("2")).collect(Collectors.toList());
            //组装路由数据
            List<RouterVO> routerVOList = MyTreeUtils.makeRouter(collect, 0);
            return routerVOList;
        }else{ //物业
            //查询业主权限
            User user = userService.loadUser(username);
            List<Menu> userMenuList = menuService.getMenuByUserId(user.getUserId());
            List<Menu> collect = userMenuList.stream().filter(item -> item != null && !item.getType().equals("2")).collect(Collectors.toList());
            //组装路由数据
            List<RouterVO> routerVOList = MyTreeUtils.makeRouter(collect, 0);
            return routerVOList;
        }
    }

    //退出登录
    @Override
    public boolean quitUser(HttpServletRequest request, HttpServletResponse response) {
        //获取当前认证对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            return true;
        }
        return false;
    }

    //修改密码
    @Override
    public boolean resetPass(ChangePassword user,HttpServletRequest request) {
        //获取token
        String token = request.getHeader("token");
        Claims claims = jwtUtils.getClaimsFromToken(token);
        //判断用户类型
        Object userType = claims.get("userType");
        if(userType.equals("0")){ //0:业主
            Liver liver = liverService.getById(user.getUserId());
            //旧密码
            String databaseOldPassword = liver.getPassword();
            boolean flag = passwordEncoder.matches(user.getOldPassword(), databaseOldPassword);
            if(!flag){
                return false;
            }
            Liver liverUser = new Liver();
            liverUser.setLiverId(user.getUserId());
            liverUser.setPassword(passwordEncoder.encode(user.getNewPassword()));
            return liverService.updateById(liverUser);
        }else{
            User newUser = userService.getById(user.getUserId());
            //旧密码
            String databaseOldPassword = newUser.getPassword();
            boolean flag = passwordEncoder.matches(user.getOldPassword(), databaseOldPassword);
            if(!flag){
                return false;
            }
            User userInfo = new User();
            userInfo.setUserId(user.getUserId());
            userInfo.setPassword(passwordEncoder.encode(user.getNewPassword()));
            return userService.updateById(userInfo);
        }
    }

    //判断当前账户状态
    @Override
    public boolean isStatus(LoginParm loginParm) {
        //根据用户类型不同做不同处理
        if(loginParm.getUserType().equals("1")){
            //获取登录的用户密码
            String username = loginParm.getUsername();
            //构造条件构造器
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.lambda().eq(User::getUsername,username);
            //执行查询
            User user = baseMapper.selectOne(userWrapper);
            //判断当前账户是否被禁用
            if(user.getIsStatus().equals("1")){
                return false;
            }
        }else{
            //获取登录的用户密码
            String livername = loginParm.getUsername();
            //构造条件构造器
            QueryWrapper<Liver> liverWrapper = new QueryWrapper<>();
            liverWrapper.lambda().eq(Liver::getUsername,livername);
            //执行查询
            Liver liver = liverMapper.selectOne(liverWrapper);
            //判断当前账户是否被禁用
            if(liver.getLiverStatus().equals("1")){
                return false;
            }
        }
        return true;
    }

}
