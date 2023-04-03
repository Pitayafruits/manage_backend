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
 * 水费实体
 */
@Data
@TableName("pay_water")
public class Water implements Serializable {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer waterId;

    //业主id
    private Integer liverId;

    //房屋id
    private Integer houseId;

    //缴费年月
    private String waterDate;

    //缴费金额
    private BigDecimal waterMoney;

    //用水额度
    private String waterNum;

    //缴费状态 0：未缴费 1：已缴费
    private String waterStatus;

    //缴费时间
    private Date waterTime;

    //业主姓名
    @TableField(exist = false)
    private String liverName;

    //业主电话
    @TableField(exist = false)
    private String liverPhone;

    //房屋编号
    @TableField(exist = false)
    private String houseNum;

    //单元id
    @TableField(exist = false)
    private Integer unitId;

    //单元名称
    @TableField(exist = false)
    private String unitName;

    //楼宇id
    @TableField(exist = false)
    private Integer buildingId;

    //楼宇名称
    @TableField(exist = false)
    private String buildingName;
}
