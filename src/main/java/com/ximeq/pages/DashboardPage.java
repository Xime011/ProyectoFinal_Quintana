package com.ximeq.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private final By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-name");
    private final By logoutButton = By.xpath("//a[text()='Logout']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    public boolean isOnDashboard() {
        waitForUrlContains("dashboard");
        return isElementVisible(dashboardHeader);
    }

    public String getHeaderText() {
        return getText(dashboardHeader);
    }
    public void goToPIM() {
        click(pimMenu);
    }

    public void logout() {
        click(userDropdown);
        click(logoutButton);
    }
}
