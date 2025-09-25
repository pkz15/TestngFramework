package com.framework.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader config;
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        config = new ConfigReader(); 
//        String browser=config.getProperty("browser");
        driver = DriverSetup.initDriver(browser); 
        driver.get(config.getProperty("url")); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSetup.quitDriver(); 
    }
}
