package com.trt.eduservice.controller;


import com.trt.commonutils.JsonResult;
import com.trt.eduservice.entity.EduCourse;
import com.trt.eduservice.entity.vo.CourseInfoVo;
import com.trt.eduservice.entity.vo.CoursePublishVo;
import com.trt.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 谭仁涛
 * @since 2020-10-03
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    /**
     * 添加课程基本信息的方法
     * @param courseInfoVo
     * @return
     */
    @PostMapping("addCourseInfo")
    public JsonResult addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.addCourseInfo(courseInfoVo);
        return JsonResult.ok().data("courseId", id);
    }

    /**
     * 根据课程id查询课程基本信息
     * @param courseId
     * @return
     */
    @GetMapping("getCourseInfo/{courseId}")
    public JsonResult getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return JsonResult.ok().data("courseInfoVo", courseInfoVo);
    }

    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("updateCourseInfo")
    public JsonResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return JsonResult.ok();
    }

    /**
     * 根据课程id查询课程确认信息
     * @param id
     * @return
     */
    @GetMapping("getPublishCourseInfo/{id}")
    public JsonResult getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return JsonResult.ok().data("coursePublishVo", coursePublishVo);
    }

    /**
     * 课程最终发布
     * 修改课程状态
     * @param id
     * @return
     */
    @PostMapping("publishCourse/{id}")
    public JsonResult publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        courseService.updateById(eduCourse);
        return JsonResult.ok();
    }

    /**
     * 课程列表
     * @return
     */
    @GetMapping
    public JsonResult getCourseList(){
        // todo 完善条件查询带分页
        List<EduCourse> list = courseService.list(null);
        return JsonResult.ok().data("list", list);
    }



}

