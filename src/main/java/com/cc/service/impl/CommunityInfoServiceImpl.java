package com.cc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.pojo.CommunityInfo;
import com.cc.mapper.CommunityInfoMapper;
import com.cc.service.CommunityInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小区基本信息serviceImpl层
 */
@Service
public class CommunityInfoServiceImpl extends ServiceImpl<CommunityInfoMapper, CommunityInfo> implements CommunityInfoService {

    //修改小区基本信息
    @Override
    public boolean updateInfo(CommunityInfo communityInfo) {
        //先查询是否有数据
        List<CommunityInfo> info = baseMapper.selectList(null);
        //如果没有则做插入操作
        if (info.size() == 0){
            int selectNum = baseMapper.insert(communityInfo);
            if (selectNum > 0){
                return true;
            }
        }
        //如果有则做修改操作
        int updateNum = baseMapper.updateById(communityInfo);
        return updateNum > 0;
    }

}
