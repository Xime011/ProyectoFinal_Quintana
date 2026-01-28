package com.ximeq.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {

        System.out.println("INICIANDO" + scenario.getName());
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
    }
    @After
    public void  tearDown(Scenario scenario){
        if(scenario.isFailed()){
            System.out.println("\nFALLÓ: " + scenario.getName());
        }else{
            System.out.println("PASÓ: " + scenario.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
