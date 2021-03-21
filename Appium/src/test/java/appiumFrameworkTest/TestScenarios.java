package appiumFrameworkTest;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import appiumFrameworkUtilities.BaseForEcommerceApp;
import appiumFrameworkUtilities.TestData;
import appiumFrameworkUtilities.Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;
import pageObjects.LoginPage;

public class TestScenarios extends BaseForEcommerceApp {
	/**
	 * 
	 * get two items and sum them up and verify at the final page.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(dataProvider = "InputData", dataProviderClass = TestData.class)
	public void calculateTotalandCompare(String inputText) throws InterruptedException, IOException {

		AndroidDriver<AndroidElement> driver = capabilities();
		LoginPage loginPage = new LoginPage(driver);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		System.out.println("Test case started");
		loginPage.nameField.sendKeys(inputText);

		// to hide keyboard
		driver.hideKeyboard();

		loginPage.countryDrpdown.click();
		Utilities util = new Utilities(driver);
		util.scrollToText("Argentina");

		loginPage.argentina.click();
		loginPage.femaleBtn.click();
		loginPage.letsShopBtn.click();

		loginPage.addToCartBtn.get(0).click();
		loginPage.addToCartBtn.get(0).click();
		loginPage.goToCartBtn.click();
		Thread.sleep(5000);
		String amount1 = loginPage.productPrice.get(0).getText().substring(1).trim();
		String amount2 = loginPage.productPrice.get(1).getText().substring(1).trim();

		double amt1val = Double.parseDouble(amount1);
		double amt2val = Double.parseDouble(amount2);
		double totalAmt = amt1val + amt2val;
		System.out.println("totalAmt calculated " + totalAmt);

		String totalAmount = CheckoutPage.totalAmtAtCheckoutPage.getText().substring(1).trim();
		double finalAmt = Double.parseDouble(totalAmount);
		System.out.println("totalAmount extracted " + totalAmount);
		Assert.assertEquals(totalAmt, finalAmt);

		System.out.println("Test case closed");
	}

	@Test
	public void totalValidation() throws IOException, InterruptedException {
		AndroidDriver<AndroidElement> driver = capabilities();
		FormPage formPage = new FormPage(driver);
		formPage.getNameField().sendKeys("hello");
		driver.hideKeyboard();
		formPage.femaleOption.click();
		formPage.getcountrySelection().click();
		Utilities u = new Utilities(driver);
		u.scrollToText("Argentina");

		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);
		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		double sum = 0;

		for (int i = 0; i < count; i++) {
			String amount1 = CheckoutPage.productList.get(i).getText();
			double amount = getAmount(amount1);
			sum = sum + amount;// 280.97+116.97.
		}
		System.out.println(sum + "sum of products");

		String total = CheckoutPage.totalAmtAtCheckoutPage.getText();

		total = total.substring(1);
		double totalValue = Double.parseDouble(total);
		System.out.println(totalValue + "Total value of products");
		Assert.assertEquals(sum, totalValue);

		driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();

		Thread.sleep(7000);
		Set<String> contexts = driver.getContextHandles();
		for (String contextName : contexts) {
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("Hello Google");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));

	}

	public static double getAmount(String value) {
		value = value.substring(1);
		double amount2value = Double.parseDouble(value);
		return amount2value;

	}

}