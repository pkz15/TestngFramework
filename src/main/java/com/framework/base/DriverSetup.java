package com.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {
        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                	driver.set(new ChromeDriver());
                    break;
                case "edge":
                	driver.set(new EdgeDriver());
                    break;
                case "firefox":
                	driver.set( new FirefoxDriver());
                    break;
                 default:
                        throw new IllegalArgumentException("Browser not supported: " + browser);
            }
            driver.get().manage().window().maximize();
        }
        return  driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
        	driver.get().quit();
        	driver.remove();
        }
    }
}
