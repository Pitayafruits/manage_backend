package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.parm.ComplaintParm;
import com.cc.domain.pojo.Complaint;
import com.cc.mapper.ComplaintMapper;
import com.cc.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 投诉serviceImpl层
 */
@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements ComplaintService {

    @Autowired
    private ComplaintService complaintService;

    //分页查询
    @Override
    public IPage<Complaint> getComplaintList(ComplaintParm complaintParm) {
        //构造分页条件
        IPage<Complaint> page = new Page<>();
        page.setCurrent(complaintParm.getCurrentPage());
        page.setSize(complaintParm.getPageSize());
        return baseMapper.getList(page, complaintParm.getComplaintTitle());
    }

    //添加
    @Override
    public boolean saveComplaint(Complaint complaint) {
        //设置投诉状态 0->未处理
        complaint.setSloveStatus("0");
        //设置投诉时间
        complaint.setComplaintTime(new Date());
        //保存
        int count = baseMapper.insert(complaint);
        return count > 0;
    }

    //编辑
    @Override
    public boolean updateComplaint(Complaint complaint) {
        //判断该投诉是否已被处理
        if (complaint.getSloveStatus().equals("1")) {
            return false;
        }
        int count = baseMapper.updateById(complaint);
        return count > 0;
    }

    //删除
    @Override
    public boolean removeComplaint(Integer complaintId) {
        //构造查询条件
        QueryWrapper<Complaint> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Complaint::getComplaintId, complaintId)
                .eq(Complaint::getSloveStatus, "0");
        Long count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            //投诉没被处理，才可以删除
            baseMapper.deleteById(complaintId);
            return true;
        }
        return false;
    }

    //我的投诉
    @Override
    public IPage<Complaint> getMyComplaintList(ComplaintParm complaintParm) {
        //构造查询条件
        QueryWrapper<Complaint> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(Complaint::getComplaintTitle, complaintParm.getComplaintTitle())
                .eq(Complaint::getLiverId, complaintParm.getLiverId());
        //构造分页条件
        IPage<Complaint> page = new Page<>();
        page.setCurrent(complaintParm.getCurrentPage());
        page.setSize(complaintParm.getPageSize());
        return baseMapper.selectPage(page, wrapper);
    }

    //处理投诉
    @Override
    public boolean sloveComplaint(Complaint complaint) {
        //设置投诉状态->已处理
        complaint.setSloveStatus("1");
        int count = baseMapper.updateById(complaint);
        return count > 0;
    }

    //投诉折线图
    @Override
    public Map<String, Object> listforMonths() {
        //创建返回对象map
        Map<String,Object> resultMap = new HashMap<>();
        //处理x轴日期
        //创建日期集合
        List<String> complaintMonths = new ArrayList<>();
        //获得日历对象
        Calendar calendar = Calendar.getInstance();
        //计算过去一年的六个月
        calendar.add(Calendar.MONTH, -6); //当前时间往前推6个月
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.MONTH,1); //当前时间往后推一个月日期
            Date date = calendar.getTime();
            complaintMonths.add(new SimpleDateFormat("yyyy-MM").format(date));
        }
        resultMap.put("complaintMonths",complaintMonths);
        //处理y轴投诉数量
        //创建投诉数量集合
        List<Integer> complaintCount = new ArrayList<>();
        //根据月份查询
        for (String complaintMonth : complaintMonths) {
            String afterDate = complaintMonth + "-1";
            String beforeDate = complaintMonth + "-31";
            Integer comCount = baseMapper.findComCountByMonths(beforeDate,afterDate);
            complaintCount.add(comCount);
        }
        resultMap.put("complaintCount",complaintCount);
        return resultMap;
    }

}
