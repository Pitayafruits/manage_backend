package com.cc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.pojo.CommunityInfo;

/**
 * 小区基本信息service层
 */
public interface CommunityInfoService extends IService<CommunityInfo> {

    //修改小区基本信息
    boolean updateInfo(CommunityInfo communityInfo);

}
