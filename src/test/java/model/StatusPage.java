package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StatusPage extends BasePage{

    private UIButton dasboardButton;
    private By dasboardButtonXpath = By.xpath("//a[text()='Dashboard']");
    public StatusPage(WebDriver driver) {
        super(driver);
        dasboardButton = new UIButton(getDriver().findElement(dasboardButtonXpath));
    }

    public HomePage clickDashboardbutton(){
        dasboardButton.clickButton();
        return new HomePage(getDriver());
    }

}
