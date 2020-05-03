package com.flipkart.utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Factory {
	
	private AppiumDriverLocalService service;
	public MobileFactory mobileFactory;
	public  String platFormName;
	public  String browserName;
	public  String deviceName;
	public  String platformversion;
	public static WebDriver driver;


	
	@BeforeTest(groups= {"flipkartSearch"})
	@Parameters({"platFormName","userName","password","enviornementName","platformversion","deviceName"})
    public void setup(String platFormName,@Optional("null")String userName,@Optional("null")String password,@Optional("null")String enviornementName,String platformversion,String deviceName )
    {
        mobileFactory=new MobileFactory();
        this.platFormName=platFormName;
        this.deviceName=deviceName;
        this.platformversion=platformversion;
        service=AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingAnyFreePort());
		service.start();
		//mobileFactory.setCapbalities(Config.PLATFROM.valueOf(platFormName),Factory.deviceName,Factory.platformversion);	

		if(Config.PLATFROM.ANDROID.toString().equals(this.platFormName))
		{
			driver= new AndroidDriver<MobileElement>(service.getUrl(), mobileFactory.setCapbalities(Config.PLATFROM.valueOf(platFormName),this.deviceName,this.platformversion));
			DriverManager.setDriver(driver);
		}
		
    }

	@AfterTest(groups= {"flipkartSearch"})
	public void endSetup()
    {
	    service.stop();
	//	webDriver.close();
		//webDriver.quit();

    }

}
