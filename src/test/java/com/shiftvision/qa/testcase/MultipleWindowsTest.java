package com.shiftvision.qa.testcase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class MultipleWindowsTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp(){
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.navigate().to("http://the-internet.herokuapp.com/windows");
        //driver.get("http://the-internet.herokuapp.com/windows");
        //driver.navigate().back();
        //driver.navigate().forward();
        //driver.navigate().refresh();
    }

    @Test
    public void test1(){

        String currentWindowHandle = driver.getWindowHandle();
        System.out.println("Current Windows:" + currentWindowHandle);

        //Set<String> hWins = driver.getWindowHandles();
        //System.out.println("Current Windows:" + hWins);

        WebElement linkToClick = driver.findElement(By.xpath("//div[@class='example']/a"));
        linkToClick.click();

        delayFor(3000);

        Set<String> hWins2 = driver.getWindowHandles();
        System.out.println("Now Windows:" + hWins2);
        String newWinHandel = null;
        for(String item : hWins2){
            if(!item.contentEquals(currentWindowHandle)){
                newWinHandel = item;
                break;
            }
        }

        driver.switchTo().window(newWinHandel);
        WebElement newWinH3 = driver.findElement(By.xpath("//div[@class='example']/h3"));
        String text = newWinH3.getText();
        System.out.println("New Win Text: " + text);


        delayFor(2000);
        driver.close();

        driver.switchTo().window(currentWindowHandle);


    }
    @Test
    public void test2(){

        String currentWindowHandle = driver.getWindowHandle();
        System.out.println("Current Windows:" + currentWindowHandle);

        //Set<String> hWins = driver.getWindowHandles();
        //System.out.println("Current Windows:" + hWins);

        WebElement linkToClick = driver.findElement(By.xpath("//div[@class='example']/a"));
        linkToClick.click();

        delayFor(3000);

        switchToNewWindow(currentWindowHandle);

        WebElement newWinH3 = driver.findElement(By.xpath("//div[@class='example']/h3"));
        String text = newWinH3.getText();
        System.out.println("New Win Text: " + text);
        delayFor(2000);
        driver.close();

        driver.switchTo().window(currentWindowHandle);


    }

    @Test
    public void test3(){
        WebElement linkToClick = driver.findElement(By.xpath("//div[@class='example']/a"));
        linkToClick.click();

        delayFor(3000);

        switchToWindowByTitle("New Window");
        WebElement newWinH3 = driver.findElement(By.xpath("//div[@class='example']/h3"));
        String text = newWinH3.getText();
        System.out.println("New Win Text: " + text);
        delayFor(2000);
        driver.close();
        delayFor(2000);

        switchToWindowByTitle("The Internet");

    }

    @Test
    public void test4(){
        WebElement linkToClick = driver.findElement(By.xpath("//div[@class='example']/a"));
        linkToClick.click();

        delayFor(3000);

        switchToWindowByUrl("http://the-internet.herokuapp.com/windows/new");
        WebElement newWinH3 = driver.findElement(By.xpath("//div[@class='example']/h3"));
        String text = newWinH3.getText();
        System.out.println("New Win Text: " + text);
        delayFor(2000);
        driver.close();
        delayFor(2000);

        switchToWindowByUrl("http://the-internet.herokuapp.com/windows");

    }


    public void switchToNewWindow(String currentWinHandle){
        Set<String> hWins2 = driver.getWindowHandles();
        System.out.println("Now Windows:" + hWins2);
        for(String item : hWins2){
            if(!item.contentEquals(currentWinHandle)){
                driver.switchTo().window(item);
                break;
            }
        }
    }
    public void switchToWindowByTitle(String title){
        Set<String> hWins = driver.getWindowHandles();
        System.out.println("Now Windows:" + hWins);
        for(String item : hWins){
           driver.switchTo().window(item);
           String winTitle = driver.getTitle();
           if(winTitle.contentEquals(title)){
               break;
           }
        }
    }

    public void switchToWindowByUrl(String url){
        Set<String> hWins = driver.getWindowHandles();
        System.out.println("Now Windows:" + hWins);
        for(String item : hWins){
            driver.switchTo().window(item);
            String winUrl = driver.getCurrentUrl();
            if(winUrl.contentEquals(url)){
                break;
            }
        }
    }

    public void switchToWindowByTitle(String title, WebDriver driver){
        Set<String> hWins = driver.getWindowHandles();
        for(String item : hWins){
            driver.switchTo().window(item);
            String winTitle = driver.getTitle();
            if(winTitle.contentEquals(title)){
                System.out.println("Window title found by: " + title);
                break;
            }
        }
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
