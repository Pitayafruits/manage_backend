package com.cc.domain.parm;

import lombok.Data;

import java.io.Serializable;

/**
 * 分配房屋保存类
 */
@Data
public class AssignHouseParm implements Serializable {

    //业主id
    private Integer liverId;

    //房屋id
    private Integer houseId;
}
