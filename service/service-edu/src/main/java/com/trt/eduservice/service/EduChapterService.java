package com.trt.eduservice.service;

import com.trt.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trt.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 谭仁涛
 * @since 2020-10-03
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 课程大纲列表,根据课程id进行查询
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    /**
     * 删除的方法
     * @param chapterId
     * @return
     */
    boolean deleteChapter(String chapterId);
}
