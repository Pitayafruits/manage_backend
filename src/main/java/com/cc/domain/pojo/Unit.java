package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("house_unit")
public class Unit implements Serializable {

    //单元id
    @TableId(type= IdType.AUTO)
    private Integer unitId;

    //楼宇id
    private Integer buildingId;

    //单元名称
    private String unitName;

    //楼宇名称
    @TableField(exist = false)
    private String buildingName;
}
