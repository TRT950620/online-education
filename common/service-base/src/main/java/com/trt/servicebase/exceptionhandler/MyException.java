package com.trt.servicebase.exceptionhandler;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authror 谭仁涛
 * @Description
 * @Date 2020/9/16 21:46
 */

@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor //生成无参数构造
public class MyException extends RuntimeException {

    private Integer code; //状态码
    private String msg; //异常信息


}
