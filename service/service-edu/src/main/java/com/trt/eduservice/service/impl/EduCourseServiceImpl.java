package com.trt.eduservice.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.trt.commonutils.JsonResult;
import com.trt.eduservice.entity.EduCourse;
import com.trt.eduservice.entity.EduCourseDescription;
import com.trt.eduservice.entity.vo.CourseInfoVo;
import com.trt.eduservice.entity.vo.CoursePublishVo;
import com.trt.eduservice.mapper.EduCourseMapper;
import com.trt.eduservice.service.EduCourseDescriptionService;
import com.trt.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trt.servicebase.exceptionhandler.MyException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 谭仁涛
 * @since 2020-10-03
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService courseDescriptionService;

    /**
     * 添加课程基本信息的方法
     * @param courseInfoVo
     * @return
     */
    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加課程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,courseInfoVo);
        int result = baseMapper.insert(eduCourse);
        if (result == 0) {
            //添加失败
            throw new MyException(20001, "添加课程信息失败");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2.向课程简介表添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        boolean res = courseDescriptionService.save(courseDescription);
        if (!res) {
            //添加失败
            throw new MyException(20001, "添加课程简介失败");
        }
        return cid;
    }

    /**
     * 根据课程id查询课程基本信息
     * @param courseId
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        //查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    /**
     * 修改课程信息
     * @param courseInfoVo
     */
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int result = baseMapper.updateById(eduCourse);
        if (result == 0) {
            throw new MyException(20001, "修改课程信息失败");
        }

        //修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);

    }

    /**
     * 根据课程id查询课程确认信息
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }



}
