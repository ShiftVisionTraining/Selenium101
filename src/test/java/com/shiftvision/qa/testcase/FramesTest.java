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
import java.util.Set;
public class FramesTest {
    private WebDriver driver;
    private JavascriptExecutorUtils js;
    @BeforeEach
    public void setUp(){
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        js = new JavascriptExecutorUtils(driver);
        driver.navigate().to("http://the-internet.herokuapp.com/iframe");
    }

    @Test
    public void test1(){
        delayFor(2000);
        driver.switchTo().frame("mce_0_ifr");
        WebElement element1 = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        js.highlight(element1);
        String text = element1.getText();
        System.out.println("Text is:" + text);
        delayFor(3000);
    }

    @Test
    public void test2(){
        delayFor(2000);
        driver.switchTo().frame(0);
        WebElement element1 = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        js.highlight(element1);
        String text = element1.getText();
        System.out.println("Text is:" + text);
        delayFor(3000);
    }

    @Test
    public void test3(){
        delayFor(2000);
        WebElement frame = driver.findElement(By.cssSelector(".tox-edit-area iframe"));
        driver.switchTo().frame(frame);
        WebElement element1 = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        js.highlight(element1);
        String text = element1.getText();
        System.out.println("Text is:" + text);
        delayFor(3000);
    }

    @Test
    public void test4(){
        delayFor(2000);
        WebElement frame = driver.findElement(By.xpath("//iframe[@title='Rich Text Area']"));
        driver.switchTo().frame(frame);
        WebElement element1 = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        js.highlight(element1);
        String text = element1.getText();
        System.out.println("Text is:" + text);
        delayFor(3000);
    }
    @Test
    public void test5(){
        delayFor(2000);
        WebElement h3Text = driver.findElement(By.xpath("//div[@class='example']/h3"));
        System.out.println(h3Text.getText());
        js.highlight(h3Text);

        WebElement frame = driver.findElement(By.xpath("//iframe[@title='Rich Text Area']"));
        driver.switchTo().frame(frame);
        WebElement element1 = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        js.highlight(element1);
        String text = element1.getText();
        System.out.println("Text is:" + text);
        delayFor(2000);

        driver.switchTo().defaultContent();
        WebElement h3TextAfter = driver.findElement(By.xpath("//div[@class='example']/h3"));
        System.out.println(h3TextAfter.getText());
        js.highlight(h3TextAfter);
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
