package com.shiftvision.qa.testcase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FindElementsTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp(){
        System.out.println("BeforeEach...");
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.navigate().to("https://heatclinic.shiftvision.com");
    }

    @Test
    public void test_findElement(){
        try {
            WebElement item = driver.findElement(By.xpath("//div[@class='title' and text()=\"Hoppin' Hot Sauce\"]/../..//input[@type='submit']"));
            item.click();
            waitFor(5000);
        }
        catch(NoSuchElementException ex){
            System.out.println(ex.getMessage());
        }
    }
    @Test
    public void test1(){
        WebElement item = driver.findElement(By.xpath("(//li[@class='product_container'])[1]"));

        List<WebElement> productContainers =   driver.findElements(By.xpath("//li[@class='product_container']"));
        System.out.println("Count of productContainers:" + productContainers.size());

        for(int i = 0; i < productContainers.size(); i++) {
            WebElement productContainer = productContainers.get(i);
            WebElement title = productContainer.findElement(By.xpath(".//div[@class='title']"));
            String titleText = title.getText();
            System.out.println(titleText);
            if (titleText.contentEquals("Hoppin' Hot Sauce")) {
                WebElement buyNowButton = productContainer.findElement(By.xpath(".//input[@type='submit']"));
                if (buyNowButton != null) {
                    buyNowButton.click();
                    waitFor(5000);
                }
            }
        }
    }

    @Test
    public void test3(){
        WebElement buyNowButton =  getBuyNowButton("Hoppin' Hot Saucex");
        if(buyNowButton != null){
            buyNowButton.click();
        }
        waitFor(5000);
    }

    @AfterEach
    public void tearDown(){
        System.out.println("AfterEach...");
        driver.close();
        driver.quit();
    }
    public WebElement getBuyNowButton(String titleTextToMatch){
        List<WebElement> productContainers =   driver.findElements(By.xpath("//li[@class='product_container']"));
        System.out.println("Count of productContainers:" + productContainers.size());

        for(int i = 0; i < productContainers.size(); i++) {
            WebElement productContainer = productContainers.get(i);
            WebElement title = productContainer.findElement(By.xpath(".//div[@class='title']"));
            String titleText = title.getText();
            System.out.println(titleText);
            if (titleText.contentEquals(titleTextToMatch)) {
                WebElement buyNowButton = productContainer.findElement(By.xpath(".//input[@type='submit']"));
                if (buyNowButton != null) {
                    return buyNowButton;
                }
            }
        }
        return null;
    }

    public void waitFor(int timeToWait){
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
