package com.cc.domain.parm;

import lombok.Data;

/**
 * 投诉查询参数
 */
@Data
public class ComplaintParm {

    //投诉人id
    private String liverId;

    //投诉标题
    private String complaintTitle;

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;

}
