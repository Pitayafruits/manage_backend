package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 停车费分页参数
 */
@Data
public class ParkParm implements Serializable {
    //车位名称
    private String parkName;

    //业主姓名
    private String liverName;

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;
}
