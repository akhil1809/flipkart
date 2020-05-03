package com.flipkart.utils;

public class Config {
	public enum  PLATFROM
    {
   	IOS,ANDROID
    }
	
	public enum AUTOMATION_NAME
   {
   	IOS("XCUITest"),
   	ANDROID("uiautomator2");
   	
   	String automationName;
   	String getAutomationName()
   	{
   		return automationName;
   	}
   	AUTOMATION_NAME(String automationName)
   	{
   		this.automationName=automationName;
   	}
   }
  
   public enum APP_CONFIG
   {
   	
   	ANDROID_PLATFORM_NAME("Android"),
   	APP_ACTIVITY("com.flipkart.android.activity.HomeFragmentHolderActivity"),
   	APP_PACKAGE("com.flipkart.android");


 
   	String  appConfig;
   	String getAppConfig()
   	{
   		return appConfig;
   	}
   	
   	APP_CONFIG(String appConfig)
   	{
   		this.appConfig=appConfig;
   	}
   }
   
   
   public enum LocatorStrategy
   {
   	ANDROID_LOCATOR_STRATEGY_TEXT_CONTAINS, ANDROID_LOCATOR_STRATEGY_TEXT, ANDROID_LOCATOR_STRATEGY_ID,ANDROID_LOCATOR_STRATEGY_TEXT_STARTS_WITH,
   	ANDROID_LOCATOR_STRATEGY_XPATH_WITH_TEXT_VIEW,ANDROID_LOCATOR_STRATEGY_XPATH
   }
   
   /****** Locator Strategy Constant ******/
   
    public final static String ANDROID_LOCATOR_STRATEGY_TEXT_CONTAINS = "textContains";
	public final static String ANDROID_LOCATOR_STRATEGY_TEXT = "text";
	public final static String ANDROID_LOCATOR_STRATEGY_ID = "id";

	public enum PRODUCT_DETAIL
	{
		PRODCUT_NAME,PRODUCT_SIZE,PRODUCT_RATE
	}
}



