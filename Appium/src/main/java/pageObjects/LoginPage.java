package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {

	public LoginPage(AndroidDriver<AndroidElement> driver) {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter name here']")
	public WebElement nameField;

	@AndroidFindBy(xpath = "//*[@text='Argentina']")
	public WebElement argentina;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	public WebElement femaleBtn;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	public WebElement letsShopBtn;

	@AndroidFindBy(xpath = "//*[@text='ADD TO CART']")
	public List<WebElement> addToCartBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	public WebElement countryDrpdown;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productPrice;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement goToCartBtn;

}
