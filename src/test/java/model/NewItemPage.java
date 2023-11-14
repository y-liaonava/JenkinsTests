package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class NewItemPage extends BasePage {

    private WebElement itemNameInput;

    private UIButton freestyleProjectRadioButton;
    private UIButton okButton;
    private By freestyleProjectRadioButtonXpath = By.xpath("//span[text()='Freestyle project']");
    private By itemNameInputXpath = By.id("name");
    private By oKButtonXpath = By.xpath("//span[@class = 'yui-button primary large-button']");

    public WebElement getItemNameInput() {
        return itemNameInput;
    }

    public UIButton getFreestyleProjectRadioButton() {
        return freestyleProjectRadioButton;
    }

    public UIButton getOkButton() {
        return okButton;
    }



    public NewItemPage(WebDriver driver) {
        super(driver);
        freestyleProjectRadioButton = new UIButton(getDriver().findElement(freestyleProjectRadioButtonXpath));
        okButton = new UIButton(getDriver().findElement(oKButtonXpath));
    }


    public NewItemPage inputItemName() {
        String generatedString = TestUtils.getRandomString();
        getDriver().findElement(itemNameInputXpath).sendKeys(generatedString);
        return new NewItemPage(getDriver());
    }

    public NewItemPage clickFreestyleProjectRadioButton() {
        freestyleProjectRadioButton.clickButton();
        return new NewItemPage(getDriver());
    }

    public  ConfigPage clickOkButton(){
        okButton.clickButton();
        return new ConfigPage(getDriver());
    }
}


