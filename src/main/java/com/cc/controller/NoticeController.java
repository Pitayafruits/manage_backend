package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.parm.IndexNoticeParm;
import com.cc.domain.parm.NoticeParm;
import com.cc.domain.pojo.Notice;
import com.cc.domain.vo.ResultVo;
import com.cc.service.NoticeService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 公告控制器
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('community:notice:list')")
    public ResultVo getNoticeList(NoticeParm noticeParm){
        IPage<Notice> noticeList = noticeService.getList(noticeParm);
        return ResultUtils.success("查询成功！",noticeList);
    }

    //添加
    @PostMapping
    @PreAuthorize("hasAuthority('community:notice:add')")
    public ResultVo addNotice(@RequestBody Notice notice){
        boolean flag = noticeService.saveNotice(notice);
        if(flag){
            return ResultUtils.success("发布成功！");
        }
        return ResultUtils.error("发布失败！");
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('community:notice:edit')")
    public ResultVo editNotice(@RequestBody Notice notice){
        boolean flag = noticeService.updateById(notice);
        if(flag){
            return ResultUtils.success("修改成功！");
        }
        return ResultUtils.error("修改失败！");
    }

    //删除
    @DeleteMapping("/{noticeId}")
    @PreAuthorize("hasAuthority('community:notice:delete')")
    public ResultVo deleteNotice(@PathVariable("noticeId") Integer noticeId){
        boolean flag = noticeService.removeById(noticeId);
        if(flag){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败！");
    }

    //首页分压查询
    @GetMapping("/getNoticeList")
    public ResultVo getNoticeList(IndexNoticeParm indexNoticeParm){
        IPage<Notice> indexNoticeList = noticeService.getIndexNoticeList(indexNoticeParm);
        return ResultUtils.success("查询成功！",indexNoticeList);
    }
}
