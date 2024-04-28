package com.shiftvision.spree.framework;

import com.shiftvision.spree.framework.utils.JavascriptExecutorUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.assertj.core.api.Assertions;

import java.time.Duration;

public class KeywordScriptBase {
    protected WebDriver driver;
    protected JavascriptExecutorUtils js;
    protected Wait<WebDriver> driverWait;

    @BeforeEach
    public void setUp(){
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        js = new JavascriptExecutorUtils(driver);
        driverWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        //driver.navigate().to("https://spree.shiftvision.com/");
    }

    @AfterEach
    public void tearDown(){
        driver.close();
        driver.quit();
    }


    public void navigateTo(String url){
        driver.navigate().to(url);
    }
    public void click(By by){
        WebElement element = driver.findElement(by);
        if(element != null){
            js.highlight(element);
            element.click();
        }
    }
    public void typeText(By by, String textToEnter){
        WebElement element = driver.findElement(by);
        if(element != null){
            js.highlight(element);
            element.clear();
            element.click();
            element.sendKeys(textToEnter);
        }
    }

    public void verifyText(By by, String textTiVerify){
        WebElement element = driver.findElement(by);
        if(element != null){
            js.highlight(element);
            String text = element.getText();
            Assertions.assertThat(text).containsIgnoringCase(textTiVerify);
        }
    }
    public void verifyIsChecked(By by){
        WebElement element = driver.findElement(by);
        if(element != null){
            js.highlight(element);
            boolean text = element.isSelected();
            Assertions.assertThat(text).as("If element is checked or selected").isTrue();
        }

    }

    public void verifyIsChecked(By by, String attributeName, String attributeValue){
        WebElement element = driver.findElement(by);
        if(element != null){
            js.highlight(element);
            String value = element.getAttribute(attributeName);
            Assertions.assertThat(value).as("Element attribute value of " + attributeName + ": ")
                    .containsIgnoringCase(attributeValue);
        }

    }


    public void waitUntilTitleIs(String title){
        driverWait.until(ExpectedConditions.titleIs(title));
    }
    public void waitUntilElementVisible(By by){
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public void delayFor(int timeInMili){
        try {
            Thread.sleep(timeInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
