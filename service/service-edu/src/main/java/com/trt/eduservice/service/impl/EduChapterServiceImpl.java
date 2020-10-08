package com.trt.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trt.eduservice.entity.EduChapter;
import com.trt.eduservice.entity.EduVideo;
import com.trt.eduservice.entity.chapter.ChapterVo;
import com.trt.eduservice.entity.chapter.VideoVo;
import com.trt.eduservice.mapper.EduChapterMapper;
import com.trt.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trt.eduservice.service.EduVideoService;
import com.trt.servicebase.exceptionhandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 谭仁涛
 * @since 2020-10-03
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    /**
     * 课程大纲列表,根据课程id进行查询
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //2根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);

        //创建Iist集合，用于最终封裝数据
        List<ChapterVo> finalList = new ArrayList<>();

        //3遍历查询章节list集合进行封装
        //遍历查询章节list集合
        for (int i = 0; i < eduChapterList.size(); i++) {
            //每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            //eduChapter对象值复制到ChapterVo里面
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            //把chapterVo放到最终list集合
            finalList.add(chapterVo);

            //创建集合，用于封装章节的小节
            List<VideoVo> videoList = new ArrayList<>();

            //4遍历查询小节Iist集合，进行封装
            for (int j = 0; j < eduVideoList.size(); j++) {
                //得到每个小节
                EduVideo eduVideo = eduVideoList.get(j);
                //判断:小节里面chapterid和章节里面id是否一样
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    //放到小节封装集合
                    videoList.add(videoVo);
                }
            }
            //把封装之后小节list集合，放到章节对象里面
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }

    /**
     * 删除的方法
     * @param chapterId
     * @return
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid章节id查询小节表，如果查询数据，不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0) { //查询出小节，不进行删除
            throw new MyException(20001, "不能删除");
        } else { //不能查询数据，进行删除
            //删除章节
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }
}
