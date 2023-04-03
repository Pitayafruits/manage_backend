package com.cc.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * 返回结果类
 */
public class ResultVo<T> {
    private Integer code; //状态码
    private String msg; //提示消息
    private T data; //返回数据
}
