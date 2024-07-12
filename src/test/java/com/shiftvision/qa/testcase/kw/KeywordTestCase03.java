package com.shiftvision.qa.testcase.kw;

import com.shiftvision.spree.framework.kw.KeywordScriptBase;
import com.shiftvision.spree.framework.kw.TestScript;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;

public class KeywordTestCase03 extends KeywordScriptBase {
    private static String EXCEL_FILE_LOCATION = System.getProperty("user.dir") + "/src/test/resources/kw_scripts/ShiftVisionKeywordTestEx.xlsx";

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
