package com.flipkart.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListnerTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result)
    {
        System.out.println(result.getName()+" test case started");
    }

    public void onTestSuccess(ITestResult result)
    {
        System.out.println(result.getName()+" test case passed");
    }


    public void onTestFailure(ITestResult result)
    {
        System.out.println(result.getName()+" test case failed");
        CommonFactory.getScreenShot(CommonFactory.failedWebTestScreenshotLocation +result.getMethod().getMethodName()+"_"+CommonFactory.getCurrentTimeStamp()+".png");
    }


    public void onTestSkipped(ITestResult result)
    {
        System.out.println(result.getName()+" test case skipped");
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }


}
