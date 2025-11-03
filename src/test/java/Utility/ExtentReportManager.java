package Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static ExtentReports getExtent() {
		File reportDir = new File("reports");
		if (!reportDir.exists())
			reportDir.mkdir();
		if (extent == null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String timestamp = LocalDateTime.now().format(dtf);
			String reportPath = "reports/ExtentReport_" + timestamp + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			try {
				String customRAcss = readCSSFile("src/main/resources/CSS.css");
				reporter.config().setCss(customRAcss);
			} catch (IOException e) {
				e.printStackTrace();
			}
			reporter.config().setReportName("Regression Report");
			reporter.config().setDocumentTitle("Automation Test Results");
		
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		return extent;
	}

	public static ExtentTest createTest(String name) {
		ExtentTest extentTest = getExtent().createTest(name);
		test.set(extentTest);
		return extentTest;
	}
	
	private static String readCSSFile(String filePath) throws IOException {
		java.nio.file.Path path = Paths.get(filePath);
		return Files.readString(path);
	}


	public static ExtentTest getTest() {
		return test.get();
	}

	public static void flush() {
		getExtent().flush();
	}
}