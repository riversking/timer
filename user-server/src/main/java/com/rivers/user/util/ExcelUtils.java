package com.rivers.user.util;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private final static String EXCEL03 = "xls";
    private final static String EXCEL07 = "xlsx";


    public static List<Map<String, Object>> importExcel(String filePath) {
        ExcelReader reader = ExcelUtil.getReader(filePath);
        return reader.readAll();
    }


}
