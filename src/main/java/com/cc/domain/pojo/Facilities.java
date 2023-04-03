package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 小区周边设施实体类
 */
@Data
@TableName("comm_facilities")
public class Facilities implements Serializable {

    //设置主键自增
    @TableId(type = IdType.AUTO)
    private Integer faId;

    //设施名称
    private String faName;

    //负责人
    private String faPerson;

    //联系电话
    private Long phoneNumber;

    //简介
    private String faText;

}
