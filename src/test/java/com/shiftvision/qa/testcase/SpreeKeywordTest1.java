package com.shiftvision.qa.testcase;

import com.shiftvision.spree.framework.KeywordScriptBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

public class SpreeKeywordTest1 extends KeywordScriptBase{

    @Test
    public void test1() {
        navigateTo("https://spree.shiftvision.com/");
        typeText(By.id("keywords"),"Rails");
        click(By.xpath("//input[@type='submit']"));
        String[] itemArray = {
                                "Ruby on Rails Tote",
                                "Ruby on Rails Bag",
                                "Ruby on Rails Baseball Jersey",
                                "Ruby on Rails Jr. Spaghetti",
                                "Ruby on Rails Ringer T-Shirt",
                                "Ruby on Rails Mug",
                                "Ruby on Rails Stein"
                             };
        verifyTextFromElements(By.cssSelector("div#products .product-list-item span.info"),itemArray);

        //verifyTextFromElements(By.cssSelector("div#products .product-list-item span.info"), "Ruby on Rails Tote|Ruby on Rails Bag|Ruby on Rails Baseball Jersey|Ruby on Rails Jr. Spaghetti|Ruby on Rails Ringer T-Shirt|Ruby on Rails Mug|Ruby on Rails Stein");

    }

    @Test
    public void test1Ex() {
        navigateTo("https://spree.shiftvision.com/");
        typeText(By.id("keywords"),"Rails");
        click(By.xpath("//input[@type='submit']"));
        verifyTextFromElements(By.cssSelector("div#products .product-list-item span.info"), "Ruby on Rails Tote|Ruby on Rails Bag|Ruby on Rails Baseball Jersey|Ruby on Rails Jr. Spaghetti|Ruby on Rails Ringer T-Shirt|Ruby on Rails Mug|Ruby on Rails Stein");
    }

    @Test
    public void test2() {
        navigateTo("https://spree.shiftvision.com/");
        typeText(By.id("keywords"),"Ruby");
        click(By.xpath("//input[@type='submit']"));
        List<String> itemNames = getTextFromElements(By.cssSelector("div#products .product-list-item span.info"));
        System.out.println(itemNames);
        List<String> itemPrices = getTextFromElements(By.cssSelector("div#products .product-list-item span.price"));
        System.out.println(itemPrices);

    }

    @Test
    public void test3() {
        navigateTo("https://spree.shiftvision.com/");
        typeText(By.id("keywords"),"Tote");
        click(By.xpath("//input[@type='submit']"));
        List<String> itemNames = getTextFromElements(By.cssSelector("div#products .product-list-item span.info"));
        System.out.println(itemNames);
        List<String> itemPrices = getTextFromElements(By.cssSelector("div#products .product-list-item span.price"));
        System.out.println(itemPrices);

    }

    public List<String> getTextFromElements(By by){
        List<WebElement> elementList = driver.findElements(by);
        ArrayList<String> itemTextList = new ArrayList<>();
        for(WebElement item : elementList){
            String text = item.getText();
            itemTextList.add(text);
        }
        return itemTextList;
    }

    public void verifyTextFromElements(By by, String textToVerify){
        List<WebElement> elementList = driver.findElements(by);
        ArrayList<String> itemTextList = new ArrayList<>();
        for(WebElement item : elementList){
            String text = item.getText();
            itemTextList.add(text);
        }

        String[] itemTextArray = textToVerify.split("\\|");
        List<String> itemTextListToVerify = new ArrayList<>();
        for(String item : itemTextArray){
            itemTextListToVerify.add(item);
        }

        assertThat(itemTextList).containsExactlyInAnyOrderElementsOf(itemTextListToVerify);

    }
    public void verifyTextFromElements(By by, String[] textToVerify){
        List<WebElement> elementList = driver.findElements(by);
        ArrayList<String> itemTextList = new ArrayList<>();
        for(WebElement item : elementList){
            String text = item.getText();
            itemTextList.add(text);
        }

        List<String> itemTextListToVerify = new ArrayList<>();
        for(String item : textToVerify){
            itemTextListToVerify.add(item);
        }

        assertThat(itemTextList).containsExactlyInAnyOrderElementsOf(itemTextListToVerify);

    }

}



//div#products .product-list-item span.info

//div#products .product-list-item span.price