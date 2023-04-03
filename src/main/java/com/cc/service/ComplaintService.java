package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.parm.ComplaintParm;
import com.cc.domain.pojo.Complaint;

import java.util.Map;

/**
 * 投诉service层
 */
public interface ComplaintService extends IService<Complaint> {

    //分页查询
    IPage<Complaint> getComplaintList(ComplaintParm complaintParm);

    //添加
    boolean saveComplaint(Complaint complaint);

    //编辑
    boolean updateComplaint(Complaint complaint);

    //删除
    boolean removeComplaint(Integer complaintId);

    //我的投诉
    IPage<Complaint> getMyComplaintList(ComplaintParm complaintParm);

    //处理投诉
    boolean sloveComplaint(Complaint complaint);

    //投诉折线图
    Map<String, Object> listforMonths();

}
