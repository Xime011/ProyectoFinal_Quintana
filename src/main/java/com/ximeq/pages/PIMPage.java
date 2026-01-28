package com.ximeq.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BasePage {

    private final By pimHeader = By.xpath("//h6[text()='PIM']");
    private final By employeeNameInput = By.xpath("//input[contains(@placeholder,'Type for hints')]");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By resultRows = By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By noRecordsLabel = By.xpath("//span[text()='No Records Found']");
    private final By loadingSpinner = By.cssSelector(".oxd-loading-spinner");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnPIMPage() {
        return isElementVisible(pimHeader);
    }

    public void searchEmployeeByName(String name) {
        type(employeeNameInput, name);
        click(searchButton);
        waitForElementToDisappear(loadingSpinner);
    }

    public boolean hasResults() {
        return driver.findElements(resultRows).size() > 0;
    }

    public boolean isNoRecordsDisplayed() {
        return isElementVisible(noRecordsLabel);
    }
}
