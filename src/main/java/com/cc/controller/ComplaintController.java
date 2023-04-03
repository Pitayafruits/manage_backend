package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.parm.ComplaintParm;
import com.cc.domain.pojo.Complaint;
import com.cc.domain.vo.ResultVo;
import com.cc.service.ComplaintService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 投诉管理控制器
 */
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    //分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('complaint:complaintList:list')")
    public ResultVo getList(ComplaintParm complaintParm){
        IPage<Complaint> complaintList = complaintService.getComplaintList(complaintParm);
        return ResultUtils.success("查询成功！",complaintList);
    }

    //添加
    @PostMapping
    @PreAuthorize("hasAuthority('complaint:myComplaint:add')")
    public ResultVo addComplaint(@RequestBody Complaint complaint){
        boolean flag = complaintService.saveComplaint(complaint);
        if(flag){
            return ResultUtils.success("添加成功！");
        }
        return ResultUtils.error("添加失败！");
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('complaint:myComplaint:edit')")
    public ResultVo editComplaint(@RequestBody Complaint complaint){
        boolean flag = complaintService.updateComplaint(complaint);
        if(flag){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("不可编辑，该投诉已被处理！");
    }

    //删除
    @DeleteMapping("/{complaintId}")
    @PreAuthorize("hasAuthority('complaint:myComplaint:delete')")
    public ResultVo deleteComplaint(@PathVariable("complaintId") Integer complaintId){
        boolean flag = complaintService.removeComplaint(complaintId);
        if (flag){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("不可删除，该投诉已被处理！");
    }

    //我的投诉
    @GetMapping("/myComplaint")
    @PreAuthorize("hasAuthority('complaint:myComplaint:list')")
    public ResultVo getMyList(ComplaintParm complaintParm){
        IPage<Complaint> complaintList = complaintService.getMyComplaintList(complaintParm);
        return ResultUtils.success("查询成功！",complaintList);
    }

    //处理投诉
    @PutMapping("/overComplaint")
    @PreAuthorize("hasAuthority('complaint:complaintList:slove')")
    public ResultVo overComplaint(@RequestBody Complaint complaint){
        boolean flag = complaintService.sloveComplaint(complaint);
        if (flag){
            return ResultUtils.success("处理成功！");
        }
        return ResultUtils.error("处理失败！");
    }
}
