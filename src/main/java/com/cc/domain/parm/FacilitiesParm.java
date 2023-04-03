package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 设施请求参数
 */
@Data
public class FacilitiesParm implements Serializable {

    //设施名称
    private String faName;

    //负责人
    private String faPerson;

    //联系电话
    private Long phoneNumber;

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;
}
