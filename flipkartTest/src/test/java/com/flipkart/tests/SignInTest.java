package com.flipkart.tests;

import com.flipkart.utils.DriverManager;
import com.flipkart.utils.JSONParsing;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

	  @Test(priority= 1,groups= {"flipkartSearch"})
	  @Description("Verify to check Login Successful or not")
	  @Severity(SeverityLevel.CRITICAL)
	  public void verifyLoginUser()
	  {
	  		try
			{
				DriverManager.getSignInPage().loginWithEmail();
				DriverManager.getSignInPage().selectMenu();
				Assert.assertEquals(DriverManager.getSignInPage().getUserName(), JSONParsing.getUserData(0).get("userName").toString());
				DriverManager.getSignInPage().selectBackOption();
			}
	  		catch (Exception e)
			{
				DriverManager.getSignInPage().selectBackOption();
				Assert.fail("Test Case Failed");
			}
	  }

}
