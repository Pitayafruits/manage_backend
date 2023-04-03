package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 房屋实体
 */
@Data
@TableName("house_list")
public class House implements Serializable {

    //房屋id
    @TableId(type = IdType.AUTO)
    private Integer houseId;

    //楼宇id
    @TableField(exist = false)
    private Long buildingId;

    //单元id
    private Integer unitId;

    //房屋编号
    private String houseNum;

    //楼宇名称
    @TableField(exist = false)
    private String buildingName;

    //单元名称
    @TableField(exist = false)
    private String unitName;

    //房屋面积
    private String houseArea;

    //使用状态0：未使用 1：已使用
    private String houseStatus;
}
