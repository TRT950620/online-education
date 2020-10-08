package com.trt.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Authror 谭仁涛
 * @Description
 * @Date 2020/9/23 0:30
 */
@Data
public class ReadData {

    //设置execl表头名称  index标记对应关系
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名", index = 1)
    private String name;
}
