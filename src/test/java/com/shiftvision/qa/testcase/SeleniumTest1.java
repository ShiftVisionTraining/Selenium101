package com.shiftvision.qa.testcase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest1 {
    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        System.out.println("BeforeEach...");
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //driver = new ChromeDriver(options);

        driver.navigate().to("https://spree.shiftvision.com/");
    }

    @Test
    public void validUserValidPassword(){
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

        waitFor(1000);
        WebElement successAlert = driver.findElement(By.cssSelector(".alert.alert-success"));
        String successText = successAlert.getText();


        Assertions.assertEquals("Logged in successfully", successText);

        waitFor(4000);


    }

    @Test
    public void validUserInvalidPassword(){
        System.out.println("Test-test2...");

        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();
        waitFor(2000);

        WebElement emailTextbox = driver.findElement(By.id("spree_user_email"));
        emailTextbox.sendKeys("test@shiftvision.com");

        WebElement passwordTextbox = driver.findElement(By.id("spree_user_password"));
        passwordTextbox.sendKeys("shift124");

        WebElement loginButton = driver.findElement(By.name("commit"));
        loginButton.click();

        waitFor(1000);
        WebElement errorAlert = driver.findElement(By.cssSelector(".alert.alert-error"));
        String errorText = errorAlert.getText();


        Assertions.assertEquals("Invalid email or password.", errorText);

        waitFor(4000);


    }

    @Test
    public void InvalidUserValidPassword(){
        System.out.println("Test-test3...");

        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();
        waitFor(2000);

        WebElement emailTextbox = driver.findElement(By.id("spree_user_email"));
        emailTextbox.sendKeys("testxxx@shiftvision.com");

        WebElement passwordTextbox = driver.findElement(By.id("spree_user_password"));
        passwordTextbox.sendKeys("shift123");

        WebElement loginButton = driver.findElement(By.name("commit"));
        loginButton.click();

        waitFor(1000);
        WebElement errorAlert = driver.findElement(By.cssSelector(".alert.alert-error"));
        String errorText = errorAlert.getText();


        Assertions.assertEquals("Invalid email or password.", errorText);

        waitFor(4000);


    }

    @AfterEach
    public void tearDown(){
        System.out.println("AfterEach...");
        driver.close();
        driver.quit();
    }

    public void waitFor(int timeToWait){
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
