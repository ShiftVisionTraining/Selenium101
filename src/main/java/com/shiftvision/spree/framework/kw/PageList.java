package com.shiftvision.spree.framework.kw;

import com.ebay.xcelite.Xcelite;
import com.ebay.xcelite.annotations.Column;
import com.ebay.xcelite.reader.SheetReader;
import com.ebay.xcelite.sheet.XceliteSheet;

import java.io.File;
import java.util.Collection;

public class PageList {
    @Column(name="Id")
    private int id;
    @Column(name="PageName")
    private String pageName;
    @Column(name="Description")
    private String description;
    @Column(name="TabName")
    private String tabName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "id=" + id +
                ", pageName='" + pageName + '\'' +
                ", description='" + description + '\'' +
                ", tabName='" + tabName + '\'' +
                '}';
    }

    public static Collection<PageList> load(String excelFile, String tabName){
        Xcelite xcelite = new Xcelite(new File(excelFile));
        XceliteSheet sheet = xcelite.getSheet(tabName);
        SheetReader<PageList> reader = sheet.getBeanReader(PageList.class);
        Collection<PageList> pageList = reader.read();
        return pageList;
    }
}
