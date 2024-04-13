package com.shiftvision.spree.framework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScriptBase {
    protected WebDriver driver;

    protected HomePage homePage;
    protected LoginPage loginPage;

    @BeforeEach
    public void setUp(){
        System.out.println("BeforeEach...");
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        driver.navigate().to("https://spree.shiftvision.com/");

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
