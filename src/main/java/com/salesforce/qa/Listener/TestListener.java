package com.salesforce.qa.Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.utilities.CommonUtilities;

public class TestListener extends TestBase implements ITestListener {
	private static ExtentReports extent;

	@Override
	public synchronized void onStart(ITestContext context)
	{
		System.out.println("***********************************\n");
		System.out.println("Test Suite started!\n");
		System.out.println("***********************************\n");

	}
	
	@Override
	public synchronized void onTestStart(ITestResult result)
	{
		System.out.println("***********************************\n");
		System.out.println(result.getMethod().getMethodName()+"------->"+"started!");
		System.out.println("***********************************\n");

		
	}
	
	@Override
	public synchronized void onTestSuccess(ITestResult result)
	{
		System.out.println("*******************************************************\n");
		System.out.println(result.getMethod().getMethodName()+"------->"+"passed!");
		System.out.println("*******************************************************\n");
	}
	
	@Override
	public synchronized void onTestFailure(ITestResult result)
	{
		System.out.println("*******************************************************\n");
		String failtest=result.getMethod().getMethodName();
		System.out.println(failtest+"------->"+"Failed!");
		System.out.println("*******************************************************\n");

	}
	
	@Override
	public synchronized void onTestSkipped(ITestResult result)
	{
		System.out.println("*******************************************************\n");
		System.out.println(result.getMethod().getMethodName()+"------->"+"skipped!");
		System.out.println("*******************************************************\n");
	}
	
	@Override
	public synchronized void onFinish(ITestContext context)
	{
		System.out.println("*******************************************************\n");
		System.out.println("Test Suite ended!");
		extent.flush();
		System.out.println("*******************************************************\n");

	}
	
}
