package com.retryanalyzer;


import java.io.IOException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


import com.base.TestBase;

public class RetryAnalyzer extends TestBase implements IRetryAnalyzer {
	public RetryAnalyzer() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	private int retryCount = 0;
	private int maxRetryCount = 3;
	
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			log("Retrying test " + result.getName() + " with status " + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
			retryCount++;
			return true;
		}
		return false;
	}
	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}
	
	
}