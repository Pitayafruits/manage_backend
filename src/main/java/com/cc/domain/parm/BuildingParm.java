package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 楼宇结果返回
 */
@Data
public class BuildingParm implements Serializable {
    //楼宇名称
    private String buildingName;

    //楼宇类型 0:普通房 1:电梯房
    private String buildingType;

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;
}
