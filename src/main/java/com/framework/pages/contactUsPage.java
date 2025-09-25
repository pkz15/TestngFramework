package com.framework.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.framework.base.ConfigReader;

public class contactUsPage {

    private WebDriver driver;
    protected ConfigReader config;
    private By contactUs = By.xpath("(//a[normalize-space()='Contact Us'])[1]");
    private By dropUs = By.xpath("//button[text()='Drop us a Word!']");
    private By Name = By.xpath("//input[@data-aid='CONTACT_FORM_NAME']");
    private By Email = By.xpath("//input[@data-aid='CONTACT_FORM_EMAIL']");
    private By Phone = By.xpath("//input[@data-aid='CONTACT_FORM_PHONE']");
    private By Message = By.cssSelector("textarea[placeholder='Message']");
    private By send = By.xpath("//button[@type='submit']");
    private By confirmation = By.xpath("//*[@data-aid='CONTACT_FORM_SUBMIT_SUCCESS_MESSAGE']");
    
    public contactUsPage(WebDriver driver) {
        this.driver = driver;
        config = new ConfigReader();
    }

    public void clickContactUs() {
        driver.findElement(contactUs).click();
    }
    
    public String dropUs() throws Throwable
    {
    	driver.findElement(dropUs).click();
    	String Names = config.getProperty("name");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    	wait.until(ExpectedConditions.visibilityOfElementLocated(Name));
    	Thread.sleep(3000);
    	driver.findElement(Name).sendKeys(Names);
    	driver.findElement(Email).sendKeys(config.getProperty("email"));
    	driver.findElement(Phone).sendKeys(config.getProperty("phone"));
    	driver.findElement(Message).sendKeys(config.getProperty("message"));
//    	driver.findElement(send).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(send));
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation));
    	return element.getText();
    	
    	
    }
    
    
}
