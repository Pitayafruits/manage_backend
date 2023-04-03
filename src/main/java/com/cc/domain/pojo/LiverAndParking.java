package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 业主和车位实体
 */
@Data
@TableName("liver_parking")
public class LiverAndParking implements Serializable {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer liverParkingId;

    //业主id
    private Integer liverId;

    //车位id
    private Integer parkId;

    //使用状态 0:使用中 1:解绑
    private String liverParkingStatus;
}
