package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 维权实体类
 */
@Data
@TableName("def_complaint")
public class Complaint implements Serializable {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer complaintId;

    //投诉人id(业主id)
    private Integer liverId;

    //投诉标题
    private String complaintTitle;

    //投诉内容
    private String complaintText;

    //投诉时间
    private Date complaintTime;

    //处理状态  0：未处理 1：已处理
    private String sloveStatus;

    //处理回复
    private String complaintReply;

    //投诉人姓名
    @TableField(exist = false)
    private String liverName;

    //投诉人联系方式
    @TableField(exist = false)
    private String liverPhone;

}
