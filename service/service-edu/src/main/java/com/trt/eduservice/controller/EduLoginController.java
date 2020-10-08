package com.trt.eduservice.controller;

import com.trt.commonutils.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @Authror 谭仁涛
 * @Description
 * @Date 2020/9/16 22:50
 */
//@Api(tags = "登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //解决跨域
public class EduLoginController {

    //login
    @PostMapping("login")
    public JsonResult login(){
        return JsonResult.ok().data("token", "admin");
    }

    //info
    @GetMapping("info")
    public JsonResult info(){
        return JsonResult.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
