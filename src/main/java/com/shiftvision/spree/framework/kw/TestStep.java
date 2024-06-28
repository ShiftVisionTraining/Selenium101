package com.shiftvision.spree.framework.kw;

import com.ebay.xcelite.Xcelite;
import com.ebay.xcelite.annotations.Column;
import com.ebay.xcelite.reader.SheetReader;
import com.ebay.xcelite.sheet.XceliteSheet;
import org.openqa.selenium.By;

import java.io.File;
import java.util.Collection;

public class TestStep {
    @Column(name="Step")
    private int step;
    @Column(name="Description")
    private String description;
    @Column(name="Keyword")
    private String keyword;
    @Column(name="Locator")
    private String locator;
    @Column(name="Data")
    private String data;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public By getLocatorBy(){
        By by = null;
        if(locator != null && locator.length() > 0 && locator.contains(":") ) {
            String byWithLocator = locator;
            String parts[] = byWithLocator.split(":");
            if (parts != null && parts.length > 0) {
                String locatorBy = parts[0];
                String locatorValue = parts[1];
                by = getBy(locatorBy, locatorValue);
            } else {
                throw new RuntimeException("There is no locator for the by object ");
            }
        }
        return  by;
    }

    @Override
    public String toString() {
        return "TestStep{" +
                "step=" + step +
                ", description='" + description + '\'' +
                ", keyword='" + keyword + '\'' +
                ", locator='" + locator + '\'' +
                ", data='" + data + '\'' +
                '}';
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

    public static Collection<TestStep> load(String excelFile, String tabName){
        Xcelite xcelite = new Xcelite(new File(excelFile));
        XceliteSheet sheet = xcelite.getSheet(tabName);
        SheetReader<TestStep> reader = sheet.getBeanReader(TestStep.class);
        Collection<TestStep> testSteps = reader.read();
        return testSteps;
    }

}
