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


public class BootstrapDropdownButton {

    private WebDriver driver;
    @BeforeEach
    public void setUp(){
        System.out.println("BeforeEach...");
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.navigate().to("https://react-bootstrap.netlify.app/docs/components/dropdowns#/action-1");
        waitFor(5000);
    }

    @Test
    public void test1() {
        WebElement dropdownButton = driver.findElement(By.xpath("//*[@id='dropdown-basic']"));
       //dropdownButton.click();

        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownButton)
                .click();
        actions.perform();

        List<WebElement> itemList = driver.findElements(By.xpath("//div[@aria-labelledby='dropdown-basic']/a"));
        for(WebElement item : itemList){
            String text = item.getText();
            System.out.println(text);
            if(text.contentEquals("Something else")){
                actions.moveToElement(item)
                        .click();
                actions.perform();
                //item.click();
                break;
            }
        }
    }

    @Test
    public void test2() {
        WebElement dropdownButton = driver.findElement(By.xpath("//*[@id='dropdown-basic']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownButton)
                .click();
        actions.perform();

        String buttonId = dropdownButton.getAttribute("id");
        List<WebElement> itemList = driver.findElements(By.xpath("//div[@aria-labelledby='" + buttonId + "']/a"));
        for(WebElement item : itemList){
            String text = item.getText();
            System.out.println(text);
            if(text.contentEquals("Something else")){
                actions.moveToElement(item)
                        .click();
                actions.perform();
                break;
            }
        }
    }
    @Test
    public void test3() {
        selectBootstrapDropdownButtonOption(By.xpath("//*[@id='dropdown-basic']"),"Something else");
        waitFor(2000);

        selectBootstrapDropdownButtonOption(By.id("dropdown-basic-button"),"Something else");
        waitFor(2000);

    }

    public void selectBootstrapDropdownButtonOption(By by, String optionToSelect){
        WebElement dropdownButton = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownButton)
                .click();
        actions.perform();

        String buttonId = dropdownButton.getAttribute("id");
        List<WebElement> itemList = driver.findElements(By.xpath("//div[@aria-labelledby='" + buttonId + "']/a"));
        for(WebElement item : itemList){
            String text = item.getText();
           // System.out.println(text);
            if(text.contentEquals(optionToSelect)){
                actions.moveToElement(item)
                        .click();
                actions.perform();
                break;
            }
        }
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
