package project.activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleChrome_LoginForm2 {
	WebDriverWait wait;
	AndroidDriver<MobileElement> driver = null;

	@BeforeTest
	public void setup() throws MalformedURLException {

		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Pixel_3_Emulator");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		//driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void LoginForm() throws InterruptedException {
		driver.get("https://www.training-support.net/selenium");
		/*
		 * driver.findElement(MobileBy.AndroidUIAutomator(
		 * "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Login Form\").instance(0))"
		 * ));
		 */
		String uiSelector = "new UiSelector().textMatches(\"Login Form\")";

		String command = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                + uiSelector + ")";


driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Popups\").instance(0))"));

		driver.findElementByXPath("//android.widget.TextView[@text='Popups']").click();

		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@text='Sign In']")));

		driver.findElementByXPath("//android.widget.Button[@text='Sign In']").click();

		driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").click();
		driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").sendKeys("admin");

		driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").click();
		driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").sendKeys("password");

		driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();

		String loginSuccess = driver.findElementByXPath("//android.view.View[@text='Welcome Back, admin']").getText();

		Assert.assertEquals(loginSuccess, "Welcome Back, admin");

		driver.findElementByXPath("//android.widget.Button[@text='Sign In']").click();

		driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").click();
		driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").clear();
		driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").sendKeys("admin1");

		driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").click();
		driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").clear();
		driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").sendKeys("password1");

		driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();

		String loginFailure = driver.findElementByXPath("//android.view.View[@text='Invalid Credentials']").getText();

		Assert.assertEquals(loginFailure, "Invalid Credentials");


	}
	@AfterClass
	public void afterclass() {
		//driver.close();
	}
}
