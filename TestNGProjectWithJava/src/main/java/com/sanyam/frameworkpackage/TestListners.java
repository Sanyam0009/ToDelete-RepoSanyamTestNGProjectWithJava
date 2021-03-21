package com.sanyam.frameworkpackage;

import java.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class TestListners extends BrowserFactory implements ITestListener {
	
	private static ExtentReports extentReports = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTestTL = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extentReports
				.createTest(result.getTestClass().getName() + "  ::  " + result.getMethod().getMethodName());
		extentTestTL.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String logText = "<b> Test Method - " + result.getMethod().getMethodName() + " - Successful</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTestTL.get().log(Status.PASS, markupLogText);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTestTL.get().fail("<details><summary><b><font color=red> Excetion occered, Click to see details"
				+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
		//WebDriver driver = ((LoginPageTest) result.getInstance()).driver;
		String path = ExtentManager.getScreenshot(driver, methodName);
		String description = result.getThrowable().getMessage();
		String logText = "<b> Test Method  - " + methodName + " --- " + description + " - Failed</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTestTL.get().log(Status.FAIL, markupLogText);
		try {
			extentTestTL.get().fail("<b><font color=red> ScreenShot of Failure </font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception e) {
			extentTestTL.get().fail("Tets Failed , Can not attach screenshot");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String logText = "<b> Test Method  - " + result.getMethod().getMethodName() + " - Skipped</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTestTL.get().log(Status.SKIP, markupLogText);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		if (extentReports != null) {
			extentReports.flush();
		}

	}
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ CUstome Methods $$$$$$$$$$$$$$$$$$$$$$$SSSS
	// Customize Pass Report Function
	public static void customOnTestSuccess(WebDriver driver, String expectedMsg, String ActualMsg, String scName) {
		String logText = "<b>" + "Expected - " + expectedMsg + "<br>" + "Actual - " + ActualMsg + "</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		
		extentTestTL.get().log(Status.PASS, markupLogText);
		if (scName != "") {
			String path1 = ExtentManager.getScreenshot(driver, scName);
			try {
				extentTestTL.get().pass("<b><font color=red> ScreenShot of above Failure </font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
			} catch (Exception e) {
				extentTestTL.get().fail("Tets Failed , Can not attach screenshot");
			}
		}
	}

	// Customize Failed Report Function
	public static void customOnTestFailure(WebDriver driver, String expectedMsg, String ActualMsg, String scName) {
		// ExtentTest test = extents.createTest("Failed Method TEst");
		// extentTest.set(test);
		String logText = "<b>" + "Expected - " + expectedMsg + "<br>" + "Actual - " + ActualMsg + "</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTestTL.get().log(Status.FAIL, markupLogText);
		if (scName != "") {
			String path1 = ExtentManager.getScreenshot(driver, scName);
			try {
				extentTestTL.get().fail("<b><font color=red> ScreenShot of above Failure </font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
			} catch (Exception e) {
				extentTestTL.get().fail("Tets Failed , Can not attach screenshot");
			}
		}

	}


}