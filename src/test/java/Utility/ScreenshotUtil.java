package Utility;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	private WebDriver driver;
	public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
        
    }
	public String captureScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            File finalDest = new File(dest);
            FileUtils.copyFile(source, finalDest);
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
