package com.flipkart.pages;

import com.flipkart.utils.CommonFactory;
import com.flipkart.utils.Config;
import com.flipkart.utils.DriverManager;
import com.flipkart.utils.FileUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchPage  extends CommonFactory {

	////******* Search  Page Objects Start Here ***********//
	
	@AndroidFindBy(id="com.flipkart.android:id/search_autoCompleteTextView")
	private WebElement searchField;

	@AndroidFindBy(id="com.flipkart.android:id/search_widget_textbox")
	private WebElement searchBox;

	@AndroidFindBy(id="com.flipkart.android:id/root_titles")
	private List<WebElement> productNameList;

	@AndroidFindBy(id="com.flipkart.android:id/allow_button")
	private  WebElement allowButton;

	@AndroidFindBy(id="com.android.packageinstaller:id/permission_allow_button")
	private WebElement permissionButton;

	@AndroidFindBy(id="com.flipkart.android:id/cart_icon")
	private WebElement cartIcon;

	@AndroidFindBy(id="com.flipkart.android:id/cart_count")
	private WebElement cartCount;

	@AndroidFindBy(id="com.flipkart.android:id/title_action_bar")
	public  WebElement cartPage;

	@AndroidFindBy(id="com.flipkart.android:id/carousel_view_pager")
	public WebElement carousel;

	@AndroidFindBy(id="com.flipkart.android:id/banner_image")
	private List<WebElement>items;

	public List<String> getProdInfo=new ArrayList<String>() ;


	public SearchPage()
	{
		CommonFactory.initliseElement(this);
	}

	//************* Search Page Functions Start here *****************//

	@Step("Search with {0} and {1}")
	public void searchProductGroup(String productGroup,String productSearchTitle)
	{
		clickElement(searchBox);
		enterText(productGroup,searchField);
		pause(2);
		//clickElement(getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_TEXT, productSearchTitle));
		clickElement(productNameList.get(1));
	}

	@Step("Allow Popup")
	public void allowPopUp()
	{
		if(isDisplayed(allowButton))
			clickElement(allowButton);
		if(isDisplayed(permissionButton))
			clickElement(permissionButton);
	}

	@Step("Get  Product name  is {0}")
	public String getProductName(String productName)
	{
		return getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_TEXT,"Dhara Refined Sunflower Oil Pouch").getText();
	}
	@Step("Get Product price is {0}")
	public String getProductPrice(String productName)
	{
		return getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_TEXT,"₹110").getText();
	}

	@Step("Get All Product Attributes")
	public String getProductDetailOnProductList(Config.PRODUCT_DETAIL product_detail, String productAttribute)
	{
		switch (product_detail)
		{
			case PRODCUT_NAME:
				return getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_TEXT_CONTAINS,productAttribute).getText();
			case PRODUCT_RATE:
				return getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_XPATH,"//android.widget.TextView[contains(@text,'"+ FileUtils.readFromPropertyFile("productNameOnList")+"')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]/android.widget.TextView[contains(@text,'₹110')]").getText();
			case PRODUCT_SIZE:
				return getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_XPATH,"//android.widget.TextView[contains(@text,'"+ FileUtils.readFromPropertyFile("productNameOnList")+"')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[contains(@text,'1 L')]").getText();
				default:
				return  null;
		}
	}

	@Step("Select Product")
	public boolean selectProduct()
	{
		while(isDisplayed(getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_TEXT, FileUtils.readFromPropertyFile("productNameOnList")))==false) {
			DriverManager.getGesture().scroll(-600);
		}
		return isDisplayed(getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_TEXT, FileUtils.readFromPropertyFile("productNameOnList")));
	}

@Step("Add Product in Cart")
	public void addItem()
	{
		clickElement(getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_XPATH,"//android.widget.TextView[contains(@text,'"+ FileUtils.readFromPropertyFile("productNameOnList")+"')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView[contains(@text,'Add item')]"));
	}

	@Step("Check cart page displayed ot not")
	public boolean isCartPageDisplayed()
	{
		clickElement(cartIcon);
		clickElement(getElement(Config.LocatorStrategy.ANDROID_LOCATOR_STRATEGY_TEXT,"View All"));
		return  isDisplayed(cartPage);
	}

	public HashMap<Enum<?>, String> getAllProductDetail(String attribute)
	{
		HashMap<Enum<?>,String> getAttribute=new HashMap<Enum<?>, String>();

		getAttribute.put(Config.PRODUCT_DETAIL.PRODCUT_NAME,getProductDetailOnProductList(Config.PRODUCT_DETAIL.PRODCUT_NAME,attribute));
		getAttribute.put(Config.PRODUCT_DETAIL.PRODUCT_RATE,getProductDetailOnProductList(Config.PRODUCT_DETAIL.PRODUCT_RATE,attribute));
		getAttribute.put(Config.PRODUCT_DETAIL.PRODUCT_SIZE,getProductDetailOnProductList(Config.PRODUCT_DETAIL.PRODUCT_SIZE,attribute));
		return getAttribute;
	}

	@Step("Get Product Count in Cart")
	public int returnCartCount()
	{
		return Integer.parseInt(cartCount.getText());
	}


}
