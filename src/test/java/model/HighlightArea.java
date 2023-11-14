package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighlightArea {

    WebElement element;
    WebDriver driver;

    public HighlightArea(WebElement element) {
        this.element = element;
    }
}
