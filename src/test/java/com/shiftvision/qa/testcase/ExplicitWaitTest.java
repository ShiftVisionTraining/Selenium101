package com.shiftvision.qa.testcase;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ExplicitWaitTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp() throws MalformedURLException {
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();

        //driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        //driver.manage().timeouts().setScriptTimeout(1,TimeUnit.SECONDS);
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(1));

        driver.navigate().to(new URL("https://spree.shiftvision.com/"));
    }

    @Test
    public void test0(){
        WebElement loginLink = driver.findElement(By.linkText("LOGIN"));
        loginLink.click();
        delayFor(2000);
    }

    @Test
    public void test1(){

       // WebDriverWait wait = new WebDriverWait(driver, 20, 100); old way
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(100));
        WebElement loginLink =  wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.linkText("Login"));
                if (element != null) {
                    return element;
                }
                return null;
            }
        });
        loginLink.click();

        delayFor(2000);
    }

    @Test
    public void test2(){
        // https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(100));
        //WebElement loginLink =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("LOGIN")));
        WebElement loginLink =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
        loginLink.click();
        delayFor(2000);

    }

    @Test
    public void test3(){
        waitForElement(By.linkText("Login")).click();
        waitForElement(By.id("spree_user_email")).sendKeys("test@shiftvision.com");
        waitForElement(By.id("spree_user_password")).sendKeys("shift123");
        waitForElementClickable(By.name("commit"),10,100).click();
    }

    public WebElement waitForElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(100));
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }
    public WebElement waitForElementClickable(By by, int timeOut, int sleepInMili){
        Wait<WebDriver> wait = null;
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofMillis(sleepInMili))
                .ignoring(NoSuchElementException.class);

        WebElement element =  wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
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
