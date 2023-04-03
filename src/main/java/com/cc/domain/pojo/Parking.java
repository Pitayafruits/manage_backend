package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 车位实体
 */
@Data
@TableName("parking_list")
public class Parking implements Serializable {

    //车位id
    @TableId(type = IdType.AUTO)
    private Integer parkId;

    //车位类型 0->地上 1->地下
    private String parkType;

    //车位名称
    private String parkName;

    //车位使用状态 0->未使用 1->已使用
    private String parkStatus;

    //车位序号
    private Integer parkNumber;
}
