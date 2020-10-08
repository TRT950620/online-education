package com.trt.servicebase.exceptionhandler;/**
 * .::::.
 * .::::::::.
 * :::::::::::    佛主保佑、永无Bug
 * ..:::::::::::'
 * '::::::::::::'
 * .::::::::::
 * '::::::::::::::..
 * ..::::::::::::.
 * ``::::::::::::::::
 * ::::``:::::::::'        .:::.
 * ::::'   ':::::'       .::::::::.
 * .::::'      ::::     .:::::::'::::.
 * .:::'       :::::  .:::::::::' ':::::.
 * .::'        :::::.:::::::::'      ':::::.
 * .::'         ::::::::::::::'         ``::::.
 * ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 * '.:::::'                    ':'````..
 */

import com.trt.commonutils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Authror 谭仁涛
 * @Description 异常处理
 * @Date 2020/9/13 23:18
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * @param e
     * @return
     */
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回教据
    public JsonResult error(Exception e){
        e.printStackTrace();
        return JsonResult.error().message("执行了全局异常处理..");
    }

    /**
     * 特定异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回教据
    public JsonResult error(ArithmeticException e){
        e.printStackTrace();
        return JsonResult.error().message("执行了ArithmeticException异常处理..");
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    @ResponseBody//为了返回教据
    public JsonResult error(MyException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return JsonResult.error().code(e.getCode()).message(e.getMsg());
    }

}
