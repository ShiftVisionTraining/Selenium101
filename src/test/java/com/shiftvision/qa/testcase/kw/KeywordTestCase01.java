package com.shiftvision.qa.testcase.kw;

import com.shiftvision.spree.framework.utils.ExcelReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;
import java.util.Arrays;

public class KeywordTestCase01 {
    protected WebDriver driver;
    protected ExcelReader excelReader;
    protected String EXCEL_FILE_LOCATION = System.getProperty("user.dir") + "/src/test/resources/kw_scripts/ShiftVisionKeywordTest.xlsx";

    @BeforeEach
    public void setUp(){
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();

        excelReader = new ExcelReader(EXCEL_FILE_LOCATION);
    }

    @Test
    public void keywordDriver(){
        Object[][] scriptData ;
        scriptData = excelReader.getExcelSheetData("Login01", false);
        //System.out.println(Arrays.deepToString(scriptData));
        for(int i = 1; i < scriptData.length; i++){
            System.out.println(Arrays.deepToString(scriptData[i]));
            processStep(scriptData[i]);
        }
    }

    public void processStep(Object[] stepData){
        String step = stepData[0].toString();
        String description = stepData[1].toString();
        String keyword = stepData[2].toString();
        String locator = stepData[3].toString();
        String data = stepData[4].toString();
        System.out.println("Processing step: " + step + " - " + description);

        processKeyword(keyword,locator,data);

    }

    public void processKeyword(String keyword, String locator, String testData){
        By by = null;
        by = getLocatorBy(locator);

        switch(keyword) {
            case "NAVIGATE_TO_APP":
                navigateToApp(testData);
                break;
            case "CLICK":
                click(by);
                break;
            case "TYPE_TEXT":
                typeText(by, testData);
                break;
            case "VERIFY_TEXT":
                verifyText(by,testData);
                break;
            case "CLOSE_BROWSER":
                closeBrowser();
                break;
            default:
                System.out.println("Unknown keywork: " + keyword);
        }
    }

    public void navigateToApp(String testData){
        driver.navigate().to(testData);
    }
    public void click(By by){
        WebElement element = driver.findElement(by);
        if(element != null){
            element.click();
        }
    }
    public void typeText(By by, String testData){
        WebElement element = driver.findElement(by);
        if(element != null){
            element.sendKeys(testData);
        }
    }
    public void verifyText(By by, String testData){
        WebElement element = driver.findElement(by);
        if(element != null){
            String actualText = element.getText();
            assertThat(actualText).containsIgnoringCase(testData);
        }
    }
    public void closeBrowser(){
        driver.close();
    }

    public By getLocatorBy(String locator){
        By by = null;
        if(locator != null && locator.length() > 0 && locator.contains(":") ) {
            String byWithLocator = locator;
            String parts[] = byWithLocator.split(":");
            if (parts != null && parts.length > 0) {
                String locatorBy = parts[0];
                String locatorValue = parts[1];
                by = getBy(locatorBy, locatorValue);
            } else {
                throw new RuntimeException("There is no locator for the by object ");
            }
        }
        return  by;
    }

    private By getBy(String locatorBy, String locator) {
        By by = null;

        if (locatorBy != null && locator != null) {
            if (locatorBy.equalsIgnoreCase("LINK_TEXT")) {
                by = By.linkText(locator);
            } else if (locatorBy.equalsIgnoreCase("PARTIAL_LINK_TEXT")) {
                by = By.partialLinkText(locator);
            } else if (locatorBy.equalsIgnoreCase("ID")) {
                by = By.id(locator);
            } else if (locatorBy.equalsIgnoreCase("NAME")) {
                by = By.name(locator);
            } else if (locatorBy.equalsIgnoreCase("CSS")) {
                by = By.cssSelector(locator);
            } else if (locatorBy.equalsIgnoreCase("TAG_NAME")) {
                by = By.tagName(locator);
            } else if (locatorBy.equalsIgnoreCase("XPATH")) {
                by = By.xpath(locator);
            } else if (locatorBy.equalsIgnoreCase("CLASS_NAME")) {
                by = By.className(locator);
            }
        }
        return by;
    }
    public void delayFor(int timeInMili){
        try {
            Thread.sleep(timeInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown(){
        //driver.close();
        driver.quit();
    }
}
