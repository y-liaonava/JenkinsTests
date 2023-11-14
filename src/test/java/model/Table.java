package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Table extends BaseWebElement {


    public Table(WebElement element) {
        super(element);
    }

    public static List<String> sortStringArray(List<String> arr) {
        Collections.sort(arr, Collections.reverseOrder());
        return arr;
    }

    public boolean ifColumnHasValue(List<WebElement> values, String value) {

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getText().equals(value)) {
                return true;
            }
        }

        return false;

    }


    public int getColumnIndexByName(String columnName, List<String> headers) {
        int columnIndex = 0;
        int columnCounter = 0;
        for (int i = 0; i < headers.size(); i++) {
            columnCounter = columnCounter + 1;
            if (headers.get(i).equals(columnName)) {
                columnIndex = columnCounter;
            }
        }
        return columnIndex;
    }
}

