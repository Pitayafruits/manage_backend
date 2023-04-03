package com.cc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.domain.parm.IndexNoticeParm;
import com.cc.domain.parm.NoticeParm;
import com.cc.domain.pojo.Notice;
import com.cc.mapper.NoticeMapper;
import com.cc.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 公告service实现层
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper,Notice> implements NoticeService {

    //分页查询
    @Override
    public IPage<Notice> getList(NoticeParm noticeParm) {
        //构造分页对象
        IPage<Notice> page = new Page<>();
        page.setCurrent(noticeParm.getCurrentPage());
        page.setSize(noticeParm.getPageSize());
        return baseMapper.getList(page, noticeParm.getFullName(),noticeParm.getNoticeTitle());
    }

    //添加
    @Override
    public boolean saveNotice(Notice notice) {
        //设置当前时间
        notice.setNoticeTime(new Date());
        int count = baseMapper.insert(notice);
        return count > 0;
    }

    //首页公告分页查询
    @Override
    public IPage<Notice> getIndexNoticeList(IndexNoticeParm indexNoticeParm) {
        //构造分页对象
        IPage<Notice> page = new Page<>();
        page.setCurrent(indexNoticeParm.getCurrentPage());
        page.setSize(indexNoticeParm.getPageSize());
        return baseMapper.getIndexNoticeList(page);
    }
}
