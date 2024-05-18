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

        //div.ag-popup .ag-virtual-list-viewport .ag-virtual-list-container div[role=option]
        WebElement listbox = driver.findElement(By.cssSelector("div.ag-popup .ag-virtual-list-container[role=listbox]"));
        WebElement listboxItem = listbox.findElement(By.xpath("./div[@role='option']//span[text()='French']"));
        listboxItem.click();

       // List<WebElement> listboxItems = listbox.findElements(By.cssSelector("div[role=option]"));
//        for(WebElement item : listboxItems){
//            String text = item.getText();
//            System.out.println(text);
//            if(text.contentEquals("French")){
//                item.click();
//            }
//        }
        //div[@id='myGrid']/div/div[@role='presentation']/div[6]/div[@role='presentation']/div[@role='listbox']/div[2]//span[.='Spanish']
        //js.highlight(cell1);
        //WebElement cell1Value = cell1.findElement(By.cssSelector(".ag-cell-value"));
        //String text = cell1.getAttribute("col-id");

        //div[role='listbox'] > div:nth-of-type(10)  span
        delayFor(3000);
    }

    @Test
    public void test4(){
        delayFor(3000);

        WebElement tableBody = driver.findElement(By.cssSelector("div#myGrid div.ag-body"));
        List<WebElement> rows = tableBody.findElements(By.cssSelector("div[role='row']"));

        List<WebElement> cells = rows.get(0).findElements(By.cssSelector("div[role='gridcell']"));

        WebElement cell1 = cells.get(1);
        Actions act = new Actions(driver);
        act.doubleClick(cell1).perform();
        delayFor(3000);

        //div.ag-popup .ag-virtual-list-viewport .ag-virtual-list-container div[role=option]
        WebElement listbox = driver.findElement(By.cssSelector("div.ag-popup .ag-virtual-list-container[role=listbox]"));
        delayFor(1000);
        List<WebElement> listboxItems = listbox.findElements(By.cssSelector("div[role=option] span"));
        for(WebElement item : listboxItems){
            String text = item.getText();
            //System.out.println(text);
            if(text.contentEquals("French")){
                item.click();
                break;
            }
        }
        delayFor(3000);
    }

    @Test
    public void test5(){
        delayFor(3000);

        WebElement tableBody = driver.findElement(By.cssSelector("div#myGrid div.ag-body"));
        List<WebElement> rows = tableBody.findElements(By.cssSelector("div[role='row']"));

        List<WebElement> cells = rows.get(0).findElements(By.cssSelector("div[role='gridcell']"));

        WebElement cell1 = cells.get(1);
        Actions act = new Actions(driver);
        act.doubleClick(cell1).perform();
        delayFor(2000);

        selectAgGridListItem("French");

        WebElement cell2 = cells.get(2);
        Actions act2 = new Actions(driver);
        act2.doubleClick(cell2).perform();
        delayFor(2000);

        selectAgGridListItem("Italy");

        WebElement cell3 = cells.get(3);
        selectAgGridListItem(cell3,"Bul");

        delayFor(3000);
    }

    public void selectAgGridListItem(String itemText){
        WebElement listbox = driver.findElement(By.cssSelector("div.ag-popup .ag-virtual-list-container[role=listbox]"));
        delayFor(1000);
        List<WebElement> listboxItems = listbox.findElements(By.cssSelector("div[role=option] span"));
        for(WebElement item : listboxItems){
            String text = item.getText();
            //System.out.println(text);
            if(text.contentEquals(itemText)){
                item.click();
                break;
            }
        }
    }

    public void selectAgGridListItem(WebElement cell, String itemText){
        Actions act = new Actions(driver);
        act.doubleClick(cell).perform();
        delayFor(2000);

        WebElement listbox = driver.findElement(By.cssSelector("div.ag-popup .ag-virtual-list-container[role=listbox]"));
        delayFor(1000);
        List<WebElement> listboxItems = listbox.findElements(By.cssSelector("div[role=option] span"));
        for(WebElement item : listboxItems){
            String text = item.getText();
            //System.out.println(text);
            if(text.contentEquals(itemText)){
                item.click();
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
