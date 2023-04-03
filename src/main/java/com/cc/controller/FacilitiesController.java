package com.cc.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.parm.FacilitiesParm;
import com.cc.domain.pojo.Facilities;
import com.cc.domain.vo.ResultVo;
import com.cc.service.FacilitiesService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小区周边设施Controller
 */
@RestController
@RequestMapping("/facilities")
public class FacilitiesController {

    @Autowired
    private FacilitiesService facilitiesService;

    //添加设施
    @PostMapping
    @PreAuthorize("hasAuthority('community:facilities:add')")
    public ResultVo addFacilities(@RequestBody Facilities facilities){
        boolean flag = facilitiesService.saveFacilities(facilities);
        if(flag){
            return ResultUtils.success("添加成功！");
        }
        return ResultUtils.error("添加失败！");
    }

    //分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('community:facilities:list')")
    public ResultVo getFacilitiesList(FacilitiesParm facilitiesParm){
        IPage<Facilities> facilitiesList = facilitiesService.getList(facilitiesParm);
        return ResultUtils.success("查询成功！",facilitiesList);
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('community:facilities:edit')")
    public ResultVo editFacilities(@RequestBody Facilities facilities){
        boolean flag = facilitiesService.updateById(facilities);
        if(flag){
            return ResultUtils.success("修改成功！");
        }
        return ResultUtils.error("修改失败！");
    }

    //删除
    @DeleteMapping("/{faId}")
    @PreAuthorize("hasAuthority('community:facilities:delete')")
    public ResultVo deleteFacilities(@PathVariable("faId")Integer faId){
        boolean flag = facilitiesService.removeById(faId);
        if(flag){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败！");
    }

    //首页查询设施列表
    @GetMapping("/getFacList")
    public ResultVo getIndexFacilitiesList(){
        List<Facilities> facilitiesList = facilitiesService.list();
        return ResultUtils.success("查询成功",facilitiesList);
    }
}
