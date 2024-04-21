package com.shiftvision.qa.testcase;

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

public class DragAndDropActionClassTest {

    WebDriver driver;
    @BeforeEach
    public void setUp(){
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
    }

    @Test
    public void test1(){
        delayFor(3000);
        WebElement element1 = driver.findElement(By.id("column-a"));
        WebElement element2 = driver.findElement(By.id("column-b"));

        final String java_script =
                "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe" +
                        "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun" +
                        "ction(format,data){this.items[format]=data;this.types.append(for" +
                        "mat);},getData:function(format){return this.items[format];},clea" +
                        "rData:function(format){}};var emit=function(event,target){var ev" +
                        "t=document.createEvent('Event');evt.initEvent(event,true,false);" +
                        "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('" +
                        "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit(" +
                        "'drop',tgt);emit('dragend',src);";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(java_script, element1, element2);

        delayFor(5000);
    }
    @Test
    public void test2(){
        delayFor(3000);
        WebElement element1 = driver.findElement(By.id("column-a"));
        WebElement element2 = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element1)
                .pause(Duration.ofSeconds(1))
                .clickAndHold(element1)
                .pause(Duration.ofSeconds(1))
                .moveToElement(element2)
                .pause(Duration.ofSeconds(1))
                .release(element2)
                .build()
                .perform();

        delayFor(5000);
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
