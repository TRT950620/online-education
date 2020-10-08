package com.trt.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: trt
 * @description:
 * @create: 2020-10-05 18:29
 */
@Data
public class ChapterVo {

    private String id;
    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}