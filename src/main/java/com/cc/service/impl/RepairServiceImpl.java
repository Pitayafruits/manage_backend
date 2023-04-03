package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.parm.RepairParm;
import com.cc.domain.pojo.Repair;
import com.cc.mapper.RepairMapper;
import com.cc.service.RepairService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 维修管理ServiceImpl层
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

    //维修列表分页查询
    @Override
    public IPage<Repair> getRepairList(RepairParm repairParm) {
        //构造分页条件
        IPage<Repair> page = new Page<>();
        page.setCurrent(repairParm.getCurrentPage());
        page.setSize(repairParm.getPageSize());
        return baseMapper.getList(page,repairParm.getLiverName());
    }

    //添加
    @Override
    public boolean saveRepair(Repair repair) {
        //设置投诉状态 0->未处理
        repair.setRepairStatus("0");
        //设置投诉时间
        repair.setRepairTime(new Date());
        //保存
        int count = baseMapper.insert(repair);
        return count > 0;
    }

    //编辑
    @Override
    public boolean updateRepair(Repair repair) {
        //判断该投诉是否已被处理
        if (repair.getRepairStatus().equals("1")){
            return false;
        }
        int count = baseMapper.updateById(repair);
        return count > 0;
    }

    //删除
    @Override
    public boolean removeRepair(Integer repairId) {
        //构造查询条件
        QueryWrapper<Repair> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Repair::getRepairId,repairId)
                .eq(Repair::getRepairStatus,"0");
        Long count = baseMapper.selectCount(wrapper);
        if (count > 0){
            //维修没被处理，才可以被删除
            baseMapper.deleteById(repairId);
            return true;
        }
        return false;
    }

    //我的报修
    @Override
    public IPage<Repair> getMyRepairList(RepairParm repairParm) {
        //构造查询条件
        QueryWrapper<Repair> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(Repair::getRepairText,repairParm.getRepairText())
                .eq(Repair::getLiverId,repairParm.getLiverId());
        //构造分页对象
        IPage<Repair> page = new Page<>();
        page.setCurrent(repairParm.getCurrentPage());
        page.setSize(repairParm.getPageSize());
        return baseMapper.selectPage(page,wrapper);
    }

    //处理报修
    @Override
    public boolean sloveRepair(Repair repair) {
        //设置报修状态->已处理
        repair.setRepairStatus("1");
        int count = baseMapper.updateById(repair);
        return count > 0;
    }

    //每月报修柱形图
    @Override
    public Map<String, Object> listForMonths() {
        //创建返回对象map
        Map<String,Object> resultMap = new HashMap<>();
        //处理x轴日期
        //创建日期集合
        List<String> repairMonths = new ArrayList<>();
        //获得日历对象
        Calendar calendar = Calendar.getInstance();
        //计算过去一年的六个月
        calendar.add(Calendar.MONTH, -6); //当前时间往前推6个月
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.MONTH,1); //当前时间往后推一个月日期
            Date date = calendar.getTime();
            repairMonths.add(new SimpleDateFormat("yyyy-MM").format(date));
        }
        resultMap.put("repairMonths",repairMonths);
        //创建投诉数量集合
        List<Integer> repairCount = new ArrayList<>();
        //根据月份查询
        for (String repairMonth : repairMonths) {
            String afterDate = repairMonth + "-1";
            String beforeDate = repairMonth + "-31";
            Integer comCount = baseMapper.findRepCountByMonths(beforeDate,afterDate);
            repairCount.add(comCount);
        }
        resultMap.put("repairCount",repairCount);
        return resultMap;
    }


}
