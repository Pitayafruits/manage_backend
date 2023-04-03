package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.User;
import com.cc.domain.pojo.UserAndRole;
import com.cc.domain.parm.UserParm;
import com.cc.mapper.UserAndRoleMapper;
import com.cc.mapper.WorkerMapper;
import com.cc.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * 物业人员serviceImpl层
 */
@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, User> implements WorkerService {

    @Autowired
    private UserAndRoleMapper userAndRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //分页查询物业人员列表
    @Override
    public IPage<User> list(UserParm parm) {
        //构造分页对象
        IPage<User> workerPage = new Page<>();
        workerPage.setSize(parm.getPageSize());
        workerPage.setCurrent(parm.getCurrentPage());
        //构造分页查询条件
        QueryWrapper<User> workerQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(parm.getPhoneNumber())){
            workerQueryWrapper.lambda().like(User::getPhoneNumber,parm.getPhoneNumber());
        }
        if (StringUtils.isNotEmpty(parm.getFullName())){
            workerQueryWrapper.lambda().like(User::getFullName,parm.getFullName());
        }
        return this.page(workerPage, workerQueryWrapper);
    }

    //新增物业人员
    @Override
    public boolean saveWorker(User user) {
        //确保用户名唯一
        if (StringUtils.isNotEmpty(user.getUsername())){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getUsername, user.getUsername());
            User selectOne = baseMapper.selectOne(wrapper);
            if (selectOne != null){
                return false;
            }
        }
        //对密码进行加密
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        //设置工作人员不为管理员
        user.setIsAdmin("0");
        int result = baseMapper.insert(user);
        if (result > 0){
            return true;
        }
        return false;
    }

    //编辑物业人员
    @Override
    public boolean updateWorker(User user) {
        //确保用户名唯一
        if (StringUtils.isNotEmpty(user.getUsername())){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getUsername, user.getUsername());
            Long count = baseMapper.selectCount(wrapper);
            if(count > 0){
                return false;
            }
        }
        //密码加密
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        int result = baseMapper.updateById(user);
        if (result > 0){
            return true;
        }
        return false;
    }

    //删除物业工作人员
    @Override
    @Transactional
    public boolean removeWorker(Integer userId) {
        //先根据id查询当前账号是否停用
        User userInfo = baseMapper.selectById(userId);
        if (userInfo.getIsStatus().equals("1")){
            //停用后执行删除
            baseMapper.deleteById(userId);
            //同时清理用户角色关系表中的数据
            //构造条件
            QueryWrapper<UserAndRole> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserAndRole::getUserId,userId);
            userAndRoleMapper.delete(wrapper);
            return true;
        }
        return false;
    }

    //首页物业员工列表
    @Override
    public List<User> getAllWorker() {
        //构造条件构造器 非管理员
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getIsAdmin,"0");
        return baseMapper.selectList(wrapper);
    }

}
