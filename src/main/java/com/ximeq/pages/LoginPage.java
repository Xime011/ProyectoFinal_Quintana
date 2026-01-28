package com.ximeq.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final String URL = "https://opensource-demo.orangehrmlive.com/";

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By loginForm = By.cssSelector("div.orangehrm-login-container");
    private final By errorMessage = By.cssSelector(".oxd-alert-content-text");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        driver.get(URL);
    }

    public void enterUserName(String user) {
        type(usernameInput, user);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    // Chain
    public void loginAs(String user, String pass) {
        enterUserName(user);
        enterPassword(pass);
        clickLogin();
    }

    public boolean isOnLoginPage() {
        return isElementVisible(loginForm);
    }

    public boolean isErrorDisplayed() {
        return isElementVisible(errorMessage);
    }
}
