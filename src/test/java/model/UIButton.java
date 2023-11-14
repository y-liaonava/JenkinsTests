package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;


public class UIButton  extends BaseWebElement {



    public UIButton(WebElement element) {
        super(element);

    }

    public String getButtonColor() {
        return element.getCssValue("color");

    }


    public String getButtonText() {
        return element.getText();

    }



    public void clickButton() {
        element.click();

    }



}
