package com.powernode.springbootdemo.commonutils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @PACKAGE_NAME: com.powernode.springbootdemo.commonutils
 * @NAME: GlobalExceptionHandler
 * @AUTHOR: 如意郎君
 * @DATE: 2020/9/16
 * @TIME: 15:08
 * @DAY_NAME_SHORT: 星期三
 * @VERSION: 1.0
 *
 * 全局异常处理程序
 * 统一异常处理器
 */
@Slf4j  //logbcak日志输出
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error(ResultEnum.FAILED);
    }


    /*指定异常*/
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();

        return R.error(ResultEnum.FAILED);
    }

    //自定义异常
    @ExceptionHandler(YellowHaoException.class)
    @ResponseBody//返回数据
    public R error(YellowHaoException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().code(e).message(e);
    }
}
