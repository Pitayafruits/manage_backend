package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 业主和房屋实体
 */
@Data
@TableName("liver_house")
public class LiverAndHouse {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer liverHouseId;

    //业主id
    private Integer liverId;

    //房屋id
    private Integer houseId;

    //使用状态 0:入住 1:退房
    private String liverHouseStatus;
}
