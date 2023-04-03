package com.cc.domain.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *  楼宇实体
 */
@Data
@TableName("house_building")
public class Building implements Serializable {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer buildingId;

    //楼宇类型 0:普通房 1:电梯房
    private String buildingType;

    //楼宇名称
    private String buildingName;

    //序号
    private Integer buildingNumber;
}
