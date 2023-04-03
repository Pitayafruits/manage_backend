package com.cc.utils;

import com.cc.domain.vo.ResultVo;
import com.cc.domain.status.StatusCode;

/**
 * 数据返回工具类
 */
public class ResultUtils {

    /**
     * 无参数返回
     * @return
     */
    public static ResultVo success(){
        return Vo(StatusCode.SUCCESS_CODE,null,null);
    }
    public static ResultVo success(String msg){
        return Vo(StatusCode.SUCCESS_CODE,msg,null);
    }

    /**
     * 返回带参数
     * @return
     */
    public static ResultVo success(String msg,Object data){
        return Vo(StatusCode.SUCCESS_CODE,msg,data);
    }
    public static ResultVo success(int code,String msg,Object data){
        return Vo(code,msg,data);
    }
    public static ResultVo Vo(int code,String msg,Object data){
        return new ResultVo(code,msg,data);
    }

    /**
     * 错误返回
     * @return
     */
    public static ResultVo error(){
        return Vo(StatusCode.ERROR_CODE,null,null);
    }
    public static ResultVo error(String msg){
        return Vo(StatusCode.ERROR_CODE,msg,null);
    }
    public static ResultVo error(int code,String msg,Object data){
        return Vo(code,msg,data);
    }
    public static ResultVo error(int code,String msg){
        return Vo(code,msg,null);
    }
    public static ResultVo error(String msg,Object data){
        return Vo(StatusCode.ERROR_CODE,msg,data);
    }
}
