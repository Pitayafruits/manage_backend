package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 公告请求参数
 */
@Data
public class NoticeParm implements Serializable {

    //公告标题
    private String noticeTitle;

    //发布人id
    private Integer userId;

    //发布人姓名
    private String fullName;

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;
}
