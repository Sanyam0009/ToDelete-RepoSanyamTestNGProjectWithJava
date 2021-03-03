package com.sanyam.frameWorkPackage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IResultMap;
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
import com.sanyam.SeleniumProject.TestDemo;

public class TestListners implements ITestListener {
	public WebDriver driver = null;
	private static ExtentReports extents = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extents
				.createTest(result.getTestClass().getName() + "  ::  " + result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String logText = "<b> Test Method - " + result.getMethod().getMethodName() + " - Successful</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, markupLogText);

	}

	// Customize Pass Report Function
	public static void customOnTestSuccess(WebDriver driver, String expectedMsg, String ActualMsg, String scName) {
		String logText = "<b>" + "Expected - " + expectedMsg + "<br>" + "Actual - " + ActualMsg + "</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, markupLogText);
		if (scName != "") {
			String path1 = ExtentManager.getScreenshot(driver, scName);
			try {
				extentTest.get().pass("<b><font color=red> ScreenShot of above Failure </font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
			} catch (Exception e) {
				extentTest.get().fail("Tets Failed , Can not attach screenshot");
			}
		}
	}

	// Customize Failed Report Function
	public static void customOnTestFailure(WebDriver driver, String expectedMsg, String ActualMsg, String scName) {
		// ExtentTest test = extents.createTest("Failed Method TEst");
		// extentTest.set(test);
		String logText = "<b>" + "Expected - " + expectedMsg + "<br>" + "Actual - " + ActualMsg + "</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, markupLogText);
		if (scName != "") {
			String path1 = ExtentManager.getScreenshot(driver, scName);
			try {
				extentTest.get().fail("<b><font color=red> ScreenShot of above Failure </font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
			} catch (Exception e) {
				extentTest.get().fail("Tets Failed , Can not attach screenshot");
			}
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red> Excetion occered, Click to see details"
				+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
		WebDriver driver = ((TestDemo) result.getInstance()).driver;
		String path = ExtentManager.getScreenshot(driver, methodName);
		String description = result.getThrowable().getMessage();
		String logText = "<b> Test Method  - " + methodName + " --- " + description + " - Failed</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, markupLogText);
		try {
			extentTest.get().fail("<b><font color=red> ScreenShot of Failure </font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception e) {
			extentTest.get().fail("Tets Failed , Can not attach screenshot");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String logText = "<b> Test Method  - " + result.getMethod().getMethodName() + " - Skipped</b>";
		Markup markupLogText = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, markupLogText);
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
		if (extents != null) {
			extents.flush();
		}

	}

}