package com.sanyam.retrylogic;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerC implements IRetryAnalyzer{
	int retry = 3;
	int count = 1;

	public boolean retry(ITestResult result) {
		if(count < retry){
			count++;
			return true;
		}
		
		return false;
	}

}
