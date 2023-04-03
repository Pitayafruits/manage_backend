package com.cc.domain.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *小区基本信息实体类
 */
@Data
@TableName("comm_info")
public class CommunityInfo implements Serializable {

    //主键递增
    @TableId(type = IdType.AUTO)
    private Integer id;

    //小区名称
    private String commName;

    //小区负责人
    private String commMan;

    //建成日期
    private String createTime;

    //楼宇数量
    private Integer buildNum;

    //小区地址
    private String commAdr;

    //联系电话
    private String phoneNumber;

    //建筑面积(亩)
    private Integer commArea;

    //绿化面积(亩)
    private Integer greenArea;

    //道路面积(亩)
    private Integer roadArea;

    //停车场面积(亩)
    private Integer stopArea;

    //小区简介
    private String commInfo;
}
