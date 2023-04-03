package com.cc.domain.parm;

import lombok.Data;

/**
 * 车位管理分类查询结果返回
 */
@Data
public class ParkingParm {
    //车位名称
    private String parkName;

    //车位使用状态 0->未使用 1->已使用
    private String parkStatus;

    //车位类型 0->地上 1->地下
    private String parkType;

    //当前页
    private Long currentPage;

    //页容量
    private Long pageSize;
}
