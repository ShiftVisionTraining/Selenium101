package com.shiftvision.spree.framework.pom;

import org.openqa.selenium.WebDriver;

public class ApplicationFactory {
    private static ApplicationFactory instance = null;
    private ThreadLocal<Application> applications = new ThreadLocal<Application>();

    private WebDriver driver;
    private ApplicationFactory(){
        driver = DriverFactory.getInstance().getDriver();
    }

    public static ApplicationFactory getInstance(){
        if(instance == null){
            instance = new ApplicationFactory();
        }
        return instance;
    }

    public Application getApplication(){
        if(instance.applications.get() == null){
            instance.applications.set(new Application());
        }

        return instance.applications.get();
    }

    public void quitApplication(){
        instance.applications.remove();
        DriverFactory.getInstance().quit();
    }

}
