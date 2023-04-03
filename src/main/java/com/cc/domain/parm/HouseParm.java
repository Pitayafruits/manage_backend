package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 房屋结果返回
 */
@Data
public class HouseParm implements Serializable {

    //楼宇名称
    private String buildingName;

    //单元名称
    private String unitName;

    //房屋编号
    private String houseNum;

    //使用状态 0：未使用 1：已使用
    private String houseStatus;

    //当前页
    private Long currentPage;

    //页容量
    private Long pageSize;
}
