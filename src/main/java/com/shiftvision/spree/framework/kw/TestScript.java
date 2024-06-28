package com.shiftvision.spree.framework.kw;

import com.ebay.xcelite.Xcelite;
import com.ebay.xcelite.annotations.Column;
import com.ebay.xcelite.reader.SheetReader;
import com.ebay.xcelite.sheet.XceliteSheet;

import java.io.File;
import java.util.Collection;

public class TestScript {
    @Column(name="Id")
    private int Id;
    @Column(name="TestScriptDescription")
    private String description;
    @Column(name="TabName")
    private String tabName;
    @Column(name="Run")
    private String run;

    public int getId() {
        return Id;
    }

    public String getDescription() {
        return description;
    }

    public String getTabName() {
        return tabName;
    }

    public String getRun() {
        return run;
    }

    @Override
    public String toString() {
        return "TestScript{" +
                "Id=" + Id +
                ", description='" + description + '\'' +
                ", tabName='" + tabName + '\'' +
                ", run='" + run + '\'' +
                '}';
    }

    public static Collection<TestScript> load(String excelFile, String tabName){
        Xcelite xcelite = new Xcelite(new File(excelFile));
        XceliteSheet sheet = xcelite.getSheet(tabName);
        SheetReader<TestScript> reader = sheet.getBeanReader(TestScript.class);
        Collection<TestScript> testScripts = reader.read();
        return testScripts;
    }


}
