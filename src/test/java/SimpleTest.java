import model.ConfigPage;
import model.HomePage;
import model.NewItemPage;
import model.TestUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SimpleTest extends BaseTest {

    @Test
    public void testSimple() {
        HomePage homepage = new HomePage(getDriver());

        Assert.assertEquals(homepage.getAddDescriptionButton().getButtonText(), "Add description");
    }

    @Test
    public void testButtonColor() {
        HomePage homepage = new HomePage(getDriver());


        Assert.assertEquals(TestUtils.convertRGBToHex(homepage.getAddDescriptionButton().getButtonColor()), "#0062d1");


    }


    @Test
    public void testNewItemCreation() {
        HomePage homepage = new HomePage(getDriver());

        homepage.clickNewItemButton();
        NewItemPage newItemPage = new NewItemPage(getDriver());
                newItemPage.inputItemName()
                .clickFreestyleProjectRadioButton()
                .clickOkButton()
                .clickSaveButton();
        String text = getDriver().findElement(By.xpath("//h1[@class = 'job-index-headline page-headline']")).getText();

        Assert.assertEquals(text, "Project "+ TestUtils.getGeneratedString());


    }
}
