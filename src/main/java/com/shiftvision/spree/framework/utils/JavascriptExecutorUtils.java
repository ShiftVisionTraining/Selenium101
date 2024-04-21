package com.shiftvision.spree.framework.utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class JavascriptExecutorUtils {
    private JavascriptExecutor jsDriver = null;

    public JavascriptExecutorUtils(WebDriver driver){
        jsDriver = (JavascriptExecutor) driver;
    }

    public void click(WebElement element) {
        this.jsDriver.executeScript("arguments[0].click()", element);
    }
    public void click(By by) {
        WebDriver driver = (WebDriver) jsDriver;
        WebElement element = driver.findElement(by);
        this.jsDriver.executeScript("arguments[0].click()", element);
    }

    public void highlight(WebElement element) {
        for (int i = 0; i < 2; i++) {
            jsDriver.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "border: 2px solid red;");
            delayFor(200);
            jsDriver.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "");
            delayFor(200);
        }
    }
    public void highlight(By by) {
        WebDriver driver = (WebDriver) jsDriver;
        WebElement element = driver.findElement(by);

        for (int i = 0; i < 2; i++) {
            jsDriver.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "border: 2px solid red;");
            delayFor(200);
            jsDriver.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "");
            delayFor(200);
        }
    }
    public void highlight(WebElement element, boolean enable) {

        if(enable == true) {
            jsDriver.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "border: 2px solid red;");
        }
        else {
            jsDriver.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "");
        }

    }
    public void highlight(By by, boolean enable) {

        WebDriver driver = (WebDriver) jsDriver;
        WebElement element = driver.findElement(by);

        if(enable == true) {
            jsDriver.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "border: 2px solid red;");
        }
        else {
            jsDriver.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "");
        }

    }

    public void scrollElementIntoView(WebElement element) {
        //this is for js scroll the browser page
        jsDriver.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    public void setAttribute(WebElement element, String attributeName, String attributeValue) {
        jsDriver.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, attributeName, attributeValue);
    }

    public void dragAndDrop(WebElement source, WebElement dest){
        final String java_script =
                "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe" +
                        "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun" +
                        "ction(format,data){this.items[format]=data;this.types.append(for" +
                        "mat);},getData:function(format){return this.items[format];},clea" +
                        "rData:function(format){}};var emit=function(event,target){var ev" +
                        "t=document.createEvent('Event');evt.initEvent(event,true,false);" +
                        "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('" +
                        "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit(" +
                        "'drop',tgt);emit('dragend',src);";

        jsDriver.executeScript(java_script, source, dest);

    }

    private void delayFor(int secInMili) {
        try {
            Thread.sleep(secInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
