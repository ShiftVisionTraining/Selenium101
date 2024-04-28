package com.shiftvision.qa.testcase;

import com.shiftvision.spree.framework.utils.JavascriptExecutorUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class NestedFrameSwitchToTest {
    private WebDriver driver;
    private JavascriptExecutorUtils js;
    @BeforeEach
    public void setUp(){
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        js = new JavascriptExecutorUtils(driver);
        driver.navigate().to("http://the-internet.herokuapp.com/nested_frames");
    }

    @Test
    public void test1(){
        WebElement topFrame = driver.findElement(By.name("frame-top"));
        driver.switchTo().frame(topFrame);
        WebElement middleFrame = driver.findElement(By.name("frame-middle"));
        driver.switchTo().frame(middleFrame);

        WebElement content = driver.findElement(By.id("content"));
        js.highlight(content);
        delayFor(2000);

    }

    @Test
    public void test2(){
        WebElement topFrame = driver.findElement(By.name("frame-top"));
        driver.switchTo().frame(topFrame);
        WebElement middleFrame = driver.findElement(By.name("frame-middle"));
        driver.switchTo().frame(middleFrame);
        WebElement content = driver.findElement(By.id("content"));
        js.highlight(content);
        delayFor(2000);

        driver.switchTo().parentFrame();
        WebElement leftFrame = driver.findElement(By.name("frame-left"));
        driver.switchTo().frame(leftFrame);
        WebElement content2 = driver.findElement(By.xpath("//body"));
        js.highlight(content2);
        delayFor(2000);

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
        driver.close();
        driver.quit();
    }
}
