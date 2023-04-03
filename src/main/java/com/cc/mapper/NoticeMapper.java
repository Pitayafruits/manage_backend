package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 公告DAO层
 */
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {

    //分页查询
    IPage<Notice> getList(IPage<Notice> page,@Param("fullName")String fullName,@Param("noticeTitle")String noticeTitle);

    //首页分页查询
    IPage<Notice> getIndexNoticeList(IPage<Notice> page);
}
