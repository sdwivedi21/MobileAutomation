package appiumFrameworkUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseForEcommerceApp {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	// command to terminate all ports
	// taskkill /F /IM node.exe
//	public AppiumDriverLocalService startServer() {

//		boolean flag = checkIfServerIsRunning(4723);
//
//		if (!flag) {
//			service = AppiumDriverLocalService.buildDefaultService();
//			// for making this run smooth , add following dependencies to pom.
//			// slf4j-simple ,slf4j-api, commons-lang3, commons-io, commons-validation
//			service.start();
//
//		}
//		return service;
//	}

//	@SuppressWarnings("resource")
//	public static boolean checkIfServerIsRunning(int port) {
//
//		boolean isServerRunning = false;
//		ServerSocket serversocket;
//		try {
//			serversocket = new ServerSocket(port);
////			serversocket.close();
//		} catch (IOException e) {
//			// If control comes here , then server is running.
//			isServerRunning = true;
//		} finally {
//			serversocket = null;
//		}
//		return isServerRunning;
//
//	}

//	public static void startEmulator() throws IOException {
//		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\res\\startEmulator.bat");
//
//	}

	public static void getScreenshot(String s) throws IOException {
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir") + "\\" + s + ".png"));

	}

	public static AndroidDriver<AndroidElement> capabilities() throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\appiumFrameworkUtilities\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		String device = (String) prop.get("deviceName");

//		if (device.contains("Pixel")) {
//			startEmulator();
//		}
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_API_27");
		capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");// This is udid , not name .
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.VERSION, "8.1");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.androidsample.generalstore");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
				"com.androidsample.generalstore.SplashActivity");
		capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, "true");

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

}
