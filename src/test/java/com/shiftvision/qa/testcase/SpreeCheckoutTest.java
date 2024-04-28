package com.shiftvision.qa.testcase;

import com.shiftvision.spree.framework.KeywordScriptBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SpreeCheckoutTest extends KeywordScriptBase {


    @Test
    public void test1(){
        navigateTo("https://spree.shiftvision.com/");

        click(By.xpath("//li[@id='link-to-login']/a"));
        waitUntilTitleIs("Login - Spree Demo Site");

        typeText(By.id("spree_user_email"), "test@shiftvision.com");
        typeText(By.id("spree_user_password"), "shift123");
        click(By.id("spree_user_remember_me"));
        click(By.name("commit"));
        waitUntilElementVisible(By.cssSelector(".alert-success"));
        verifyText(By.cssSelector(".alert-success"), "Logged in successfully");

        click(By.xpath("//div[@id='products']//div[@class='panel panel-default']//span[@title='Ruby on Rails Tote']"));
        waitUntilTitleIs("Ruby on Rails Tote - Spree Demo Site");

        typeText(By.id("quantity"), "2");
        click(By.id("add-to-cart-button"));
        waitUntilTitleIs("Shopping Cart - Spree Demo Site");

        verifyText(By.cssSelector(".cart-total td.lead"), "$33.58");
        click(By.id("checkout-link"));
        waitUntilElementVisible(By.id("checkout"));

        //Enter all address info
        click(By.xpath("//input[@name='commit' and @value='Save and Continue']"));


        verifyIsChecked(By.xpath("//ul/li//span[text()='UPS Ground (USD)']/../input"));


        delayFor(3000);
    }






}
