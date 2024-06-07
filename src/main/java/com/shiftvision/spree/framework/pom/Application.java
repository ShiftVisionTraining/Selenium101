package com.shiftvision.spree.framework.pom;

import org.openqa.selenium.WebDriver;

public class Application {

    protected WebDriver driver;

    private LoginPage loginPage;
    private HomePage homePage;

    public Application(){
        this.driver = DriverFactory.getInstance().getDriver();
    }

    public void waitFor(int timeToWait){
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToApplication(){
        driver.navigate().to("https://spree.shiftvision.com/");
    }
    public LoginPage loginPage(){
        if(loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public HomePage homePage(){
        if(homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }



}
