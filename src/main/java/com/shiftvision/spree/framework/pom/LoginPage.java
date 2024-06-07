package com.shiftvision.spree.framework.pom;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    //Model Section
    @FindBy(id = "spree_user_email")
    private  WebElement emailTextbox;
    @FindBy(id = "spree_user_password")
    private WebElement passwordTextbox;
    @FindBy(name = "commit")
    private WebElement loginButton;

    @FindBy(css = ".alert.alert-error")
    private WebElement errorAlert;


    public LoginPage(){
        this.driver = DriverFactory.getInstance().getDriver();;
        PageFactory.initElements(driver,this);
    }

    //Controller Section
    public void login(String email, String password) {
        emailTextbox.sendKeys(email);
        passwordTextbox.sendKeys(password);
        loginButton.click();
    }

    public void verifyErrorMessage(String expectedMsg){
        String errorText = errorAlert.getText();
        Assertions.assertEquals(expectedMsg, errorText);
    }
}
