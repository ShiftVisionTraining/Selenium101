package com.shiftvision.qa.testcase;

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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;

public class DynamicTableTest {
    private WebDriver driver;
    private Wait<WebDriver> wait = null;
    private JavascriptExecutorUtils js;

    private HashMap<String, Integer> columnIndex = null;
    private HashMap<String, Integer> namesIndex = null;

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

        driver.navigate().to("https://codepen.io/davizp/pen/xVGEwM");

    }


    @Test
    public void test1(){
        delayFor(3000);
        driver.switchTo().frame("result");
        WebElement tableBody = driver.findElement(By.xpath("//table[@id='example']/tbody"));
        WebElement cell1 = tableBody.findElement(By.xpath("./tr[1]/td[1]"));
        System.out.println(cell1.getText());

        WebElement cell2 = tableBody.findElement(By.xpath("./tr[1]/td[6]"));
        System.out.println(cell2.getText());

        String[] cells = new String[6];
        for(int i = 1; i <= 6 ; i++){
            WebElement cell = tableBody.findElement(By.xpath("./tr[1]/td[" + i + "]"));
            String text = cell.getText();
            cells[i-1] = text;
        }

        System.out.println(Arrays.deepToString(cells));

    }

    @Test
    public void test2(){
        delayFor(3000);
        driver.switchTo().frame("result");
        WebElement tableBody = driver.findElement(By.xpath("//table[@id='example']/tbody"));

        HashMap<String, Integer> namesIndex = new HashMap<>();
        for(int i = 1; i <= 10 ; i++){
            WebElement cell = tableBody.findElement(By.xpath("./tr[" + i + "]/td[1]"));
            String text = cell.getText();
            namesIndex.put(text, i);
        }

        Integer rowIndex = namesIndex.get("Airi Satou");
        String[] cells = new String[6];
        for(int i = 1; i <= 6 ; i++){
            WebElement cell = tableBody.findElement(By.xpath("./tr[" + rowIndex + "]/td[" + i + "]"));
            String text = cell.getText();
            cells[i-1] = text;
        }

        System.out.println(Arrays.deepToString(cells));

    }

    @Test
    public void test3(){
        delayFor(3000);
        driver.switchTo().frame("result");
        WebElement tableHeader = driver.findElement(By.xpath("//table[@id='example']/thead"));
        WebElement tableBody = driver.findElement(By.xpath("//table[@id='example']/tbody"));

        HashMap<String, Integer> columnIndex = new HashMap<>();
        for(int i = 1; i <= 6 ; i++){
            WebElement cell = tableHeader.findElement(By.xpath("./tr[1]/th[" + i + "]"));
            String text = cell.getText();
            columnIndex.put(text, i);
        }

        HashMap<String, Integer> namesIndex = new HashMap<>();
        for(int i = 1; i <= 10 ; i++){
            WebElement cell = tableBody.findElement(By.xpath("./tr[" + i + "]/td[1]"));
            String text = cell.getText();
            namesIndex.put(text, i);
        }

        Integer rowIndex = namesIndex.get("Airi Satou");
        Integer colIndex = columnIndex.get("Salary");

        WebElement cell = tableBody.findElement(By.xpath("./tr[" + rowIndex + "]/td[" + colIndex + "]"));
        String text = cell.getText();
        System.out.println(text);
    }

    @Test
    public void test4(){
        delayFor(3000);
        driver.switchTo().frame("result");
        WebElement table = driver.findElement(By.xpath("//table[@id='example']"));
        setTableRowColIndex(table);

        Integer rowIndex = namesIndex.get("Airi Satou");
        Integer colIndex = columnIndex.get("Salary");

        WebElement cell = table.findElement(By.xpath("./tbody/tr[" + rowIndex + "]/td[" + colIndex + "]"));
        String text = cell.getText();
        System.out.println(text);


    }


    public void setTableRowColIndex(WebElement table){
        WebElement tableHeader = table.findElement(By.xpath("./thead"));
        WebElement tableBody = table.findElement(By.xpath("./tbody"));

        columnIndex = new HashMap<>();
        for(int i = 1; i <= 6 ; i++){
            WebElement cell = tableHeader.findElement(By.xpath("./tr[1]/th[" + i + "]"));
            String text = cell.getText();
            columnIndex.put(text, i);
        }

        namesIndex = new HashMap<>();
        for(int i = 1; i <= 10 ; i++){
            WebElement cell = tableBody.findElement(By.xpath("./tr[" + i + "]/td[1]"));
            String text = cell.getText();
            namesIndex.put(text, i);
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
