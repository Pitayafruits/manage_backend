package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 停车费查询参数
 */
@Data
public class LiverParkParm implements Serializable {
    //业主id
    private Integer liverId;

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;
}
