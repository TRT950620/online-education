package com.trt.demo.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @Authror 谭仁涛
 * @Description excel 写操作
 * @Date 2020/9/23 0:34
 */
public class TestReadEasyExcel {

    public static void main(String[] args) {
        //实现excel读操作
        //1 设置读文件夹地址和excel文件名称
        //String filename = "F:/write.xlsx";
        String filename = "F:\\write.xlsx";

        // 2调用easyexcel里面的方法实现读操作
        //write方法两个参数:第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.read(filename, ReadData.class, new ExcelListener()).sheet().doRead();
    }


}
