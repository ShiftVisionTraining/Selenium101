package com.shiftvision.spree.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String email, String password) {
        WebElement emailTextbox = driver.findElement(By.id("spree_user_email"));
        emailTextbox.sendKeys(email);

        WebElement passwordTextbox = driver.findElement(By.id("spree_user_password"));
        passwordTextbox.sendKeys(password);

        WebElement loginButton = driver.findElement(By.name("commit"));
        loginButton.click();
    }

    public String getErrorMessage(){
        //waitFor(1000);
        WebElement errorAlert = driver.findElement(By.cssSelector(".alert.alert-error"));
        String errorText = errorAlert.getText();
        return errorText;
    }
}
