package com.cc.config.security.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cc.config.security.exception.CustomerAuthenionException;
import com.cc.config.security.handler.LoginFailureHandler;
import com.cc.config.security.service.CustomUserDetailService;
import com.cc.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token验证过滤器
 */
@Component("checkTokenFilter")
public class CheckTokenFilter extends OncePerRequestFilter {

    //重置密码地址
    @Value("${cc.resetPassUrl}")
    private String resetPassUrl;

    //发送短信地址
    @Value("${cc.sendMsgUrl}")
    private String sendMsgUrl;

    //首页设施列表
    @Value("${cc.facListUrl}")
    private String facListUrl;

    //登录请求的地址
    @Value("${cc.loginUrl}")
    private String loginUrl;

    //首页小区信息的地址
    @Value("${cc.commInfoUrl}")
    private String commInfoUrl;

    //工作人员列表信息的地址
    @Value("${cc.workerListUrl}")
    private String workerListUrl;

    //公告列表的地址
    @Value("${cc.noticeListUrl}")
    private String noticeListUrl;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            //获取当前请求的地址
            String url = httpServletRequest.getRequestURI();
            //不需要token验证的请求
            //登录请求 小区基本信息请求
            if(!url.equals(loginUrl) && (!url.equals(commInfoUrl)) && (!url.equals(workerListUrl)) && (!url.equals(noticeListUrl)) && (!url.equals(facListUrl)) && (!url.equals(sendMsgUrl)) && (!url.equals(resetPassUrl))){
                //非登录做token验证
                tokenValidate(httpServletRequest);
            }
        }catch (AuthenticationException e){
            loginFailureHandler.commence(httpServletRequest,httpServletResponse,e);
            return;
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    //token验证
    private void tokenValidate(HttpServletRequest httpServletRequest){
        //获取token
        String token = httpServletRequest.getHeader("token");
        if(StringUtils.isEmpty(token)){
            token = httpServletRequest.getParameter("token");
        }
        if(StringUtils.isEmpty(token)){
            throw new CustomerAuthenionException("未获取到token!");
        }
        //token获取到则解析
        String username = jwtUtils.getUsernameFromToken(token);
        if(StringUtils.isEmpty(username)){
            throw new CustomerAuthenionException("登录已过期，请重新登录!");
        }
        //获取用户类型
        Claims claims = jwtUtils.getClaimsFromToken(token);
        String userType = (String) claims.get("userType");
        //查询用户信息,交给Spring Security
        UserDetails details = customUserDetailService.loadUserByUsername(username + ":" + userType);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details,null, details.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        //设置上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
