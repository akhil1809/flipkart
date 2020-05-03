package com.flipkart.utils;


import com.flipkart.utils.Config.LocatorStrategy;
import com.google.common.base.Function;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CommonFactory {
	
	
	
	private static Wait<WebDriver> fluentWait;
	private static WebElement fluentWaitElement;
	final private static int TIMEOUT_IN_SECONDS = 5;
	final private static int POLLING_TIME_IN_SECONDS = 1;
	public static String failedWebTestScreenshotLocation = "./screenshot/failed_Test";
	
	public static void initliseElement(Object object)
	{
		PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver(),Duration.ofSeconds(60)), object);	
	}
  
	//*****************  Display Element Fnction Start Here *********************//

	public static boolean isDisplayed(int timeoutInSeconds, int pollingTimeInSeconds, WebElement element)
	{
		try
		{
			if(timeoutInSeconds == 0)
				timeoutInSeconds = TIMEOUT_IN_SECONDS;
			if(pollingTimeInSeconds == 0)
				pollingTimeInSeconds = POLLING_TIME_IN_SECONDS;
			
			fluentWait = new FluentWait<WebDriver>(DriverManager.getDriver())
					.pollingEvery(Duration.ofSeconds(pollingTimeInSeconds))
					.withTimeout(Duration.ofSeconds(timeoutInSeconds))
					     .ignoring(NoSuchElementException.class, Error.class);
		
		
			fluentWaitElement = element;
			return fluentWait.until ( new Function<WebDriver, Boolean>() 
			{
				public Boolean apply(WebDriver driver)
				{
					return fluentWaitElement.isDisplayed();
				}
			}
					);
	    }
		
		catch(Throwable e)
		{
			e.printStackTrace();
			return false;
		}
  
	}
	
	public static  boolean isDisplayed(WebElement element)
    {
        try
        {
                WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 10);
                wait.until(ExpectedConditions.visibilityOf(element));
                return  true;
        }
        catch(Throwable e)
        {
            e.printStackTrace();
            return  false;
        }

    }
	
	/*****Enter Text ***********/

	public static void clickElement(WebElement element)
    {
       try
       {
           if(isDisplayed(element))
           {
               WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 30);
               wait.until(ExpectedConditions.elementToBeClickable(element));
               element.click();
           }
       }

       catch (NoSuchElementException e)
       {
           e.printStackTrace();

       }
    }
	
 /********Enter Text*********/
    
    public static void  enterText(String attributesName,WebElement element)
    {
    	try
    	{
    		if(isDisplayed(element))
            {
                WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 30);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.sendKeys(attributesName);
            }
    		 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    
  //******* Single Method to Find a element and Elements *****//
	  
  	 public static  WebElement getElement(LocatorStrategy andoridLocator, String andoridAttributeValue)
  	  {
  		 try
  		 {
  			 
  		
  		 WebElement returnElement = null;
  		  switch(andoridLocator)
  		  {
  		  	case ANDROID_LOCATOR_STRATEGY_ID:
  		  		returnElement=DriverManager.getDriver().findElement(MobileBy.id(andoridAttributeValue));
  		  		break;
  		  	case ANDROID_LOCATOR_STRATEGY_TEXT_CONTAINS:
  				returnElement = DriverManager.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\""+andoridAttributeValue+"\")"));
  				break;
  			case ANDROID_LOCATOR_STRATEGY_TEXT_STARTS_WITH:
  				returnElement = DriverManager.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiSelector().textStartsWith(\""+andoridAttributeValue+"\")"));
  				break;
  			case ANDROID_LOCATOR_STRATEGY_TEXT:
  				returnElement = DriverManager.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+andoridAttributeValue+"\")"));
  				break;
  			case ANDROID_LOCATOR_STRATEGY_XPATH_WITH_TEXT_VIEW:
  				returnElement=DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[contains(@text,'"+andoridAttributeValue+"')]"));
  				break;
			  case ANDROID_LOCATOR_STRATEGY_XPATH:
				  returnElement=DriverManager.getDriver().findElement((By.xpath(andoridAttributeValue)));
  		  		default:			
  		  }
  		  return returnElement;
  		 }
  		  catch(Throwable e)
  		 {
  			  e.printStackTrace(); 
  			  return null;
  			  
  		 }
  		 
  	  }
  	  
  	  public static List<WebElement> getElements(LocatorStrategy androidLocatorStrategy, String androidAttributeValue)
  		{
  			try
  			{
  				List<WebElement> returnElement = null;
  				switch(androidLocatorStrategy)
  					{
  						case ANDROID_LOCATOR_STRATEGY_TEXT_CONTAINS:
  							returnElement = DriverManager.getDriver().findElements(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\""+androidAttributeValue+"\")"));
  							break;
  						case ANDROID_LOCATOR_STRATEGY_TEXT:
  							returnElement = DriverManager.getDriver().findElements(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+androidAttributeValue+"\")"));
  							break;
  						case ANDROID_LOCATOR_STRATEGY_ID:
  							returnElement = DriverManager.getDriver().findElements(MobileBy.id(androidAttributeValue));
  							break;
  						default:
  					}
  			return returnElement;
  			}
  			catch(Throwable e)
  			{
  				e.printStackTrace();
  			}
  			return null;
  		}


		public void pause(int second)
		{
			try
			{
				Thread.sleep(1000 * second);
			}
			catch(Exception e){ }
		}

	/*********** Get Screen Shot ************/
	public static void getScreenShot(String failedWebTestScreenshotLocation)
	{
		try
		{
			File srcFile=((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(failedWebTestScreenshotLocation));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	public static String getCurrentTimeStamp()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
		return simpleDateFormat.format(new Date());
	}

}
