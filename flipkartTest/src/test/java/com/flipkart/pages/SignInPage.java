package com.flipkart.pages;


import com.flipkart.utils.CommonFactory;
import com.flipkart.utils.Config.LocatorStrategy;
import com.flipkart.utils.JSONParsing;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class SignInPage extends CommonFactory {
	

////******* Sigin  Page Objects Start Here ***********//
	@AndroidFindBy(id="com.google.android.gms:id/cancel")
	private WebElement cancelButton;

	@AndroidFindBy(id="com.flipkart.android:id/tv_right_cta")
	private WebElement useEmail;
	
	@AndroidFindBy(id = "com.flipkart.android:id/phone_input")
	private WebElement emailIDTextField;
	
	@AndroidFindBy(id = "com.flipkart.android:id/button")
	private WebElement continueButton;
	
	@AndroidFindBy(id = "phone_input")
	private WebElement passwordField;
	
	@AndroidFindBy(id = "Back Button")
	private WebElement backButton;

	@AndroidFindBy(accessibility = "Drawer")
	private WebElement hamBurgerMenu;

	@AndroidFindBy(accessibility = "Open Drawer")
	public WebElement backButtonHome;


	
	public SignInPage()
	{
		CommonFactory.initliseElement(this);
	}


//************* Login Function Start here *****************//

	@Step("Click On Cancel Button")
	public void clickOnCancelButton()
	{
		if(isDisplayed(cancelButton))
			clickElement(cancelButton);
	}

	@Step("Login With Valid Credential")
	public void loginWithEmail()
	{
		clickOnCancelButton();
		clickElement(useEmail);
		enterText(JSONParsing.getUserData(0).get("userEmail").toString(), emailIDTextField);
		clickElement(continueButton);
		enterText(JSONParsing.getUserData(0).get("password").toString(), passwordField);
		clickElement(continueButton);
	}

	@Step("Get User Longed in  Name")
	public String getUserName()
	{
		clickElement(getElement(LocatorStrategy.ANDROID_LOCATOR_STRATEGY_TEXT_CONTAINS,"My Account"));
		return getElement(LocatorStrategy.ANDROID_LOCATOR_STRATEGY_XPATH,"//android.view.View[contains(@text,'"+ JSONParsing.getUserData(0).get("userName").toString()+"')]").getText();
	}

	@Step("Select HamBurger Icon")
	public void selectMenu()
	{
		clickElement(hamBurgerMenu);
	}

	@Step("Click on Back Button")
	public void selectBackOption()
	{
		clickElement(backButtonHome);
	}

}
