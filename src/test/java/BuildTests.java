import com.beust.ah.A;
import model.BuildHistoryPage;
import model.HomePage;
import model.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.NoSuchElementException;

public class BuildTests extends BaseTest {

    @Test(dependsOnMethods = {"SimpleTest.testNewItemCreation"})
    public void startBuildTest() throws InterruptedException {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickContextMenuButtonForCreatedProject()
                .clickBuildNowButton()
                .clickBuildHistoryButton();

        Assert.assertEquals(new BuildHistoryPage(getDriver()).ifTableContainsJobName(TestUtils.getGeneratedString()), true, "seem there is no such job in the table");

    }

    @Test(dependsOnMethods = {"SimpleTest.testNewItemCreation"})
    public void buildWasTodayTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickContextMenuButtonForCreatedProject()
                .clickBuildNowButton()
                .clickBuildHistoryButton();

        Assert.assertEquals(new BuildHistoryPage(getDriver()).timelineHighlightPositionInTodayBlock(), true, "seems the is no build today");

    }


    @Test
    public void deleteBuildTest() {
        getDriver().navigate().to("http://localhost:8080/view/all/builds");
        BuildHistoryPage buildHistoryPage = new BuildHistoryPage(getDriver());

        buildHistoryPage.getLastJobAndBuildNames();
        buildHistoryPage.clickLastBuildContextMenuButton()
                .clickDeleteBuildButton()
                .clickYesButton()
                .clickDashboardbutton()
                .clickBuildHistoryButton();

        Assert.assertEquals(buildHistoryPage.findJobAndBuildByNames(), false);
    }

    @Test

    public void deleteProjectTest() {
        getDriver().navigate().to("http://localhost:8080/view/all/builds");
        BuildHistoryPage buildHistoryPage = new BuildHistoryPage(getDriver());
        buildHistoryPage.getLastJobAndBuildNames();
        buildHistoryPage.clickLastJobContextMenuButton()
        .clickDeleteProjectButton();
        //.clickBuildHistoryButton();

        Assert.assertEquals(new HomePage(getDriver()).tableContainsProjectName(buildHistoryPage.getLastJobAndBuildNames().get(0)), false);


    }


}
