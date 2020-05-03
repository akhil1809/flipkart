package com.flipkart.utils;


import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileFactory {
	
	DesiredCapabilities capabilities;
	public MobileFactory()
	{
		capabilities=new DesiredCapabilities();
	}
	
	public  DesiredCapabilities  setCapbalities(Config.PLATFROM platform, String DEVICE_NAME, String PLATFORM_VERSION)
	{
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,DEVICE_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
		switch(platform)
		{
		  case ANDROID:
			  capabilities.setCapability("appPackage", Config.APP_CONFIG.APP_PACKAGE.getAppConfig());
			  capabilities.setCapability("appActivity", Config.APP_CONFIG.APP_ACTIVITY.getAppConfig());
			  capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Config.APP_CONFIG.ANDROID_PLATFORM_NAME.getAppConfig() );
			  capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, Config.AUTOMATION_NAME.ANDROID.getAutomationName());
			  return capabilities;
			  
		 default:
			 return null;
		}
	}
	
	
	
	

}
