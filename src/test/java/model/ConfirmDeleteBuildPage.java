package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmDeleteBuildPage extends BasePage{

    private UIButton yesButton;
    By yesButtonXpath = By.xpath("//button[text()= 'Yes']");


    public ConfirmDeleteBuildPage(WebDriver driver) {
        super(driver);
        yesButton = new UIButton(getDriver().findElement(yesButtonXpath));

    }

    public StatusPage clickYesButton(){
        yesButton.clickButton();
        return new StatusPage(getDriver());
    }


}
