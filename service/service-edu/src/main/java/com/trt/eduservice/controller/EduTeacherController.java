package com.trt.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trt.commonutils.JsonResult;
import com.trt.eduservice.entity.EduTeacher;
import com.trt.eduservice.entity.vo.TeacherQuery;
import com.trt.eduservice.service.EduTeacherService;
import com.trt.servicebase.exceptionhandler.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 谭仁涛
 * @since 2020-09-13
 */
//@Api(description = "讲师管理")
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    //访问地址： http://localhost:8001/eduservice/teacher/findAll
    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询讲师表中所有数据
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public JsonResult findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return JsonResult.ok().data("items", list);
    }

    /**
     * 逻辑删除讲师的方法
     * @param id
     * @return
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public JsonResult removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return JsonResult.ok();
        }else {

            return JsonResult.error();
        }
    }

    /**
     * 分页查询讲师的方法
     * @param current 当前页
     * @param limit 每页记录数
     * @return
     */
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public JsonResult pageListTeacher(@PathVariable long current,
                                      @PathVariable long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

//        try{
//            int i = 10/0;
//        }catch (Exception e){
//            //执行自定义异常
//            throw new MyException(201, "执行了自定义异常");
//        }

        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合

//        Map map = new HashMap();
//        map.put("total", total);
//        map.put("row", records);
//        return JsonResult.ok().data(map);

        return JsonResult.ok().data("total",total).data("rows",records);
    }

    /**
     * 条件查询带分页的方法
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public JsonResult pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                           @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //构建条件
        QueryWrapper wrapper = new QueryWrapper();
        //多条件组合查询
        //mybatis学过动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_end", end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        teacherService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        return JsonResult.ok().data("total",total).data("rows",records);
    }

    /**
     * 添加讲师接口的方法
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value = "添加讲师接口")
    @PostMapping("addTeacher")
    public JsonResult addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean result = teacherService.save(eduTeacher);
        if (result) {
            return JsonResult.ok();
        }else {
            return JsonResult.error();
        }
    }

    /**
     * 根据讲师id进行查询
     * @param id
     * @return
     */
    @ApiOperation(value = "根据讲师id进行查询")
    @GetMapping("getTeacher/{id}")
    public JsonResult getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return JsonResult.ok().data("teacher", eduTeacher);
    }


    /**
     * 讲师修改功能
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value = "讲师修改功能")
    @PostMapping("updateTeacher")
    public JsonResult updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean result = teacherService.updateById(eduTeacher);
        if (result) {
            return JsonResult.ok();
        }else {
            return JsonResult.error();
        }
    }






}

