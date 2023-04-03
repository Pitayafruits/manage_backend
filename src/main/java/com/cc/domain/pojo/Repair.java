package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("def_repair")
public class Repair implements Serializable {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer repairId;

    //报修业主id
    private Integer liverId;

    //维修地址
    private String repairPlace;

    //维修内容
    private String repairText;

    //报修时间
    private Date repairTime;

    //维修状态 0:未维修 1：已维修
    private String repairStatus;

    //报修人姓名
    @TableField(exist = false)
    private String liverName;

    //报修人联系方式
    @TableField(exist = false)
    private String liverPhone;
}
