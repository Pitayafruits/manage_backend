package com.cc.utils;

import com.cc.domain.pojo.Menu;
import com.cc.domain.vo.RouterVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 菜单转为树形结构
 * 递归算法
 */
public class MyTreeUtils {

    public static List<Menu> makeTree(List<Menu> menuList,Integer parentId){
        //存放处理后的结果
        List<Menu> resultList = new ArrayList<>();
        //拼装树形数据
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId() == parentId)
                .forEach(res -> {
                    //过滤出的数据放到新的里面
                    Menu newMenu = new Menu();
                    BeanUtils.copyProperties(res,newMenu);
                    //找出当前对象的下级菜单
                    List<Menu> childMenu = makeTree(menuList, res.getMenuId());
                    newMenu.setChildren(childMenu);
                    resultList.add(newMenu);
                });
        return resultList;
    }

    //生成路由数据格式
    public static List<RouterVO> makeRouter(List<Menu> menuList,Integer parentId){
        //接收生成的路由数据
        List<RouterVO> resultList = new ArrayList<>();
        //组装数据
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                //数据过滤
                .filter(item -> item != null && item.getParentId() == parentId)
                .forEach(item -> {
                    //设置路由数据
                    RouterVO router = new RouterVO();
                    router.setName(item.getRouteName());
                    router.setPath(item.getRoutePath());
                    //判断是一级菜单
                    if(item.getParentId() == 0){
                        router.setComponent("Layout");
                        router.setAlwaysShow(true);
                    }else{
                        router.setComponent(item.getRouteUrl());
                        router.setAlwaysShow(false);
                    }
                    //设置meta
                    router.setMeta(router.new Meta(
                            item.getMenuLabel(),
                            item.getIcon(),
                            item.getMenuCode().split(",")
                    ));
                    //设置children
                    List<RouterVO> children = makeRouter(menuList, item.getMenuId());
                    router.setChildren(children);
                    resultList.add(router);
                });
        return resultList;
    }

//    public static List<RouterVO> makeRouter(List<Menu> menuList, Integer pid){
//        List<RouterVO> list = new ArrayList<>();
//        Optional.ofNullable(menuList).orElse(new ArrayList<>())
//                .stream()
//                .filter(item -> item != null && item.getParentId() == pid)
//                .forEach(item -> {
//                    RouterVO router = new RouterVO();
//                    router.setName(item.getRouteName());
//                    router.setPath(item.getRoutePath());
//                    if(item.getParentId() == 0){
//                        router.setComponent("Layout");
//                        router.setAlwaysShow(true);
//                    }else{
//                        router.setComponent(item.getRouteUrl());
//                        router.setAlwaysShow(false);
//                    }
//                    //设置meta
//                    router.setMeta(router.new Meta(
//                            item.getMenuLabel(),
//                            item.getIcon(),
//                            item.getMenuCode().split(",")
//                    ));
//                    //设置children,每一项的下级
//                    List<RouterVO> children = makeRouter(menuList, item.getMenuId());
//                    router.setChildren(children);
//                    list.add(router);
//                });
//        return list;
//    }
}
