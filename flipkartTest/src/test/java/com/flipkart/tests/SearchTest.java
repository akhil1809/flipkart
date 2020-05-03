package com.flipkart.tests;

import com.flipkart.utils.Config;
import com.flipkart.utils.DriverManager;
import com.flipkart.utils.FileUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest {
	

	public String productName;
	public String productPrice;
	public String productSize;

	@Test(alwaysRun = true,priority = 2,groups= {"flipkartSearch"})
	@Description("Verify the Search Product Functionality")
	@Severity(SeverityLevel.CRITICAL)
	public void searchTest()
	{
		try
		{
			DriverManager.getSignInPage().loginWithEmail();
			DriverManager.getGesture().horizontalSwipe(DriverManager.getSearchPage().carousel);
			DriverManager.getSearchPage().searchProductGroup(FileUtils.readFromPropertyFile("productGroup"),FileUtils.readFromPropertyFile("productSearchTitle"));
			DriverManager.getSearchPage().allowPopUp();
			Assert.assertTrue(DriverManager.getSearchPage().selectProduct());
		}
		catch (Exception e)
		{
			Assert.fail("Test Case Failed");
		}

	}

	@Test(alwaysRun = true,priority = 3,groups= {"flipkartSearch"})
	@Description("Verify to Product count in Cart")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyAddItemCount()
	{
		try
		{
			DriverManager.getSearchPage().addItem();
			productName=DriverManager.getSearchPage().getProductDetailOnProductList(Config.PRODUCT_DETAIL.PRODCUT_NAME,FileUtils.readFromPropertyFile("productNameOnList"));
			productPrice=DriverManager.getSearchPage().getProductDetailOnProductList(Config.PRODUCT_DETAIL.PRODUCT_RATE,FileUtils.readFromPropertyFile("productPrice"));
			productSize=DriverManager.getSearchPage().getProductDetailOnProductList(Config.PRODUCT_DETAIL.PRODUCT_SIZE,FileUtils.readFromPropertyFile("productSize"));
			for(int i=0;i<2;i++)
			{
				DriverManager.getGesture().scroll(300);
			}
			Assert.assertEquals(1,DriverManager.getSearchPage().returnCartCount());
		}
		catch (Exception e)
		{
			Assert.fail();
		}

	}


	@Test(alwaysRun = true,priority = 4,groups= {"flipkartSearch"})
	@Description("Verify to check Product Card is displayed or not")
	@Severity(SeverityLevel.CRITICAL)
	public void getProductDetailCheck()
	{
		try
		{
			Assert.assertTrue(DriverManager.getSearchPage().isCartPageDisplayed());
		}
		catch (Exception e)
		{
			Assert.fail();
		}

	}

	@Test(priority = 5,groups= {"flipkartSearch"})
	@Description("Verify to selected Product Name  available in Add to cart Page")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyProductName()
	{
		try
		{
			Assert.assertEquals(productName + " ",DriverManager.getSearchPage().getProductDetailOnProductList(Config.PRODUCT_DETAIL.PRODCUT_NAME,FileUtils.readFromPropertyFile("productNameOnList")));

		}
		catch (Exception e)
		{
			Assert.fail();
		}
	}

	@Test(priority = 6,groups= {"flipkartSearch"})
	@Description("Verify to selected Product Price  available in Add to cart Page")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyProductPrice()
	{
		try
		{
			Assert.assertEquals(productPrice,DriverManager.getSearchPage().getProductDetailOnProductList(Config.PRODUCT_DETAIL.PRODCUT_NAME,FileUtils.readFromPropertyFile("productPrice")));
		}
		catch (Exception e)
		{
			Assert.fail();
		}
	}


	@Test(priority = 7,groups= {"flipkartSearch"})
	@Description("Verify to selected Product Size  available in Add to cart Page")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyProductSize()
	{
		try
		{
			Assert.assertEquals(productSize,DriverManager.getSearchPage().getProductDetailOnProductList(Config.PRODUCT_DETAIL.PRODCUT_NAME,FileUtils.readFromPropertyFile("productSize").replaceAll(" ","")));
		}
		catch (Exception e)
		{
			Assert.fail();
		}
	}
}

