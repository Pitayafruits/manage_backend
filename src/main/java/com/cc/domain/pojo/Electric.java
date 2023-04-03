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
 * 电费实体类
 */
@Data
@TableName("pay_electric")
public class Electric implements Serializable {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer electricId;

    //业主id
    private Integer liverId;

    //房屋id
    private Integer houseId;

    //缴费年月
    private String electricDate;

    //缴费金额
    private BigDecimal electricMoney;

    //用电额度
    private String electricNum;

    //缴费状态 0：未缴费 1：已缴费
    private String electricStatus;

    //缴费时间
    private Date electricTime;

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
