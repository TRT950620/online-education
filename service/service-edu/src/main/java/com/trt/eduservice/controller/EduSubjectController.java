package com.trt.eduservice.controller;


import com.trt.commonutils.JsonResult;
import com.trt.eduservice.entity.subject.OneSubject;
import com.trt.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 谭仁涛
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("addSubject")
    public JsonResult addSubject(MultipartFile file){
        //上传过来excel文件
        subjectService.addSubject(file, subjectService);
        return JsonResult.ok();
    }

    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public JsonResult getAllSubject(){
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return JsonResult.ok().data("list", list);
    }



}

