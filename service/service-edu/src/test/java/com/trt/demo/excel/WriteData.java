package com.trt.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Authror 谭仁涛
 * @Description
 * @Date 2020/9/23 0:30
 */
@Data
public class WriteData {

    //设置execl表头名称
    @ExcelProperty("学生编号")
    private Integer sno;

    @ExcelProperty("学生姓名")
    private String name;
}
