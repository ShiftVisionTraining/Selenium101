package com.shiftvision.spree.framework.kw;

import com.shiftvision.spree.framework.pom.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionFactory {
    private static ActionFactory instance;
    private DriverFactory driverFactory = null;

    private ActionFactory(){
        driverFactory = DriverFactory.getInstance();
    }
    public static ActionFactory getInstance(){
        if(instance == null){
            instance = new ActionFactory();
        }
        return instance;
    }


    public void navigateToApp(String url){
        driverFactory.getDriver().navigate().to(url);
    }
    public void closeBrowser(){
        driverFactory.quit();
    }
    public void delayFor(int timeInMili){
        try {
            Thread.sleep(timeInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void typeText(WebElement element, String textToEnter){
        if(element != null) {
            element.click();
            element.clear();
            element.sendKeys(textToEnter);
        }
    }
    public void typeText(By by , String textToEnter){
        WebElement element = driverFactory.getDriver().findElement(by);
        if(element != null) {
            element.click();
            element.clear();
            element.sendKeys(textToEnter);
        }
    }
    public void click(By by){
        WebElement element = driverFactory.getDriver().findElement(by);
        if(element != null) {
            element.click();
        }
    }
    public String getText(By by){
        String text = "";
        WebElement element = driverFactory.getDriver().findElement(by);
        if(element != null){
            text = element.getText();
        }
        return text;
    }
    public String getTitle(){
        String title = driverFactory.getDriver().getTitle();
        return title;
    }


    public void verifyText(By by, String testData){
        WebElement element = driverFactory.getDriver().findElement(by);
        if(element != null){
            String actualText = element.getText();
            assertThat(actualText).containsIgnoringCase(testData);
        }
    }

}
