package com.shiftvision.spree.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HtmlTableUtils {
    protected WebDriver driver = null;
    private JavascriptExecutorUtils js = null;
    private boolean enableHighlight = false;
    public HtmlTableUtils(WebDriver driver){
        this.driver = driver;
        js = new JavascriptExecutorUtils(this.driver);
    }

    public boolean isEnableHighlight() {
        return enableHighlight;
    }

    public void setEnableHighlight(boolean enableHighlight) {
        this.enableHighlight = enableHighlight;
    }


    public List<WebElement> getAllTableRows(WebElement table){
        WebElement tBody = table.findElement(By.tagName(("tbody")));
        if(enableHighlight) {
            js.highlight(tBody);
        }
        List<WebElement> trs = tBody.findElements(By.tagName("tr"));
        return trs;
    }
    public List<WebElement> getAllTableRowCells(WebElement row){
        if(enableHighlight) {
            js.highlight(row);
        }
        List<WebElement> tds = row.findElements(By.xpath("./th | ./td"));
        return tds;
    }

    public WebElement getTableCell(WebElement table, int row, int coll){
        List<WebElement> rows = getAllTableRows(table);
        List<WebElement> Cells = getAllTableRowCells(rows.get(row));
        WebElement cell = Cells.get(coll);
        if(enableHighlight) {
            js.highlight(cell);
        }
        return cell;
    }

    public List<List<String>> getTableData(WebElement table){
        List<List<String>> tablesData = new ArrayList<>();

        List<WebElement> rows = getAllTableRows(table);
        for(WebElement row : rows){
            List<String> tableRow = new ArrayList<>();
            tablesData.add(tableRow);
            List<WebElement> cells = getAllTableRowCells(row);
            for(WebElement cell : cells){
                if(enableHighlight) {
                    js.highlight(cell);
                }
                String cellText = cell.getText();
                tableRow.add(cellText);
            }
        }
        return tablesData;
    }
}
