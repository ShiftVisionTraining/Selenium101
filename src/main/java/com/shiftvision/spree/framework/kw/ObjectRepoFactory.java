package com.shiftvision.spree.framework.kw;

import org.openqa.selenium.By;

import java.util.Collection;
import java.util.HashMap;

public class ObjectRepoFactory {
    private static ObjectRepoFactory instance = null;
    private static String EXCEL_FILE_LOCATION = System.getProperty("user.dir") + "/src/test/resources/kw_scripts/HeatClinicObjectRepo.xlsx";

    private HashMap<String, HashMap<String, String>> locatorRepository = new HashMap<>();

    private ObjectRepoFactory(){
        Collection<PageList> pageList = PageList.load(EXCEL_FILE_LOCATION,"PageList");
        System.out.println("********** Loading Object Repository ***********" );
        for(PageList page : pageList) {
            System.out.println("loading page locator for : " + page.toString());
            String pageName = page.getPageName();
            Collection<PageLocator> pageLocator = PageLocator.load(EXCEL_FILE_LOCATION, page.getTabName());

            HashMap<String, String> item = new HashMap<>();
            for(PageLocator locator : pageLocator){
                System.out.println("loading locator for : " + locator.toString());
                String locatorName = locator.getName();
                String locatorString = locator.getLocator();
                item.put(locatorName, locatorString);
            }
            locatorRepository.put(pageName,item);
        }
        System.out.println("********** Object Repository Loaded ***********" );
    }

    public By getPageLocatorBy(String pageName, String pageLocatorName){
        By by = null;
        String locatorByString = locatorRepository.get(pageName).get(pageLocatorName);
        if(locatorByString != null && locatorByString.length() > 0 && locatorByString.contains(":") ) {
            String locatorParts[] = locatorByString.split(":");
            if (locatorParts != null && locatorParts.length > 0) {
                String locatorByName = locatorParts[0];
                String locatorByValue = locatorParts[1];
                by = getBy(locatorByName,locatorByValue);
            } else {
                throw new RuntimeException("There is no locator for the by object ");
            }
        } else{
            throw new RuntimeException("There is no locator for the by object ");
        }
        return by;
    }

    private By getBy(String locatorBy, String locator) {
        By by = null;

        if (locatorBy != null && locator != null) {
            if (locatorBy.equalsIgnoreCase("LINK_TEXT")) {
                by = By.linkText(locator);
            } else if (locatorBy.equalsIgnoreCase("PARTIAL_LINK_TEXT")) {
                by = By.partialLinkText(locator);
            } else if (locatorBy.equalsIgnoreCase("ID")) {
                by = By.id(locator);
            } else if (locatorBy.equalsIgnoreCase("NAME")) {
                by = By.name(locator);
            } else if (locatorBy.equalsIgnoreCase("CSS")) {
                by = By.cssSelector(locator);
            } else if (locatorBy.equalsIgnoreCase("TAG_NAME")) {
                by = By.tagName(locator);
            } else if (locatorBy.equalsIgnoreCase("XPATH")) {
                by = By.xpath(locator);
            } else if (locatorBy.equalsIgnoreCase("CLASS_NAME")) {
                by = By.className(locator);
            }
        }
        return by;
    }

    public static ObjectRepoFactory getInstance(){
        if(instance == null){
            instance = new ObjectRepoFactory();
        }
        return instance;
    }
}
