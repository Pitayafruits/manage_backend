package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.parm.ResetPassParm;
import com.cc.domain.pojo.*;
import com.cc.domain.parm.AssignHouseParm;
import com.cc.mapper.*;
import com.cc.service.LiverService;
import com.cc.utils.SMSUtils;
import com.cc.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 业主service实现层
 */
@Service
public class LiverServiceImpl extends ServiceImpl<LiverMapper, Liver> implements LiverService {

    @Autowired
    private LiverAndRoleMapper liverAndRoleMapper;

    @Autowired
    private LiverAndHouseMapper liverAndHouseMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private LiverAndParkingMapper liverAndParkingMapper;

    @Autowired
    private ParkingMapper parkingMapper;

    @Autowired
    private  WaterMapper waterMapper;

    @Autowired
    private ElectricMapper electricMapper;

    @Autowired
    private ParkMapper parkMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    //添加
    @Override
    @Transactional
    public boolean saveLiverAndRole(Liver liver) {
        //查询登陆账号是否被占用
        QueryWrapper<Liver> liverWrapper = new QueryWrapper<>();
        liverWrapper.lambda().eq(Liver::getUsername, liver.getUsername());
        Liver ansLiver = baseMapper.selectOne(liverWrapper);
        if (ansLiver != null) {
            return false;
        }
        //保存业主信息
        //密码加密
        if (StringUtils.isNotEmpty(liver.getPassword())) {
            liver.setPassword(passwordEncoder.encode(liver.getPassword()));
        }
        int saveCount = baseMapper.insert(liver);
        if (saveCount > 0) {
            //维护角色信息
            LiverAndRole liverAndRole = new LiverAndRole();
            liverAndRole.setLiverId(liver.getLiverId());
            liverAndRole.setRoleId(liver.getRoleId());
            liverAndRoleMapper.insert(liverAndRole);
            return true;
        }
        return false;
    }

    //分页查询
    @Override
    public IPage<Liver> getLiverList(IPage<Liver> page, String liverName, String liverPhone) {
        return baseMapper.getLiverList(page, liverName, liverPhone);
    }

    //编辑
    @Override
    @Transactional
    public boolean editLiver(Liver liver) {
        //查询登陆账号是否被占用
        QueryWrapper<Liver> liverWrapper = new QueryWrapper<>();
        liverWrapper.lambda().eq(Liver::getUsername, liver.getUsername());
        Liver ansLiver = baseMapper.selectOne(liverWrapper);
        if (ansLiver != null) {
            return false;
        }
        //更新业主表
        int count = baseMapper.updateById(liver);
        if (count > 0) {
            //删除业主角色表关联的数据
            //构造条件
            QueryWrapper<LiverAndRole> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(LiverAndRole::getLiverId, liver.getLiverId());
            liverAndRoleMapper.delete(wrapper);
            //插入新的业主角色关系
            LiverAndRole liverAndRole = new LiverAndRole();
            liverAndRole.setRoleId(liver.getRoleId());
            liverAndRole.setLiverId(liver.getLiverId());
            liverAndRoleMapper.insert(liverAndRole);
            return true;
        }
        return false;
    }

    //编辑时的查询
    @Override
    public Liver getLiver(Integer liverId) {
        return baseMapper.getLiver(liverId);
    }

    //分配房屋
    @Override
    @Transactional
    public boolean assignHouse(AssignHouseParm assignHouseParm) {
        //保存业主房屋关系
        LiverAndHouse liverAndHouse = new LiverAndHouse();
        liverAndHouse.setLiverId(assignHouseParm.getLiverId());
        liverAndHouse.setHouseId(assignHouseParm.getHouseId());
        liverAndHouse.setLiverHouseStatus("0");
        int count = liverAndHouseMapper.insert(liverAndHouse);
        if (count > 0) {
            //更新房屋使用状态
            House house = new House();
            house.setHouseId(assignHouseParm.getHouseId());
            house.setHouseStatus("1");
            houseMapper.updateById(house);
            return true;
        }
        return false;
    }

    //分配车位
    @Override
    @Transactional
    public boolean assignPark(LiverAndParking liverAndParking) {
        //数据存储到业主车位表
        liverAndParking.setLiverParkingStatus("0");
        int count = liverAndParkingMapper.insert(liverAndParking);
        if (count > 0){
            //修改车位使用状态
            Parking parking = new Parking();
            parking.setParkId(liverAndParking.getParkId());
            parking.setParkStatus("1");
            parkingMapper.updateById(parking);
            return true;
        }
        return false;
    }

    //退房
    @Override
    @Transactional
    public boolean returnHouse(AssignHouseParm assignHouseParm) {
        //先查询水电费是否缴清
        QueryWrapper<Water> waterWrapper = new QueryWrapper<>();
        //水费查询条件
        waterWrapper.lambda().eq(Water::getLiverId,assignHouseParm.getLiverId())
                .eq(Water::getHouseId,assignHouseParm.getHouseId())
                .eq(Water::getWaterStatus,"0");
        Long waterCount = waterMapper.selectCount(waterWrapper);
        //电费查询条件
        QueryWrapper<Electric> electricWrapper = new QueryWrapper<>();
        electricWrapper.lambda().eq(Electric::getLiverId,assignHouseParm.getLiverId())
                .eq(Electric::getHouseId,assignHouseParm.getHouseId())
                .eq(Electric::getElectricStatus,"0");
        Long electricCount = electricMapper.selectCount(electricWrapper);
        //水电费已经缴清
        if (waterCount <= 0 && electricCount <= 0){
            //解绑房屋业主关系
            LiverAndHouse liverAndHouse = new LiverAndHouse();
            liverAndHouse.setLiverHouseStatus("1");
            QueryWrapper<LiverAndHouse> liverAndHouseWrapper = new QueryWrapper<>();
            liverAndHouseWrapper.lambda().eq(LiverAndHouse::getLiverId,assignHouseParm.getLiverId())
                    .eq(LiverAndHouse::getHouseId,assignHouseParm.getHouseId());
            liverAndHouseMapper.update(liverAndHouse,liverAndHouseWrapper);
            // 更新当前房屋状态为未使用
            House house = new House();
            house.setHouseStatus("0");
            house.setHouseId(assignHouseParm.getHouseId());
            houseMapper.updateById(house);
            return true;
        }
        return false;
    }

    //退车位
    @Override
    @Transactional
    public boolean returnParking(LiverAndParking liverAndParking) {
        //查询停车费是否缴清
        QueryWrapper<Park> parkWrapper = new QueryWrapper<>();
        parkWrapper.lambda().eq(Park::getParkId,liverAndParking.getParkId())
                .eq(Park::getLiverId,liverAndParking.getLiverId())
                .eq(Park::getParkingStatus,"0");
        Long count = parkMapper.selectCount(parkWrapper);
        //车费已经缴清
        if(count <= 0){
            //解绑业主和车位的关系
            LiverAndParking liverAndPark = new LiverAndParking();
            liverAndPark.setLiverParkingStatus("1");
            QueryWrapper<LiverAndParking> liverAndParkingWrapper = new QueryWrapper<>();
            liverAndParkingWrapper.lambda().eq(LiverAndParking::getParkId,liverAndParking.getParkId())
                    .eq(LiverAndParking::getLiverId,liverAndParking.getLiverId());
            liverAndParkingMapper.update(liverAndPark,liverAndParkingWrapper);
            //更新车位的使用状态
            Parking parking = new Parking();
            parking.setParkStatus("0");
            parking.setParkId(liverAndParking.getParkId());
            parkingMapper.updateById(parking);
            return true;
        }
        return false;
    }

    //根据用户名查询用户信息
    @Override
    public Liver loadUser(String username) {
        //构造查询条件
        QueryWrapper<Liver> wrapper =  new QueryWrapper<>();
        wrapper.lambda().eq(Liver::getUsername,username);
        return baseMapper.selectOne(wrapper);
    }

    //删除业主
    @Override
    public boolean removeLiver(Integer liverId) {
        //查询业主车位是否已退
        QueryWrapper<LiverAndParking> parkingWrapper = new QueryWrapper<>();
        parkingWrapper.lambda().eq(LiverAndParking::getLiverId,liverId);
        Long parkingCount = liverAndParkingMapper.selectCount(parkingWrapper);
        if (parkingCount > 0){
            return false;
        }
        //查询业主房屋是否已退
        QueryWrapper<LiverAndHouse> houseWrapper = new QueryWrapper<>();
        houseWrapper.lambda().eq(LiverAndHouse::getLiverId,liverId);
        Long houseCount = liverAndHouseMapper.selectCount(houseWrapper);
        if (houseCount > 0){
            return false;
        }
        //查询业主电费是否已缴清
        QueryWrapper<Electric> electricWrapper = new QueryWrapper<>();
        electricWrapper.lambda().eq(Electric::getLiverId,liverId);
        Long electricCount = electricMapper.selectCount(electricWrapper);
        if(electricCount > 0){
            return false;
        }
        //查询业主水费是否已缴清
        QueryWrapper<Water> waterWrapper = new QueryWrapper<>();
        waterWrapper.lambda().eq(Water::getLiverId,liverId);
        Long waterCount = waterMapper.selectCount(waterWrapper);
        if (waterCount > 0){
            return false;
        }
        int count = baseMapper.deleteById(liverId);
        //删除业成功也要删除业主角色关联
        if(count > 0){
            QueryWrapper<LiverAndRole> liverAndRoleWrapper = new QueryWrapper<>();
            liverAndRoleWrapper.lambda().eq(LiverAndRole::getLiverId,liverId);
            int delete = liverAndRoleMapper.delete(liverAndRoleWrapper);
            return delete > 0;
        }
        return false;
    }

    //发送短信
    @Override
    public void sendMessage(String phoneNum) {
        //生成验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        //调用腾讯云提供的短信服务API
        SMSUtils.sendMessage(phoneNum, code.toString(),SMSUtils.templateId_validate);
        //将验证码存到redis中
        redisTemplate.opsForValue().set(phoneNum,code.toString(),5, TimeUnit.MINUTES);
    }

    //重置密码
    @Override
    public boolean resetPass(ResetPassParm resetPassParm) {
        //获得输入的手机号和验证码
        String inputPhone = resetPassParm.getPhone();
        String inputCode = resetPassParm.getCode();
        //取出redis中存入的验证码
        Object codeInRedis = redisTemplate.opsForValue().get(inputPhone);
        //比对用户输入的和Redis中的验证码
        if(codeInRedis != null && inputCode.equals(codeInRedis)){
            //校验成功则重置密码
            //通过电话号码查询业主
            QueryWrapper<Liver> liverQueryWrapper = new QueryWrapper<>();
            liverQueryWrapper.lambda().eq(Liver::getLiverPhone,inputPhone);
            Liver liveByPhone = baseMapper.selectOne(liverQueryWrapper);
            //如果该用户存在，则修改密码
            if(liveByPhone != null){
                //生成新的随机密码
                String newPass = ValidateCodeUtils.generateValidateCode(6).toString();
                //修改密码
                liveByPhone.setPassword(passwordEncoder.encode(newPass));
                int count = baseMapper.updateById(liveByPhone);
                //修改成功
                if(count > 0){
                    //重置的新密码发送短信通知用户
                    SMSUtils.sendMessage(inputPhone,newPass,SMSUtils.templateId_password);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean findByPhone(String parmPhone) {
        //构造条件构造器
        QueryWrapper<Liver> liverQueryWrapper = new QueryWrapper<>();
        liverQueryWrapper.lambda().eq(Liver::getLiverPhone,parmPhone);
        Liver liverInfo = baseMapper.selectOne(liverQueryWrapper);
        if(liverInfo != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean PhoneIsExist(String liverPhone) {
        //构造条件构造器
        QueryWrapper<Liver> liverQueryWrapper = new QueryWrapper<>();
        liverQueryWrapper.lambda().eq(Liver::getLiverPhone,liverPhone);
        Liver liverInfo = baseMapper.selectOne(liverQueryWrapper);
        if(liverInfo == null){
            return true;
        }
        return false;
    }

}
