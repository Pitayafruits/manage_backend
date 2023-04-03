package com.cc.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告实体类
 */
@Data
@TableName("comm_notice")
public class Notice implements Serializable {

    //主键
    @TableId(type= IdType.AUTO)
    private Integer noticeId;

    //物业人员id
    private Integer userId;

    //公告标题
    private String noticeTitle;

    //公告内容
    private String noticeText;

    //发布时间
    private Date noticeTime;

    //发布人姓名
    @TableField(exist = false)
    private String fullName;

}
