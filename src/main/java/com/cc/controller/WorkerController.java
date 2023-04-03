package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.User;
import com.cc.domain.parm.UserParm;
import com.cc.domain.vo.ResultVo;
import com.cc.service.WorkerService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物业人员Controller
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    //首页物业员工列表
    @GetMapping("/workerList")
    public ResultVo workerList(){
        List<User> workerList = workerService.getAllWorker();
        return ResultUtils.success("查询成功！",workerList);
    }

    //查询物业员工列表
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:worker:list')")
    public ResultVo list(UserParm parm){
        IPage<User> workerList = workerService.list(parm);
        //前端密码脱敏处理
        workerList.getRecords().stream().forEach(item -> item.setPassword("********"));
        return ResultUtils.success("查询成功！",workerList);
    }

    //新增物业员工
    @PostMapping
    @PreAuthorize("hasAuthority('user:worker:add')")
    public ResultVo addWorker(@RequestBody User user){
        boolean flag = workerService.saveWorker(user);
        if (flag){
            return ResultUtils.success("添加成功！");
        }
        return ResultUtils.error("添加失败，用户名已被占用!");
    }

    //编辑物业员工
    @PutMapping
    @PreAuthorize("hasAuthority('user:worker:edit')")
    public ResultVo editWorker(@RequestBody User user){
        boolean flag = workerService.updateWorker(user);
        if (flag){
            return ResultUtils.success("修改成功！");
        }
        return ResultUtils.error("修改失败，用户名已经被占用!");
    }

    //删除员工
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('user:worker:delete')")
    public ResultVo deleteWorker(@PathVariable("userId") Integer userId){
        boolean flag = workerService.removeWorker(userId);
        if (flag){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败，请先停用该账号!");
    }
}
