package model;

import org.openqa.selenium.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BuildHistoryPage extends BasePage {

    private String LEFT_PATTERN = "left: (-?\\d*)px";
    private UIButton lastBuildContextMenuButton;
    private UIButton lastJobContextMenuButton;
    private UIButton deleteBuildButton;
    private ArrayList<String> lastJobAndBuildNames = new ArrayList<>();
    private Table buildsTable;
    private UIButton deleteProjectButton;


    private By deleteBuildButtonXpath = By.xpath("//span[contains(text(), 'Delete build')]");
    private By buildsTableXpath = By.xpath("//div[@id = 'main-panel']//table[2]");
    private By deleteProjectButtonXpath = By.xpath(("//span[contains(text(), 'Delete Project')]"));


    List<WebElement> buildColumnValues = getDriver().findElements(By.xpath("//a[@class ='jenkins-table__link model-link']"));


    public BuildHistoryPage(WebDriver driver) {
        super(driver);
        buildsTable = new Table(getDriver().findElement(buildsTableXpath));


    }



    public boolean ifTableContainsJobName(String jobName) {
        return buildsTable.ifColumnHasValue(buildColumnValues, jobName);
    }


    public boolean timelineHighlightPositionInTodayBlock() {
        WebElement timeLineHighLight = getDriver().findElement(By.xpath("//div[@name = 'ether-highlight']"));
        String elementStyle = timeLineHighLight.getAttribute("style");
        int elementLeftPosition = Integer.parseInt(TestUtils.findSomeSequenceInString(elementStyle, LEFT_PATTERN));
        List<WebElement> dateBlocks = getDriver().findElements(By.xpath("//div[@id= 'timeline-band-0']//div[@name='ether-markers']/div"));

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
        String formattedCurrentDate = formatter.format(currentDate);

        for (int i = 0; i < dateBlocks.size(); i++) {
            if (elementLeftPosition > Integer.parseInt(TestUtils.findSomeSequenceInString(dateBlocks.get(i).getAttribute("style"), LEFT_PATTERN))
                    && elementLeftPosition < Integer.parseInt(TestUtils.findSomeSequenceInString(dateBlocks.get(i + 1).getAttribute("style"), LEFT_PATTERN))) {
                String date = dateBlocks.get(i).getText();
                if (date.equals(formattedCurrentDate)) {
                    return true;
                }
            }
        }
        return false;
    }


    public List<String> getTableHeaders() {
        List<WebElement> tableHeaders = getDriver().findElements(By.xpath("//table[@id = 'projectStatus']/thead//a"));
        List<String> columnNames = new ArrayList<>();
        for (WebElement element : tableHeaders) {
            columnNames.add(element.getText());
        }
        return columnNames;
    }

    public int findColumnIndexByName(String name){
        return buildsTable.getColumnIndexByName(name, getTableHeaders());
    }


    public BuildHistoryPage clickLastBuildContextMenuButton() {
        lastBuildContextMenuButton = new UIButton(getDriver().findElement(By.xpath("//table[@id = 'projectStatus']//tbody//tr[1]//a[2]//button")));
        buildsTable.hoverOverElement(getDriver().findElement(By.xpath("//table[@id = 'projectStatus']//tbody//tr[1]//a[2]")), getDriver());
        lastBuildContextMenuButton.clickButton();
        return new BuildHistoryPage(getDriver());

    }

    public BuildHistoryPage clickLastJobContextMenuButton(){
        lastJobContextMenuButton = new UIButton(getDriver().findElement(By.xpath("//table[@id = 'projectStatus']//tbody//tr[1]//a[1]//button")));
        buildsTable.hoverOverElement(getDriver().findElement(By.xpath("//table[@id = 'projectStatus']//tbody//tr[1]//a[1]")), getDriver());
        lastJobContextMenuButton.clickButton();
        return new BuildHistoryPage(getDriver());
    }

    public ConfirmDeleteBuildPage clickDeleteBuildButton() {
        deleteBuildButton = new UIButton(getDriver().findElement(deleteBuildButtonXpath));
        deleteBuildButton.clickButton();
        return new ConfirmDeleteBuildPage(getDriver());
    }


    public HomePage clickDeleteProjectButton() {
        deleteProjectButton = new UIButton(getDriver().findElement(deleteProjectButtonXpath));
        deleteProjectButton.clickButton();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
        return new HomePage(getDriver());
    }


    public List<WebElement> getLastJobAndBuild() {
        try {
            List<WebElement> lastJobAndBuild = new ArrayList<WebElement>();
            lastJobAndBuild.add(getDriver().findElement(By.xpath("//table[@id = 'projectStatus']//tbody//tr[1]//a[1]")));
            lastJobAndBuild.add(getDriver().findElement(By.xpath("//table[@id = 'projectStatus']//tbody//tr[1]//a[2]")));
            return lastJobAndBuild;
        } catch (Exception e) {
            throw new NoSuchElementException("No builds in the table");
        }
    }


    public List <String> getLastJobAndBuildNames() {
        for (int i = 0; i < getLastJobAndBuild().size(); i++) {
            lastJobAndBuildNames.add(getLastJobAndBuild().get(i).getText());
        }
        return lastJobAndBuildNames;
    }


    public boolean findJobAndBuildByNames(ArrayList ) {
        List<WebElement> buildColumnValues = new ArrayList<>();
        try {
            buildColumnValues = getDriver().findElements(By.xpath("//table[@id = 'projectStatus']//tbody//td[2]"));
            boolean jobAndBuildNameIsDisplayed = false;
            for (int i = 0; i < buildColumnValues.size(); i++) {
                if (buildColumnValues.get(i).findElement(By.xpath("/a[1]")).getText().equals(getLastJobAndBuildNames().get(0)) &&
                        buildColumnValues.get(i).findElement(By.xpath("/a[2]")).getText().equals(getLastJobAndBuildNames().get(1))) {
                    jobAndBuildNameIsDisplayed = true;
                }
            }
            if (jobAndBuildNameIsDisplayed) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}







