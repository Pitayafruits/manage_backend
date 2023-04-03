package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.domain.pojo.Parking;
import org.springframework.stereotype.Repository;

/**
 * 车位管理DAO层
 */
@Repository
public interface ParkingMapper extends BaseMapper<Parking> {
}
