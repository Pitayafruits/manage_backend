package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.domain.parm.ResetPassParm;
import com.cc.domain.pojo.Liver;
import com.cc.domain.pojo.LiverAndParking;
import com.cc.domain.parm.AssignHouseParm;
import com.cc.domain.parm.LiverParm;
import com.cc.domain.vo.ResultVo;
import com.cc.service.LiverService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 业主控制器
 */
@RestController
@RequestMapping("/liver")
public class LiverController {

    @Autowired
    private LiverService liverService;

    //新增
    @PostMapping
    @PreAuthorize("hasAuthority('liver:liver:add')")
    public ResultVo addLiver(@RequestBody Liver liver) {
        boolean phone = liverService.PhoneIsExist(liver.getLiverPhone());
        if(!phone){
            return ResultUtils.error("添加失败，该手机号已被绑定！");
        }
        boolean flag = liverService.saveLiverAndRole(liver);
        if (flag) {
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("该用户名已经被占用!");
    }

    //分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('liver:liver:list')")
    public ResultVo getLiverList(LiverParm liverParm) {
        //构造分页对象
        IPage<Liver> page = new Page<>();
        page.setCurrent(liverParm.getCurrentPage());
        page.setSize(liverParm.getPageSize());
        IPage<Liver> liverList = liverService.getLiverList(page, liverParm.getLiverName(), liverParm.getLiverPhone());
        return ResultUtils.success("查询成功!", liverList);
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('liver:liver:edit')")
    public ResultVo editLiver(@RequestBody Liver liver) {
        boolean phone = liverService.PhoneIsExist(liver.getLiverPhone());
        if(!phone){
            return ResultUtils.error("编辑失败，该手机号已被绑定！");
        }
        boolean flag = liverService.editLiver(liver);
        if (flag) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败，登录名已被占用!");
    }

    //编辑时的查询
    @GetMapping("/getLiverById")
    public ResultVo getLiverById(Liver liver) {
        Liver liverInfo = liverService.getLiver(liver.getLiverId());
        return ResultUtils.success("查询成功!", liverInfo);
    }

    //业主分配房屋
    @PostMapping("/assignLiverHouse")
    @PreAuthorize("hasAuthority('liver:liver:avgHouse')")
    public ResultVo assignSaveHouse(@RequestBody AssignHouseParm assignHouseParm) {
        boolean flag = liverService.assignHouse(assignHouseParm);
        if (flag) {
            return ResultUtils.success("分配成功!");
        }
        return ResultUtils.error("分配失败!");
    }

    //业主分配车位
    @PostMapping("/assignLiverPark")
    @PreAuthorize("hasAuthority('liver:liver:avgParking')")
    public ResultVo assignSavePark(@RequestBody LiverAndParking liverAndParking) {
        boolean flag = liverService.assignPark(liverAndParking);
        if (flag) {
            return ResultUtils.success("分配成功!");
        }
        return ResultUtils.error("分配失败!");
    }

    //退房
    @PostMapping("/backHouse")
    @PreAuthorize("hasAuthority('liver:liver:returnHouse')")
    public ResultVo backHouse(@RequestBody AssignHouseParm assignHouseParm) {
        boolean flag = liverService.returnHouse(assignHouseParm);
        if (flag) {
            return ResultUtils.success("退房成功!");
        }
        return ResultUtils.error("退房失败，该业主水电费未缴清!");
    }

    //退车位
    @PostMapping("/backParking")
    @PreAuthorize("hasAuthority('liver:liver:returnParking')")
    public ResultVo backParking(@RequestBody LiverAndParking liverAndParking) {
        boolean flag = liverService.returnParking(liverAndParking);
        if (flag) {
            return ResultUtils.success("退车位成功!");
        }
        return ResultUtils.error("退车位失败，该业主车位费未缴清!");
    }

    //删除业主
    @DeleteMapping("/{liverId}")
    @PreAuthorize("hasAuthority('liver:liver:delete')")
    public ResultVo deleteLiver(@PathVariable("liverId") Integer liverId) {
        boolean flag = liverService.removeLiver(liverId);
        if (flag) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败，请查看该业主是否还有绑定数据!");
    }

    //统计业主数量
    @GetMapping("/getLiverNum")
    public ResultVo getLiverNum() {
        long liverCount = liverService.count();
        return ResultUtils.success("查询成功！", liverCount);
    }

    //发送短信
    @PostMapping("/sendMsg")
    public ResultVo sendMsg(@RequestBody ResetPassParm resetPassParm) {
        String phoneNum = resetPassParm.getPhone();
        //先查询该手机号是否有绑定业主
        boolean liverInfo = liverService.findByPhone(phoneNum);
        if (liverInfo) {
            if (StringUtils.isNotEmpty(phoneNum)) {
                liverService.sendMessage(phoneNum);
                return ResultUtils.success("发送成功！");
            }
            return ResultUtils.error("请输入正确的手机号！");
        }
        return ResultUtils.error("该手机号无绑定业主信息，请检查是否输入正确！");
    }

    //重置密码
    @PostMapping("/resetPassword")
    public ResultVo resetPassword(@RequestBody ResetPassParm resetPassParm) {
        boolean flag = liverService.resetPass(resetPassParm);
        if (flag) {
            return ResultUtils.success("重置成功！");
        }
        return ResultUtils.error("验证码错误，重置失败！");
    }

}
