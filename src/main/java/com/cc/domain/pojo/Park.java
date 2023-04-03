package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 停车费实体
 */
@Data
@TableName("pay_parking")
public class Park implements Serializable {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer parkingId;

    //业主id
    private Integer liverId;

    //车位id
    private Integer parkId;

    //缴费年月
    private String parkingDate;

    //缴费金额
    private BigDecimal parkingMoney;

    //缴费状态 0：未缴费 1：已缴费
    private String parkingStatus;

    //缴费时间
    private Date parkingTime;

    //业主姓名
    @TableField(exist = false)
    private String liverName;

    //业主电话
    @TableField(exist = false)
    private String liverPhone;

    //车位名称
    @TableField(exist = false)
    private String parkName;

    //车位类型（0地上 1地下）
    @TableField(exist = false)
    private String parkType;
}
