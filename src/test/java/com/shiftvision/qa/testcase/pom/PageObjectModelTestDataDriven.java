package com.shiftvision.qa.testcase.pom;

import com.shiftvision.spree.framework.pom.PageObjectModelScriptBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class PageObjectModelTestDataDriven extends PageObjectModelScriptBase {

    //@Test
    @ParameterizedTest
    @CsvSource({
            "Success,test@shiftvision.com,shift123,Logged in successfully",
            "Invalid,invalid@shiftvision.com,shift123,Invalid email or password.",
            "Invalid,test@shiftvision.com,invalid,Invalid email or password.",
            "Validation,@shiftvision.com,shift123,Please enter a part followed by '@'. '@shiftvision.com' is incomplete.",
            "Validation,invalid,shift123,Please include an '@' in the email address. 'invalid' is missing an '@'.",
            "Validation,test,shift123,Please include an '@' in the email address. 'test' is missing an '@'."
    })
    public void validUserValidPasswordTest(String validationType, String email, String password, String message){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login(email,password);
        spree().waitFor(2000);
        if(validationType.contentEquals("Success")) {
            spree().homePage().verifySuccessMessage(message);
        } else if(validationType.contentEquals("Invalid")){
            spree().loginPage().verifyErrorMessage(message);
        } else if(validationType.contentEquals("Validation")) {
            spree().loginPage().verifyEmailValidationMessage(message);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/SVTestData - Sheet1.csv",numLinesToSkip = 1)
    public void validUserValidPasswordTest2(String validationType, String email, String password, String message){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login(email,password);
        spree().waitFor(2000);
        if(validationType.contentEquals("Success")) {
            spree().homePage().verifySuccessMessage(message);
        } else if(validationType.contentEquals("Invalid")){
            spree().loginPage().verifyErrorMessage(message);
        } else if(validationType.contentEquals("Validation")) {
            spree().loginPage().verifyEmailValidationMessage(message);
        }
    }

//    @Test
//    public void invalidUserValidPasswordTest1(){
//        spree().homePage().navigateToLoginPage();
//        spree().loginPage().login("invalid@shiftvision.com","shift123");
//        spree().waitFor(2000);
//        spree().loginPage().verifyErrorMessage("Invalid email or password.");
//    }
//    @Test
//    public void invalidUserValidPasswordTest2(){
//        spree().homePage().navigateToLoginPage();
//        spree().loginPage().login("@shiftvision.com","shift123");
//        spree().waitFor(2000);
//        spree().loginPage().verifyEmailValidationMessage("Please enter a part followed by '@'. '@shiftvision.com' is incomplete.");
//    }
//    @Test
//    public void invalidUserValidPasswordTest3(){
//        spree().homePage().navigateToLoginPage();
//        spree().loginPage().login("invalid","shift123");
//        spree().waitFor(2000);
//        spree().loginPage().verifyEmailValidationMessage("Please include an '@' in the email address. 'invalid' is missing an '@'.");
//    }
//
//    //validationMessage
//    @Test
//    public void validUserInvalidPasswordTest(){
//        spree().homePage().navigateToLoginPage();
//        spree().loginPage().login("test@shiftvision.com","invalid");
//        spree().waitFor(2000);
//        spree().loginPage().verifyErrorMessage("Invalid email or password.");
//    }
}
