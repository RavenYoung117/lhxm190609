package com.qf.exception;

/**
 * wzg 2019/10/14 15:36
 */
//自定义异常类
public class WxException extends RuntimeException {

    public WxException(String message){
        super(message);
    }
}
