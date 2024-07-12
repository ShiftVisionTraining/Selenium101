package com.shiftvision.qa.testcase;

import com.shiftvision.spree.framework.utils.ExcelReader;

import java.util.Arrays;

public class ExcelTest {
    public static void main(String[] args) {
        String fileName = System.getProperty("user.dir") + "/src/test/resources/Protiva.xlsx";

        ExcelReader excelReader = new ExcelReader(fileName);
        Object[][] data;
        data = excelReader.getExcelSheetData("Login01", false);
        System.out.println(Arrays.deepToString(data));
    }
}
