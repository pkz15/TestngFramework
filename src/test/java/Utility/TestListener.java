package Utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.framework.base.BaseTest;
import com.framework.base.DriverSetup;

public class TestListener implements ITestListener {

	private Map<String, Long> startTimes = new HashMap<>();

    // Capture screenshot
    private String captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String dir = System.getProperty("user.dir") + "/screenshots";
            File folder = new File(dir);
            if (!folder.exists()) folder.mkdir();

            String dest = dir + "/" + testName + "_" + System.currentTimeMillis() + ".png";
            File finalDest = new File(dest);
            FileUtils.copyFile(source, finalDest);
            return dest;
        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        startTimes.put(methodName, System.currentTimeMillis());

        ExtentReportManager.createTest(methodName)
                .log(Status.INFO, "Test Started: " + methodName);
        ExtentReportManager.getTest().log(Status.INFO, "Current running method: " + methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        long duration = System.currentTimeMillis() - startTimes.get(methodName);

        WebDriver driver = DriverSetup.getDriver();
        String screenshotPath = captureScreenshot(driver, methodName);

        ExtentReportManager.getTest().log(Status.PASS, "Test Passed");
        ExtentReportManager.getTest().log(Status.INFO, "Duration: " + duration + " ms");
        ExtentReportManager.getTest().log(Status.INFO, "Moving to next method...");
        BaseTest base = (BaseTest) result.getInstance();
        
        
        
        
        if (screenshotPath != null&&!base.isApitest) {
            try {
                ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                ExtentReportManager.getTest().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
            }
        }
        else
        {
        	 ExtentReportManager.getTest().log(Status.INFO, "No Screenshot for API Validation..");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        long duration = System.currentTimeMillis() - startTimes.get(methodName);

        WebDriver driver = DriverSetup.getDriver();
        String screenshotPath = captureScreenshot(driver, methodName);

        ExtentReportManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        ExtentReportManager.getTest().log(Status.INFO, "Duration: " + duration + " ms");
        ExtentReportManager.getTest().log(Status.INFO, "Moving to next Test method...");

        if (screenshotPath != null) {
            try {
                ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                ExtentReportManager.getTest().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flush();
    }
}