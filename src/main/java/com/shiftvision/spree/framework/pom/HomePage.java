package com.shiftvision.spree.framework.pom;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    @FindBy(linkText = "Login")
    private WebElement loginLink;
    @FindBy(css = ".alert.alert-success")
    private WebElement successAlert;

    public HomePage(){
        this.driver = DriverFactory.getInstance().getDriver();;
        PageFactory.initElements(driver,this);
    }


    public void navigateToLoginPage(){
       loginLink.click();
    }

    public void verifySuccessMessage(String expectedMessage){
        String successText = successAlert.getText();
        Assertions.assertEquals(expectedMessage, successText);
    }
}
