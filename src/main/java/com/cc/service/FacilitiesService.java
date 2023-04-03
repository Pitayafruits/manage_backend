package com.cc.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.parm.FacilitiesParm;
import com.cc.domain.pojo.Facilities;

/**
 * 小区周边设施service层
 */
public interface FacilitiesService extends IService<Facilities> {

    //添加设施
    boolean saveFacilities(Facilities facilities);

    //分页查询
    IPage<Facilities> getList(FacilitiesParm facilitiesParm);
}
