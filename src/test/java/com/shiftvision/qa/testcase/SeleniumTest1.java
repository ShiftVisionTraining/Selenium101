package com.shiftvision.qa.testcase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest1 {
    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        System.out.println("BeforeEach...");
        //String driverFile = System.getProperty("user.dir") + "/win/chromedriver.exe";
        //System.setProperty("webdriver.chrome.driver",driverFile);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://spree.shiftvision.com/");
    }

    @Test
    public void test1(){
        System.out.println("Test-test1...");
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();
        waitFor(2000);

        WebElement emailTextbox = driver.findElement(By.id("spree_user_email"));
        emailTextbox.sendKeys("test@shiftvision.com");

        WebElement passwordTextbox = driver.findElement(By.id("spree_user_password"));
        passwordTextbox.sendKeys("shift123");

        WebElement loginButton = driver.findElement(By.name("commit"));
        loginButton.click();

        waitFor(10000);

    }

    @AfterEach
    public void tearDown(){
        System.out.println("AfterEach...");
        driver.close();
       // driver.quit();
    }

    public void waitFor(int timeToWait){
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
