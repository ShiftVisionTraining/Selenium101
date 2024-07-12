package com.shiftvision.spree.framework.kw;

import com.shiftvision.spree.framework.pom.DriverFactory;
import org.openqa.selenium.By;

import java.io.File;
import java.util.Collection;

public class KeywordFactory {
    private static KeywordFactory instance = null;
    private DriverFactory driverFactory = null;
    private ActionFactory actionFactory = null;
    private ObjectRepoFactory objectFactory = null;

    private KeywordFactory(){
        driverFactory = DriverFactory.getInstance();
        actionFactory = ActionFactory.getInstance();
        objectFactory = ObjectRepoFactory.getInstance();
    }
    public static KeywordFactory getInstance(){
        if(instance == null){
            instance = new KeywordFactory();
        }
        return instance;
    }

    public static Collection<TestScript> getScriptList(String keywordFile, String tabName){
        Collection<TestScript> testScripts = TestScript.load(keywordFile,"TestScript");
        return testScripts;
    }

    public void processKeywordScript(String fileName, String tabName){
        if(new File(fileName).exists()) {
            System.out.println("Executing test script: " + tabName);
            Collection<TestStep> testSteps = TestStep.load(fileName, tabName);
            for (TestStep ts : testSteps) {
                System.out.println(ts);
                processKeyword(ts);
            }
        }
        else{
            throw new RuntimeException("Invalid file name: " + fileName);
        }
    }

    public void processKeyword(TestStep ts){
        String keyword = ts.getKeyword().toUpperCase();
        By by = ts.getLocatorBy();
        String testData = ts.getData();

        switch(keyword) {
            case "NAVIGATE_TO_APP":
                actionFactory.navigateToApp(testData);
                break;
            case "CLICK":
                actionFactory.click(by);
                break;
            case "TYPE_TEXT":
                actionFactory.typeText(by,testData);
                break;
            case "VERIFY_TEXT":
                actionFactory.verifyText(by, testData);
                break;
            case "CLOSE_BROWSER":
                actionFactory.closeBrowser();
                break;
            case "WAIT":
                int timeToDelay = Integer.parseInt(testData.replace(".0",""));
                actionFactory.delayFor(timeToDelay);
                break;
            default:
                System.out.println("Unknown keywork: " + keyword);
        }
    }




}
