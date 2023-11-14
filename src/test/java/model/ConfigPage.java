package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfigPage  extends BasePage{



    private UIButton saveButton;
    By saveButtonXpath = By.xpath("//button[text()='Save']");
    public ConfigPage(WebDriver driver) {
        super(driver);
        saveButton = new UIButton(getDriver().findElement(saveButtonXpath));
    }

    public void clickSaveButton(){
        saveButton.clickButton();

    }


}
