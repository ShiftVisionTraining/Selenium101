package com.shiftvision.qa.testcase;

import com.shiftvision.spree.framework.ScriptBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeleniumTest3  extends ScriptBase {

    @Test
    public void validUserValidPassword(){
        System.out.println("Test-test1...");

        homePage.navigateToLoginPage();
        loginPage.login("test@shiftvision.com", "shift123");

        String successText = homePage.getSuccessMessage();
        Assertions.assertEquals("Logged in successfully", successText);
    }

    @Test
    public void validUserInvalidPassword(){
        System.out.println("Test-test2...");

        homePage.navigateToLoginPage();
        loginPage.login("test@shiftvision.com", "shift124");

        String errorText = loginPage.getErrorMessage();
        Assertions.assertEquals("Invalid email or password.", errorText);

    }

    @Test
    public void InvalidUserValidPassword(){
        System.out.println("Test-test3...");

        homePage.navigateToLoginPage();
        loginPage.login("testxxx@shiftvision.com", "shift123");

        String errorText = loginPage.getErrorMessage();
        Assertions.assertEquals("Invalid email or password.", errorText);

    }

}
