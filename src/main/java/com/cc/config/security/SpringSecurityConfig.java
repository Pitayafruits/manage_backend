package com.cc.config.security;

import com.cc.config.security.filter.CheckTokenFilter;
import com.cc.config.security.handler.CustomAccessDeineHandler;
import com.cc.config.security.handler.LoginFailureHandler;
import com.cc.config.security.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity配置类
 */

@Configuration
@EnableWebSecurity //启用spring security
@EnableGlobalMethodSecurity(prePostEnabled = true)  //启用spring security注解
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private CustomAccessDeineHandler customAccessDeineHandler;

    @Autowired
    private CheckTokenFilter checkTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //配置方法
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //解决跨域问题
        http.cors().and().headers().frameOptions().disable();
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //关闭跨域请求伪造
        http.csrf().disable()
                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //放行首页不需要认证的请求
                .authorizeRequests()
                .antMatchers("/user/login","/community/comminfo","/worker/workerList","/notice/getNoticeList","/facilities/getFacList","/liver/sendMsg","/liver/resetPassword").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(loginFailureHandler).accessDeniedHandler(customAccessDeineHandler);
    }

    //注入AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //配置自定义的UserDetailService
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailService);
    }
}
