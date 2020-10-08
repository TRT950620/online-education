package com.trt.commonutils;/**
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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Authror 谭仁涛
 * @description 统一返回结果的类
 * @Date 2020/9/13 18:26
 */
@Data
public class JsonResult {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private JsonResult(){}

    //链式编程 JsonResult.ok().code().message()。
    //成功静态方法
    public static JsonResult ok(){
        JsonResult result = new JsonResult();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    //失败静态方法
    public static JsonResult error(){
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }

    public JsonResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public JsonResult message(String message){
        this.setMessage(message);
        return this;
    }

    public JsonResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public JsonResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public JsonResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }



}
