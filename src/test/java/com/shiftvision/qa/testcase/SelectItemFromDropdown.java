package com.shiftvision.qa.testcase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
public class SelectItemFromDropdown {
    private WebDriver driver;
    @BeforeEach
    public void setUp(){
        System.out.println("BeforeEach...");
        String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverFile);
        driver = new ChromeDriver();
        driver.navigate().to("http://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void test1(){
        waitFor(5000);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText("Option 2");
        waitFor(5000);
        dropdownSelect.selectByIndex(1);
        waitFor(5000);
        dropdownSelect.selectByValue("2");
        waitFor(5000);
    }

    @Test
    public void test2() {
        waitFor(2000);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText("Option 2");

        String selectedText = dropdownSelect.getFirstSelectedOption().getText();
        Assertions.assertEquals("Option 2", selectedText);
    }

    @Test
    public void test3() {
        waitFor(2000);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText("Option 2");

        List<WebElement> allSelectedItem =  dropdownSelect.getAllSelectedOptions();
        List<String> itemText = new ArrayList<>();
        for(WebElement option :  allSelectedItem){
            String text = option.getText();
            itemText.add(text);
        }

        System.out.println(itemText);


        String[] actualArray = new String[itemText.size()];
        actualArray = itemText.toArray(actualArray);

        String[] expectedArray =  {"Option 2"};

        Assertions.assertArrayEquals(expectedArray,actualArray);
    }

    @Test
    public void test4() {
        waitFor(2000);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText("Option 2");

        List<WebElement> allSelectedItem =  dropdownSelect.getAllSelectedOptions();
        List<String> itemText = new ArrayList<>();
        for(WebElement option :  allSelectedItem){
            String text = option.getText();
            itemText.add(text);
        }

        org.assertj.core.api.Assertions.assertThat(itemText).containsExactly("Option 2");


    }

    @Test
    public void test5() {
        waitFor(2000);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText("Option 2");

        List<WebElement> allSelectedItem =  dropdownSelect.getAllSelectedOptions();

        List<String> itemText =  allSelectedItem.stream().map( item -> item.getText()).toList();

        org.assertj.core.api.Assertions.assertThat(itemText).containsExactly("Option 2");

    }

    @Test
    public void test6() {
        waitFor(2000);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdown);

        List<WebElement> options = dropdownSelect.getOptions();
        ArrayList<String> optionsText = new ArrayList<>();
        for(WebElement option : options ){
            optionsText.add(option.getText());
        }

        System.out.println(optionsText);
        org.assertj.core.api.Assertions.assertThat(optionsText)
                .containsExactly("Please select an option","Option 1","Option 2");

    }

    @Test
    public void test7() {
        waitFor(2000);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdown);
        //dropdownSelect.selectByVisibleText("Option 2");

        List<WebElement> options = dropdownSelect.getOptions();
        for(WebElement option : options ){
           String optionText = option.getText();
           if(optionText.contentEquals("Option 2")){
               option.click();
               break;
           }
        }

        waitFor(5000);

    }

    @Test
    public void test8() {
        waitFor(2000);
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        for(WebElement option : options ){
            String optionText = option.getText();
            if(optionText.contentEquals("Option 2")){
                option.click();
                break;
            }
        }

        waitFor(5000);

    }

    @Test
    public void test9() {
        waitFor(2000);
        selectOption(By.id("dropdown"),"Option 2");
        waitFor(5000);
    }

    @Test
    public void test10() {
        waitFor(2000);
        List<String> optionsText = getAllAvailableOptions(By.id("dropdown"));

       // org.assertj.core.api.Assertions.assertThat(optionsText)
       //          .containsExactly("Please select an option","Option 1","Option 2");

        org.assertj.core.api.Assertions.assertThat(optionsText)
                .containsAnyOf("Option 1","Option 2");

        waitFor(5000);
    }



    public void selectOption(By by, String itemToSelect){
        WebElement dropdown = driver.findElement(by);
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        for(WebElement option : options ){
            String optionText = option.getText();
            if(optionText.contentEquals(itemToSelect)){
                option.click();
                break;
            }
        }
    }

    public List<String> getAllAvailableOptions(By by){
        ArrayList<String> optionsText = new ArrayList<>();
        WebElement dropdown = driver.findElement(by);
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        for(WebElement option : options ){
            String optionText = option.getText();
            optionsText.add(optionText);
        }
        return optionsText;
    }


    @AfterEach
    public void tearDown(){
        System.out.println("AfterEach...");
        driver.close();
        driver.quit();
    }


    public void waitFor(int timeToWait){
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
