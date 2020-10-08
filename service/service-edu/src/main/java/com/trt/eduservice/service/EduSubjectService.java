package com.trt.eduservice.service;

import com.trt.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trt.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 谭仁涛
 * @since 2020-09-25
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file, EduSubjectService subjectService);

    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
