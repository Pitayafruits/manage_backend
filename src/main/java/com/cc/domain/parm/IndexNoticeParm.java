package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 首页公告分页参数
 */

@Data
public class IndexNoticeParm implements Serializable {
    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;
}
