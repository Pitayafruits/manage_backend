package com.cc.config.security.service;

import com.cc.domain.pojo.Liver;
import com.cc.domain.pojo.Menu;
import com.cc.domain.pojo.User;
import com.cc.service.LiverService;
import com.cc.service.MenuService;
import com.cc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义UserDetailService
 */
@Component("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private LiverService liverService;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //获取用户类型 0:业主 1：物业
        int index = s.indexOf(":");
        //截取账号和用户类型
        String username = s.substring(0,index);
        String userType = s.substring(index + 1, s.length());
        //根据用户类型查询不同表
        //UserDetails user = null;
        if(userType.equals("0")){ //业主
            Liver liver = liverService.loadUser(username);
            if(liver == null){
                throw new UsernameNotFoundException("用户账号不存在！");
            }
            //查询业主权限
            List<Menu> liverMenuList = menuService.getMenuByLiverId(liver.getLiverId());
            //获取权限字段
            List<String> menuList = liverMenuList.stream()
                    .filter(item -> item != null).map(item -> item.getMenuCode())
                    .filter(item -> item != null).collect(Collectors.toList());
            //转数组
            String[] menuArray = menuList.toArray(new String[menuList.size()]);
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(menuArray);
            //设置物业权限
            liver.setAuthorities(authorityList);
            return liver;
        }else if(userType.equals("1")){ //物业
            User user = userService.loadUser(username);
            if(user == null){
                throw new UsernameNotFoundException("用户账号不存在！");
            }
            //查询物业权限
            List<Menu> userMenuList = menuService.getMenuByUserId(user.getUserId());
            //获取权限字段
            List<String> menuList = userMenuList.stream()
                    .filter(item -> item != null).map(item -> item.getMenuCode())
                    .filter(item -> item != null).collect(Collectors.toList());
            //转数组
            String[] menuArray = menuList.toArray(new String[menuList.size()]);
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(menuArray);
            //设置物业权限
            user.setAuthorities(authorityList);
            return user;
        }else{
            throw new UsernameNotFoundException("用户类型不存在！");
        }
    }
}
