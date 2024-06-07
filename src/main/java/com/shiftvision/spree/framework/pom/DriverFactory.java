package com.shiftvision.spree.framework.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static DriverFactory instance = null;
    private final ThreadLocal<WebDriver> driverCollection = new ThreadLocal<WebDriver>();

    private static String browser = "CHROME";

    private DriverFactory(){
        System.out.println("Creating driver factory...");
    }

    public static DriverFactory getInstance(){
        if(instance == null){
            instance = new DriverFactory();
        }
        return instance;
    }

    public WebDriver getDriver() {
        createDriver();
        return driverCollection.get();
    }

    public void quit() {
        // Quits the driver and closes the browser
        try {
            driverCollection.get().quit();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            driverCollection.remove();
        }
    }


    private void createDriver(){
        browser = System.getenv("browser");
        if(browser == null || browser.length() == 0){
            browser = System.getProperty("browser");
        }
        if(browser == null) {
            browser = "CHROME";
        }

        if(instance.driverCollection.get() == null) {
            WebDriver driver = null;
            if(DriverFactory.browser.toUpperCase().contentEquals("CHROME")) {
                String driverFile = System.getProperty("user.dir") + "/src/main/resources/drivers/chrome/win/chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", driverFile);
                driver = new ChromeDriver();
            }
            instance.driverCollection.set(driver);
        }



    }


}
