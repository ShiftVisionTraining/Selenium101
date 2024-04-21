package com.shiftvision.qa.testcase;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptAlertTest {

    private WebDriver driver;
    @BeforeEach
    public void setUp(){
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.navigate().to("http://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void test1(){
        delayFor(3000);
        WebElement element1 = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        element1.click();
        delayFor(2000);

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        Assertions.assertThat(text).isEqualToIgnoringCase("I am a JS Alert");
        alert.accept();

        delayFor(3000);
    }
    @Test
    public void test2(){
        delayFor(3000);
        WebElement element1 = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        element1.click();
        delayFor(2000);

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        Assertions.assertThat(text).isEqualToIgnoringCase("I am a JS Confirm");
        alert.dismiss();
        delayFor(1000);
        WebElement result = driver.findElement(By.id("result"));
        Assertions.assertThat(result.getText()).isEqualToIgnoringCase("You clicked: Cancel");

        delayFor(3000);
    }

    @Test
    public void test3(){
        delayFor(3000);
        WebElement element1 = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        element1.click();
        delayFor(2000);

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        Assertions.assertThat(text).isEqualToIgnoringCase("I am a JS prompt");
        alert.sendKeys("Shiftvision");
        alert.accept();
        delayFor(1000);
        WebElement result = driver.findElement(By.id("result"));
        Assertions.assertThat(result.getText()).isEqualToIgnoringCase("You entered: Shiftvision");

        delayFor(3000);
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
