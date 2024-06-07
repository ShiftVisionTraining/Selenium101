package com.shiftvision.qa.testcase;

import com.shiftvision.spree.framework.pom.DriverFactory;
import com.shiftvision.spree.framework.pom.HomePage;
import com.shiftvision.spree.framework.pom.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SpreePageObjectModelTest1 {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp(){
        System.out.println("BeforeEach...");
//        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver",driverFile);
//        driver = new ChromeDriver();
        driver.navigate().to("https://spree.shiftvision.com/");
        this.driver = DriverFactory.getInstance().getDriver();
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test
    public void validUserValidPassword(){
        homePage.navigateToLoginPage();
        loginPage.login("test@shiftvision.com","shift123");
        homePage.verifySuccessMessage("Logged in successfully");
        waitFor(5000);
    }

    @Test
    public void invalidUserValidPassword(){
        homePage.navigateToLoginPage();
        loginPage.login("invalid@shiftvision.com","shift123");
        loginPage.verifyErrorMessage("Invalid email or password.");
        waitFor(5000);
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
