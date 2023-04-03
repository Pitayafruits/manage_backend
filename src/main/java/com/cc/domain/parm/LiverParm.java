package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 业主结果返回
 */
@Data
public class LiverParm implements Serializable {

    //业主姓名
    private String liverName;

    //联系电话
    private String liverPhone;

    //当前页
    private Long currentPage;

    //页容量
    private Long pageSize;
}
