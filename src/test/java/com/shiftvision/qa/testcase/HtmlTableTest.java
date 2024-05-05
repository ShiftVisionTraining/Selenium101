package com.shiftvision.qa.testcase;

import com.shiftvision.spree.framework.utils.HtmlTableUtils;
import com.shiftvision.spree.framework.utils.JavascriptExecutorUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HtmlTableTest {
    private WebDriver driver;
    private Wait<WebDriver> wait = null;
    private JavascriptExecutorUtils js;

    @BeforeEach
    public void setUp(){
        //String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        //System.setProperty("webdriver.chrome.driver",driverFile);
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

        //driver.navigate().to("https://developer.mozilla.org/en-US/docs/Learn/HTML/Tables/Basics");

    }

    @Test
    public void test1(){
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        WebElement table = driver.findElement(By.id("customers"));
        js.highlight(table);

        WebElement tBody = table.findElement(By.tagName(("tbody")));
        List<WebElement> trs = tBody.findElements(By.tagName("tr"));
        for(WebElement tr : trs){
            List<WebElement> tds = tr.findElements(By.xpath("./th | ./td"));
            for(WebElement td : tds){
                String tdText = td.getText();
                System.out.println(tdText);
            }
        }


        delayFor(3000);
    }

    @Test
    public void test2(){
        try {
            driver.navigate().to("https://www.w3schools.com/html/html_tables.asp");

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customers")));
       // WebElement table = driver.findElement(By.id("customers"));
        js.highlight(table);

        List<WebElement> rows = getAllTableRows(table);
        List<WebElement> Cells = getAllTableRowCells(rows.get(0));
        WebElement cell = Cells.get(0);
        String tdText = cell.getText();
        System.out.println(tdText);

        delayFor(3000);
    }

    @Test
    public void test3(){
        try {
            driver.navigate().to("https://www.w3schools.com/html/html_tables.asp");

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customers")));
        js.highlight(table);

        WebElement cell = getTableCell(table, 1,1);
        String tdText = cell.getText();
        System.out.println(tdText);

        delayFor(3000);
    }

    @Test
    public void test4(){
        try {
            driver.navigate().to("https://www.w3schools.com/html/html_tables.asp");

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customers")));
        js.highlight(table);

        List<List<String>> tableData = getTableData(table);
        for(List<String> l : tableData){
            for(String s : l){
                System.out.println(s);
            }
        }

        System.out.println("Row 2, Col 1: " + tableData.get(1).get(0));


        delayFor(3000);
    }

    @Test
    public void test5(){
        try {
            driver.navigate().to("https://www.w3schools.com/html/html_tables.asp");

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        HtmlTableUtils htmlTableUtils = new HtmlTableUtils(driver);
        htmlTableUtils.setEnableHighlight(true);

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customers")));
        js.highlight(table);

        List<List<String>> tableData = htmlTableUtils.getTableData(table);
        for(List<String> l : tableData){
            for(String s : l){
                System.out.println(s);
            }
        }

        System.out.println("Row 2, Col 1: " + tableData.get(1).get(0));


        delayFor(3000);
    }



    public List<WebElement> getAllTableRows(WebElement table){
        WebElement tBody = table.findElement(By.tagName(("tbody")));
        //js.highlight(tBody);
        List<WebElement> trs = tBody.findElements(By.tagName("tr"));
        //System.out.println("Size of the rows:" + trs.size());
        return trs;
    }
    public List<WebElement> getAllTableRowCells(WebElement row){
        //js.highlight(row);
        List<WebElement> tds = row.findElements(By.xpath("./th | ./td"));
        //List<WebElement> tds = row.findElements(By.tagName("td"));
        //System.out.println("Size of the Cells:" + tds.size());
        return tds;
    }

    public WebElement getTableCell(WebElement table, int row, int coll){
        List<WebElement> rows = getAllTableRows(table);
        List<WebElement> Cells = getAllTableRowCells(rows.get(row));
        WebElement cell = Cells.get(coll);
        js.highlight(cell);
        return cell;
    }

    public List<List<String>> getTableData(WebElement table){
        List<List<String>> tablesData = new ArrayList<>();

        List<WebElement> rows = getAllTableRows(table);
        for(WebElement row : rows){
            List<String> tableRow = new ArrayList<>();
            tablesData.add(tableRow);
            List<WebElement> cells = getAllTableRowCells(row);
            for(WebElement cell : cells){
                //js.highlight(cell);
                String cellText = cell.getText();
                tableRow.add(cellText);
            }
        }
        return tablesData;
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
