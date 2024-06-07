package com.shiftvision.spree.framework.pom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PageObjectModelScriptBase {
    protected Application application;

    @BeforeEach
    public void setUp(){
        System.out.println("BeforeEach...");
        application = ApplicationFactory.getInstance().getApplication();
        application.navigateToApplication();
    }

    @AfterEach
    public void tearDown(){
        System.out.println("AfterEach...");
        ApplicationFactory.getInstance().quitApplication();
    }

    public Application spree(){
        return application;
    }

    public void waitFor(int timeToWait){
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
