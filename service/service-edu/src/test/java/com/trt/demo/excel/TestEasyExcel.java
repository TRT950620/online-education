package com.trt.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Authror 谭仁涛
 * @Description excel 写操作
 * @Date 2020/9/23 0:34
 */
public class TestEasyExcel {

    public static void main(String[] args) {
        //实现excel写操作
        //1 设置写入文件夹地址和excel文件名称
        //String filename = "F:/write.xlsx";
        String filename = "F:\\write.xlsx";

        // 2调用easyexcel里面的方法实现写操作
        //write方法两个参数:第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(filename, WriteData.class).sheet("学生列表").doWrite(getData());


    }


    //创建方法返回list集合
    private static List<WriteData> getData(){
        List<WriteData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            WriteData data = new WriteData();
            data.setSno(i);
            data.setName("lucy"+i);
            list.add(data);
        }
        return list;
    }


}
