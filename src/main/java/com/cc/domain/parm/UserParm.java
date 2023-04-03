package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 工作人员列表查询实体
 */
@Data
public class UserParm implements Serializable {

    //每页显示的条数
    private Long pageSize;

    //当前页码
    private Long currentPage;

    //真实姓名
    private String fullName;

    //电话
    private String phoneNumber;

}
