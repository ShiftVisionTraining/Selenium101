package com.shiftvision.qa.testcase.pom;

import com.shiftvision.spree.framework.pom.PageObjectModelScriptBase;
import org.junit.jupiter.api.Test;

public class PageObjectModelTest extends PageObjectModelScriptBase {

    @Test
    public void validUserValidPasswordTest(){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login("test@shiftvision.com","shift123");
        spree().waitFor(2000);
        spree().homePage().verifySuccessMessage("Logged in successfully");
    }

    @Test
    public void invalidUserValidPasswordTest(){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login("invalid@shiftvision.com","shift123");
        spree().waitFor(2000);
        spree().loginPage().verifyErrorMessage("Invalid email or password.");
    }
}
