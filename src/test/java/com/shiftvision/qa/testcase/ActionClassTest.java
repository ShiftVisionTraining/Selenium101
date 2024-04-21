package com.shiftvision.qa.testcase;

import org.junit.jupiter.api.AfterEach;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
public class ActionClassTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.out.println("BeforeEach...");
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.navigate().to("http://the-internet.herokuapp.com/hovers");
    }

    @Test
    public void test1() {
        delayFor(3000);
        WebElement element1 = driver.findElement(By.xpath("(//div[@class='figure']/img)[1]"));
        WebElement element2 = driver.findElement(By.xpath("(//div[@class='figure']/img)[2]"));
        WebElement element3 = driver.findElement(By.xpath("(//div[@class='figure']/img)[3]"));


        Actions actions = new Actions(driver);
        actions.moveToElement(element1).build().perform();
        delayFor(2000);
        actions.moveToElement(element2).build().perform();
        delayFor(2000);
        actions.moveToElement(element3).build().perform();

        //element.click();

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
        System.out.println("AfterEach...");
        driver.close();
        driver.quit();
    }

}
