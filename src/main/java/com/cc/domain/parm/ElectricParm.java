package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 *  电费分页参数
 */
@Data
public class ElectricParm implements Serializable {

    //房屋编号
    private String houseNum;

    //业主姓名
    private String liverName;

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;

}
