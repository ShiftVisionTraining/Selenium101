package com.shiftvision.qa.testcase.kw;

import com.shiftvision.spree.framework.kw.KeywordScriptBase;
import com.shiftvision.spree.framework.kw.TestScript;
import com.shiftvision.spree.framework.kw.TestStep;
import com.shiftvision.spree.framework.utils.ExcelReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class KeywordTestCase02 extends KeywordScriptBase {
    private static String EXCEL_FILE_LOCATION = System.getProperty("user.dir") + "/src/test/resources/kw_scripts/ShiftVisionKeywordTest.xlsx";

    @ParameterizedTest
    @MethodSource("testScriptProvider")
    public void keywordDriver(TestScript ts){
        if(ts.getRun().equalsIgnoreCase("Y")) {
            System.out.println("Running test : " + ts.getDescription());
            keywordFactory.processKeywordScript(EXCEL_FILE_LOCATION, ts.getTabName());
        }
    }

    public static Collection<TestScript> testScriptProvider(){
        Collection<TestScript> testScripts = keywordFactory.getScriptList(EXCEL_FILE_LOCATION,"TestScript");
        return testScripts;
    }

}
