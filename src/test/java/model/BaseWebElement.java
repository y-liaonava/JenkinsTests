package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public  class BaseWebElement {

    protected WebElement element;
    protected WebDriver driver;

    public BaseWebElement(WebElement element) {
        this.element = element;

    }

    public void hoverOverElement(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .contextClick()
                .build().perform();
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].mouseOver()", element);


    }


    }


