package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.parm.ResetPassParm;
import com.cc.domain.pojo.Liver;
import com.cc.domain.pojo.LiverAndParking;
import com.cc.domain.parm.AssignHouseParm;

/**
 * 业主service层
 */
public interface LiverService extends IService<Liver> {

    //添加
    boolean saveLiverAndRole(Liver liver);

    //分页查询
    IPage<Liver> getLiverList(IPage<Liver> page,String liverName,String liverPhone);

    //编辑
    boolean editLiver(Liver liver);

    //编辑时的查询
    Liver getLiver(Integer liverId);

    //分配房屋
    boolean assignHouse(AssignHouseParm assignHouseParm);

    //分配车位
    boolean assignPark(LiverAndParking liverAndParking);

    //退房
    boolean returnHouse(AssignHouseParm assignHouseParm);

    //退车位
    boolean returnParking(LiverAndParking liverAndParking);

    //根据用户名查询用户信息
    Liver loadUser(String username);

    //删除业主
    boolean removeLiver(Integer liverId);

    //发送短信
    void sendMessage(String phoneNum);

    //重置密码
    boolean resetPass(ResetPassParm resetPassParm);

    //查询手机号是否绑定业主
    boolean findByPhone(String parmPhone);

    //添加和修改时查询手机号是否已被占用
    boolean PhoneIsExist(String liverPhone);
}
