package com.trt.eduservice.entity.subject;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: trt
 * @description: 一级分类
 * @create: 2020-10-02 21:01
 */
@Data
public class OneSubject implements Serializable {

    private String id;
    private String title;

    //一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();

}