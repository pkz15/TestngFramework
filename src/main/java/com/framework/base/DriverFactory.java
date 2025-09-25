package com.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver initDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
//                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
//                    WebDriverManager.edgedriver().browserVersion("140.0.3485.81") .setup();
                    driver = new EdgeDriver();
                    break;
                case "firefox":
//                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();;
                    break;
                
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
