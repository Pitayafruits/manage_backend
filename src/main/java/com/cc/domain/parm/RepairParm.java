package com.cc.domain.parm;

import lombok.Data;

/**
 * 维修查询参数
 */
@Data
public class RepairParm {

    //报修人id
    private Integer liverId;

    //报修人姓名
    private String liverName;

    //报修内容
    private String repairText;

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;
}
