package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends BasePage {
    private UIButton addDescriptionButton;
    private UIButton newItemButton;
    private UIButton sortNameButton;
    private UIButton buildNowButton;
    private UIButton buildHistoryButton;
    private List<WebElement> projects;
    private Table projectsTable;


    private By addDescriptionButtonXPath = By.id("description-link");
    private By newItemButtonXPath = By.xpath("//a[contains(@href, 'view/all/newJob')]");
    private By sortNameButtonXpath = By.xpath("//a[text()='Name']");
    private By projectNamesXpath = By.xpath("//a [@class = 'jenkins-table__link model-link inside']");
    private By buildNowButtonXpath = By.xpath("//span[text()='Build Now']");
    private By buildHistoryButtonXpath = By.xpath("//a[@href = '/view/all/builds']");
    private List<WebElement> nameColumnValues = getDriver().findElements(By.xpath("//table[@id = 'projectstatus']//a[@class = 'jenkins-table__link model-link inside']/span"));
    private By projectsTableXpath = (By.xpath("//table[@id = 'projectstatus']"));


    public HomePage(WebDriver driver) {
        super(driver);
        addDescriptionButton = new UIButton(getDriver().findElement(addDescriptionButtonXPath));
        newItemButton = new UIButton(getDriver().findElement(newItemButtonXPath));
        buildHistoryButton = new UIButton(getDriver().findElement(buildHistoryButtonXpath));
    }
    public UIButton getAddDescriptionButton() {
        return addDescriptionButton;
    }

    public void clickNewItemButton() {
        newItemButton.clickButton();
    }

    public HomePage clickSortButton() {
        sortNameButton = new UIButton(getDriver().findElement(sortNameButtonXpath));
        sortNameButton.clickButton();
        return new HomePage(getDriver());
    }

    public List<WebElement> getProjects() {
        projects = getDriver().findElements(projectNamesXpath);
        return projects;
    }

    public ArrayList<String> getProjectNames() {
        projects = getDriver().findElements(projectNamesXpath);
        ArrayList<String> projectNames = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            projectNames.add(projects.get(i).getText());
        }
        return projectNames;
    }

    public HomePage clickContextMenuButtonForCreatedProject() {
        By contextMenuButtonXpath = By.xpath("//table[@id = 'projectstatus']/tbody//tr[" +
                findCreatedProjectPosition(TestUtils.getGeneratedString()) + "]//a[@class = 'jenkins-table__link model-link inside']/button");
        UIButton contextMenuButton = new UIButton(getDriver().findElement((contextMenuButtonXpath)));
        findCreatedProject(TestUtils.getGeneratedString()).hoverOverElement(getDriver().findElement(contextMenuButtonXpath), getDriver());
        contextMenuButton.clickButton();
        return new HomePage(getDriver());
    }


    public HomePage clickBuildNowButton() {
        buildNowButton = new UIButton(getDriver().findElement(buildNowButtonXpath));
        buildNowButton.clickButton();
        return new HomePage(getDriver());

    }

    public BuildHistoryPage clickBuildHistoryButton() {
        buildHistoryButton.clickButton();
        return new BuildHistoryPage(getDriver());

    }

    public boolean tableContainsProjectName(String projectName) {
        projectsTable = new Table(getDriver().findElement(projectsTableXpath));
        return projectsTable.ifColumnHasValue(getProjects(), projectName);

    }

    public BaseWebElement findCreatedProject(String createdString) {
        return new BaseWebElement(getDriver().findElement(By.xpath(
                "//table[@id = 'projectstatus']/tbody//tr[" + findCreatedProjectPosition(createdString) + "]//a[@class = 'jenkins-table__link model-link inside']")));

    }

    public int findCreatedProjectPosition(String createdString) {
        int rowNumber = 0;
        int counter = 0;
        for (int i = 0; i < nameColumnValues.size(); i++) {
            counter = counter + 1;
            if (nameColumnValues.get(i).getText().equals(createdString)) {
                rowNumber = counter;
            }
        }
        return rowNumber;
    }

}



