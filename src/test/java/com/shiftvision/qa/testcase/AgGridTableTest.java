package com.shiftvision.qa.testcase;

import com.shiftvision.spree.framework.utils.JavascriptExecutorUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AgGridTableTest {
    private WebDriver driver;
    private Wait<WebDriver> wait = null;
    private JavascriptExecutorUtils js;


    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        js = new JavascriptExecutorUtils(driver);

        driver.manage().window().maximize();

        driver.navigate().to("https://www.ag-grid.com/example/");

    }


    @Test
    public void test1(){
        delayFor(3000);

        WebElement tableBody = driver.findElement(By.cssSelector("div#myGrid div.ag-body"));
        List<WebElement> rows = tableBody.findElements(By.cssSelector("div[role='row']"));

        List<WebElement> cells = rows.get(0).findElements(By.cssSelector("div[role='gridcell']"));

        WebElement cell1 = cells.get(0);

        WebElement cell1Checkbox = cell1.findElement(By.cssSelector(".ag-checkbox-input"));
        cell1Checkbox.click();

        WebElement cell1Value = cell1.findElement(By.cssSelector(".ag-cell-value"));
        String text = cell1Value.getText();
        System.out.println("Cell value:" + text);

        delayFor(3000);
    }

    @Test
    public void test2(){
        delayFor(3000);

        WebElement tableBody = driver.findElement(By.cssSelector("div#myGrid div.ag-body"));
        List<WebElement> rows = tableBody.findElements(By.cssSelector("div[role='row']"));

        List<WebElement> cells = rows.get(0).findElements(By.cssSelector("div[role='gridcell']"));

        WebElement cell1 = cells.get(5);

        //js.highlight(cell1);
        //WebElement cell1Value = cell1.findElement(By.cssSelector(".ag-cell-value"));
        //String text = cell1.getAttribute("col-id");
        String text = cell1.getText();
        System.out.println("Cell value:" + text);

        Actions act = new Actions(driver);
        act.doubleClick(cell1).perform();

        WebElement inputBox = cell1.findElement(By.cssSelector("input"));
        inputBox.clear();
        inputBox.sendKeys("5000");
        inputBox.sendKeys(Keys.ENTER);



        delayFor(3000);
    }

    @Test
    public void test3(){
        delayFor(3000);

        WebElement tableBody = driver.findElement(By.cssSelector("div#myGrid div.ag-body"));
        List<WebElement> rows = tableBody.findElements(By.cssSelector("div[role='row']"));

        List<WebElement> cells = rows.get(0).findElements(By.cssSelector("div[role='gridcell']"));

        WebElement cell1 = cells.get(1);
        Actions act = new Actions(driver);
        act.doubleClick(cell1).perform();
        delayFor(3000);


        //WebElement listbox = cell1.findElement(By.xpath(".//div[@role='listbox']"));
        //js.highlight(listbox);
//        List<WebElement> listboxItems = listbox.findElements(By.tagName("span"));
//        for(WebElement item : listboxItems){
//            System.out.println(item.getText());
//        }
        //div[@id='myGrid']/div/div[@role='presentation']/div[6]/div[@role='presentation']/div[@role='listbox']/div[2]//span[.='Spanish']
        //js.highlight(cell1);
        //WebElement cell1Value = cell1.findElement(By.cssSelector(".ag-cell-value"));
        //String text = cell1.getAttribute("col-id");

        //div[role='listbox'] > div:nth-of-type(10)  span
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
