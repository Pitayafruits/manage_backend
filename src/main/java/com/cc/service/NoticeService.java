package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.domain.parm.IndexNoticeParm;
import com.cc.domain.parm.NoticeParm;
import com.cc.domain.pojo.Notice;

/**
 * 公告service层
 */
public interface NoticeService extends IService<Notice> {

    //分页查询
    IPage<Notice> getList(NoticeParm noticeParm);

    //添加
    boolean saveNotice(Notice notice);

    //首页公告分页查询
    IPage<Notice> getIndexNoticeList(IndexNoticeParm indexNoticeParm);
}
