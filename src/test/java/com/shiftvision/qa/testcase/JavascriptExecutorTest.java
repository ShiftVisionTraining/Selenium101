package com.shiftvision.qa.testcase;
import com.shiftvision.spree.framework.utils.JavascriptExecutorUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;
public class JavascriptExecutorTest {
    private WebDriver driver;
    private JavascriptExecutorUtils js;
    @BeforeEach
    public void setUp(){
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        js = new JavascriptExecutorUtils(driver);
        driver.navigate().to("http://the-internet.herokuapp.com");
    }


    @Test
    public void highlight() {
        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
        delayFor(3000);

        WebElement element1 = driver.findElement(By.id("column-a"));
        WebElement element2 = driver.findElement(By.id("column-b"));

        js.highlight(element1);
        delayFor(3000);

        js.highlight(By.id("column-b"));
        delayFor(3000);

    }

    @Test
    public void jsClick(){
        driver.navigate().to( "http://the-internet.herokuapp.com/javascript_alerts");
        delayFor(3000);

        WebElement element1 = driver.findElement(By.xpath("//ul/li[1]/button"));
        js.click(element1);

        delayFor(3000);
        Alert alert = driver.switchTo().alert();
        alert.accept();

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
