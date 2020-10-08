package com.trt.eduservice.service;

import com.trt.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trt.eduservice.entity.vo.CourseInfoVo;
import com.trt.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 谭仁涛
 * @since 2020-10-03
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程基本信息的方法
     * @param courseInfoVo
     * @return
     */
    String addCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id查询课程基本信息
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     * @param courseInfoVo
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id查询课程确认信息
     * @param id
     * @return
     */
    CoursePublishVo publishCourseInfo(String id);
}
