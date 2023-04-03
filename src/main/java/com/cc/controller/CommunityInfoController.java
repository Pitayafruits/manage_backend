package com.cc.controller;


import com.cc.domain.pojo.CommunityInfo;
import com.cc.domain.vo.ResultVo;
import com.cc.service.CommunityInfoService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小区基本信息Controller
 */
@RestController
@RequestMapping("/community")
public class CommunityInfoController {

    @Autowired
    private CommunityInfoService communityInfoService;

    //首页查询小区基本信息
    @GetMapping("/comminfo")
    public ResultVo getCommInfo(){
        List<CommunityInfo> list = communityInfoService.list();
        if (list != null){
            return ResultUtils.success("查询到小区基本信息",list);
        }
        return ResultUtils.error("未查询到小区基本信息!");
    }

    //查询小区基本信息也用作数据回显
    @GetMapping("/info")
    @PreAuthorize("hasAuthority('community:commInfo:info')")
    public ResultVo showCommInfo() {
        List<CommunityInfo> communityInfoList = communityInfoService.list();
        if (communityInfoList != null && communityInfoList.size() > 0){
            Integer id = communityInfoList.get(0).getId();
            CommunityInfo info = communityInfoService.getById(id);
            return ResultUtils.success("查询成功",info);
        }
        return ResultUtils.error("暂无数据，请添加小区基本信息！");
    }

    //修改小区基本信息
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('community:commInfo:save')")
    public ResultVo updateCommInfo(@RequestBody CommunityInfo communityInfo){
            boolean flag = communityInfoService.updateInfo(communityInfo);
            if (flag) {
                return ResultUtils.success("修改成功");
            }
        return ResultUtils.error("修改失败,小区信息不能为空！");
    }

}
