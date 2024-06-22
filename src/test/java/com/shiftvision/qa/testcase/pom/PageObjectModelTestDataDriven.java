package com.shiftvision.qa.testcase.pom;

import com.shiftvision.spree.framework.pom.PageObjectModelScriptBase;
import com.shiftvision.spree.framework.utils.ExcelReader;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;

public class PageObjectModelTestDataDriven extends PageObjectModelScriptBase {

    //@Test
    @ParameterizedTest
    @CsvSource({
            "Success,test@shiftvision.com,shift123,Logged in successfully",
            "Invalid,invalid@shiftvision.com,shift123,Invalid email or password.",
            "Invalid,test@shiftvision.com,invalid,Invalid email or password.",
            "Validation,@shiftvision.com,shift123,Please enter a part followed by '@'. '@shiftvision.com' is incomplete.",
            "Validation,invalid,shift123,Please include an '@' in the email address. 'invalid' is missing an '@'.",
            "Validation,test,shift123,Please include an '@' in the email address. 'test' is missing an '@'."
    })
    public void validUserValidPasswordTest1(String validationType, String email,
                                           String password, String message) {
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login(email, password);
        spree().waitFor(2000);
        if (validationType.contentEquals("Success")) {
            spree().homePage().verifySuccessMessage(message);
        } else if (validationType.contentEquals("Invalid")) {
            spree().loginPage().verifyErrorMessage(message);
        } else if (validationType.contentEquals("Validation")) {
            spree().loginPage().verifyEmailValidationMessage(message);
        }
    }

    @ParameterizedTest
    @MethodSource("dataProviderArray")
    public void validUserValidPasswordTest2(String validationType, String email, String password, String message){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login(email,password);
        spree().waitFor(2000);
        if(validationType.contentEquals("Success")) {
            spree().homePage().verifySuccessMessage(message);
        } else if(validationType.contentEquals("Invalid")){
            spree().loginPage().verifyErrorMessage(message);
        } else if(validationType.contentEquals("Validation")) {
            spree().loginPage().verifyEmailValidationMessage(message);
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/SVTestData - Sheet1.csv",numLinesToSkip = 1)
    public void validUserValidPasswordTest3(String validationType, String email, String password, String message){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login(email,password);
        spree().waitFor(2000);
        if(validationType.contentEquals("Success")) {
            spree().homePage().verifySuccessMessage(message);
        } else if(validationType.contentEquals("Invalid")){
            spree().loginPage().verifyErrorMessage(message);
        } else if(validationType.contentEquals("Validation")) {
            spree().loginPage().verifyEmailValidationMessage(message);
        }
    }

    @ParameterizedTest
    @MethodSource("dataProviderJExcel")
    public void validUserValidPasswordTest4(String validationType, String email, String password, String message){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login(email,password);
        spree().waitFor(2000);
        if(validationType.contentEquals("Success")) {
            spree().homePage().verifySuccessMessage(message);
        } else if(validationType.contentEquals("Invalid")){
            spree().loginPage().verifyErrorMessage(message);
        } else if(validationType.contentEquals("Validation")) {
            spree().loginPage().verifyEmailValidationMessage(message);
        }
    }

    @ParameterizedTest
    @MethodSource("dataProviderPOIExcel")
    public void validUserValidPasswordTest5(String validationType, String email, String password, String message){
        spree().homePage().navigateToLoginPage();
        spree().loginPage().login(email,password);
        spree().waitFor(2000);
        if(validationType.contentEquals("Success")) {
            spree().homePage().verifySuccessMessage(message);
        } else if(validationType.contentEquals("Invalid")){
            spree().loginPage().verifyErrorMessage(message);
        } else if(validationType.contentEquals("Validation")) {
            spree().loginPage().verifyEmailValidationMessage(message);
        }
    }


    public static Object[][] dataProviderArray(){
        String[][] data =
                {
                        {"Success","test@shiftvision.com","shift123","Logged in successfully"},
                        {"Invalid","invalid@shiftvision.com","shift123","Invalid email or password."},
                        {"Invalid","test@shiftvision.com","invalid","Invalid email or password."},
                        {"Validation","@shiftvision.com","shift123","Please enter a part followed by '@'. '@shiftvision.com' is incomplete."},
                        {"Validation","invalid","shift123","Please include an '@' in the email address. 'invalid' is missing an '@'."},
                        {"Validation","test","shift123","Please include an '@' in the email address. 'test' is missing an '@'."}
                };
        return data;
    }

    public static Object[][] dataProviderJExcel(){
        // https://mkyong.com/java/jexcel-api-reading-and-writing-excel-file-in-java/
        String[][] data = null;
        String EXCEL_FILE_LOCATION = System.getProperty("user.dir") + "/src/test/resources/SVTestData.xls";

        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(new File(EXCEL_FILE_LOCATION));
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            int cols = sheet.getColumns();
            data = new String[rows - 1][cols];
            for(int i = 1; i < rows; i++){
                for(int j = 0; j < cols; j++) {
                    Cell cell = sheet.getCell(j, i);
                    data[i - 1][j] = cell.getContents();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Object[][] dataProviderJExcel2(){
        String[][] data = null;
        String EXCEL_FILE_LOCATION = System.getProperty("user.dir") + "/src/test/resources/SVTestData.xls";
        //https://mkyong.com/java/jexcel-api-reading-and-writing-excel-file-in-java/
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(new File(EXCEL_FILE_LOCATION));
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            int cols = sheet.getColumns();
            data = new String[rows - 1][cols];
            for(int i = 1; i < rows; i++){
                Cell cell1 = sheet.getCell(0, i);
                data[i-1][0] = cell1.getContents();
                Cell cell2 = sheet.getCell(1, i);
                data[i-1][1] = cell2.getContents();
                Cell cell3 = sheet.getCell(2, i);
                data[i-1][2] = cell3.getContents();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Object[][] dataProviderPOIExcel(){
        String[][] data = null;
        String EXCEL_FILE_LOCATION = System.getProperty("user.dir") + "/src/test/resources/SVTestData.xlsx";

        ExcelReader excelReader = new ExcelReader(EXCEL_FILE_LOCATION);
        data = excelReader.getExcelSheetData("Sheet1",true);

        return data;
    }
}
