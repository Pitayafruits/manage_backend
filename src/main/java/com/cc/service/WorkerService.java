package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.User;
import com.cc.domain.parm.UserParm;

import java.util.List;

/**
 * 物业人员service层
 */
public interface WorkerService extends IService<User> {

    //分页查询物业人员列表
    IPage<User> list(UserParm parm);

    //新增物业人员
    boolean saveWorker(User user);

    //编辑物业人员
    boolean updateWorker(User user);

    //删除员工
    boolean removeWorker(Integer userId);

    //首页物业员工列表
    List<User> getAllWorker();
}
