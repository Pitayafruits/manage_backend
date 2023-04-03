package com.cc.domain.parm;

import lombok.Data;

/**
 *单元列表查询实体
 */
@Data
public class UnitParm {

    //楼宇名称
    private String buildingName;

    //单元名称
    private String unitName;

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;
}
