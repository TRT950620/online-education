package com.trt.eduservice.controller;


import com.trt.commonutils.JsonResult;
import com.trt.eduservice.entity.EduChapter;
import com.trt.eduservice.entity.chapter.ChapterVo;
import com.trt.eduservice.service.EduChapterService;
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
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    /**
     * 课程大纲列表,根据课程id进行查询
     * @param courseId
     * @return
     */
    @GetMapping("getChapterVideo/{courseId}")
    public JsonResult getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return JsonResult.ok().data("allChapterVideo",list);
    }


    /**
     * 添加章节
     * @param eduChapter
     * @return
     */
    @PostMapping("addChapter")
    public JsonResult addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return JsonResult.ok();
    }

    /**
     * 根据章节id查询
     * @param chapterId
     * @return
     */
    @GetMapping("getChapterInfo/{chapterId}")
    public JsonResult getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);
        return JsonResult.ok().data("chapter", eduChapter);
    }

    /**
     * 修改章节
     * @param eduChapter
     * @return
     */
    @PostMapping("updateChapter")
    public JsonResult updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return JsonResult.ok();
    }

    /**
     * 删除的方法
     * @param chapterId
     * @return
     */
    @DeleteMapping("{chapterId}")
    public JsonResult deleteChapter(@PathVariable String chapterId){
        boolean result = chapterService.deleteChapter(chapterId);
        if (result) {
            return JsonResult.ok();
        } else {
            return JsonResult.error();
        }
    }


}

