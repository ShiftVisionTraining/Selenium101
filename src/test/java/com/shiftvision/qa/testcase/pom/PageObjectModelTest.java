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
    public void invalidUserValidPasswordTest1(){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login("invalid@shiftvision.com","shift123");
        spree().waitFor(2000);
        spree().loginPage().verifyErrorMessage("Invalid email or password.");
    }
    @Test
    public void invalidUserValidPasswordTest2(){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login("@shiftvision.com","shift123");
        spree().waitFor(2000);
        spree().loginPage().verifyEmailValidationMessage("Please enter a part followed by '@'. '@shiftvision.com' is incomplete.");
    }
    @Test
    public void invalidUserValidPasswordTest3(){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login("invalid","shift123");
        spree().waitFor(2000);
        spree().loginPage().verifyEmailValidationMessage("Please include an '@' in the email address. 'invalid' is missing an '@'.");
    }

    //validationMessage
    @Test
    public void validUserInvalidPasswordTest(){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login("test@shiftvision.com","invalid");
        spree().waitFor(2000);
        spree().loginPage().verifyErrorMessage("Invalid email or password.");
    }
}
