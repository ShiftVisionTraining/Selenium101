package com.shiftvision.spree.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToLoginPage(){
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();
        //waitFor(2000);
    }

    public String getSuccessMessage(){
        //waitFor(1000);
        WebElement successAlert = driver.findElement(By.cssSelector(".alert.alert-success"));
        String successText = successAlert.getText();
        return successText;
    }
}
