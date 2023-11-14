import model.HomePage;
import model.Table;
import model.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SortingTest  extends BaseTest {
    @Test
    public void dashboardNameColumnSorting (){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickSortButton();

        Assert.assertEquals(homePage.getProjectNames(), Table.sortStringArray(homePage.getProjectNames()));


    }
}
