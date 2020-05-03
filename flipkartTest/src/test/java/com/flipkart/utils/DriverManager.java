package com.flipkart.utils;


import com.flipkart.pages.SearchPage;
import com.flipkart.pages.SignInPage;
import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	private static SignInPage signIn;
	private static SearchPage search;
	private static GestureFactory gesture;
	
	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver driverObject) {
		driver.set(driverObject);
	}
	
	
	public static SignInPage getSignInPage()
	{
		if(signIn==null)
			signIn=new SignInPage();
		return signIn;
	
	}
	
	public static SearchPage getSearchPage()
	{
		if(search==null)
			search=new SearchPage();
		return search;
	
	}

	public static GestureFactory getGesture()
	{
		if(gesture==null)
			gesture=new GestureFactory();
		return gesture;
	}
	
}
