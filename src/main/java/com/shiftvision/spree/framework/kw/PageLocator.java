package com.shiftvision.spree.framework.kw;

import com.ebay.xcelite.Xcelite;
import com.ebay.xcelite.annotations.Column;
import com.ebay.xcelite.reader.SheetReader;
import com.ebay.xcelite.sheet.XceliteSheet;

import java.io.File;
import java.util.Collection;

public class PageLocator {
    @Column(name="LocatorId")
    private int id;
    @Column(name="Name")
    private String name;
    @Column(name="Locator")
    private String locator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    @Override
    public String toString() {
        return "PageLocator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locator='" + locator + '\'' +
                '}';
    }

    public static Collection<PageLocator> load(String excelFile, String tabName){
        Xcelite xcelite = new Xcelite(new File(excelFile));
        XceliteSheet sheet = xcelite.getSheet(tabName);
        SheetReader<PageLocator> reader = sheet.getBeanReader(PageLocator.class);
        Collection<PageLocator> pageLocator = reader.read();
        return pageLocator;
    }
}
