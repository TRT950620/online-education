package com.trt.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Authror 谭仁涛
 * @Description 创建监斲进行exce1文件读取
 * @Date 2020/9/23 0:49
 */
public class ExcelListener extends AnalysisEventListener<ReadData> {

    //一行一行读取excel内容
    @Override
    public void invoke(ReadData readData, AnalysisContext analysisContext) {
        System.out.println("****"+readData);

    }

    //读取表头内容
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }


    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
}
