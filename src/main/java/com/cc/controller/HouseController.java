package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.domain.pojo.House;
import com.cc.domain.parm.HouseParm;
import com.cc.domain.vo.ResultVo;
import com.cc.service.HouseService;
import com.cc.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房屋控制器
 */

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    //分页
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('houseBuilding:house:list')")
    public ResultVo getHouseList(HouseParm parm){
        IPage<House> houseList = houseService.getList(parm);
        return ResultUtils.success("查询成功",houseList);
    }

    //添加
    @PostMapping
    @PreAuthorize("hasAuthority('houseBuilding:house:add')")
    public ResultVo addHouse(@RequestBody House house){
        boolean flag = houseService.save(house);
        if (flag){
            return ResultUtils.success("添加成功!");
        }
        return ResultUtils.error("添加失败!");
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('houseBuilding:house:edit')")
    public ResultVo editHouse(@RequestBody House house){
        boolean flag = houseService.updateById(house);
        if (flag){
            return ResultUtils.success("修改成功!");
        }
        return ResultUtils.error("修改失败!");
    }

    //删除
    @DeleteMapping("/{houseId}")
    @PreAuthorize("hasAuthority('houseBuilding:house:delete')")
    public ResultVo deleteHouse(@PathVariable("houseId") Integer houseId){
        boolean flag = houseService.removeHouse(houseId);
        if (flag){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败,该房屋正在使用中!");
    }

    //根据单元id获取房屋列表
    @GetMapping("/getHouseListByUnitId")
    public ResultVo getHouseListByUnitId(House house){
        List houseList = houseService.getHouseList(house);
        if (houseList != null){
            return ResultUtils.success("查询成功!",houseList);
        }
        return ResultUtils.error("查询失败!");
    }

    //统计房屋数量
    @GetMapping("/getHouseNum")
    public ResultVo getHouseNum(){
        long houseCount = houseService.count();
        return ResultUtils.success("查询成功！",houseCount);
    }

}