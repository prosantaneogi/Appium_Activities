package activityPackage;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Session3_Activity1 {
	AppiumDriver<MobileElement> driver = null;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Pixel 3 Emulator");
		caps.setCapability("platformName", "Android");
		caps.setCapability("noReset", true);
		caps.setCapability("appPackage", "com.google.android.apps.messaging");
		caps.setCapability("appActivity", ".ui.ConversationListActivity");

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		wait = new WebDriverWait(driver, 5);
	}

	@Test
	public void smsTest() {
		// Locate the button to write a new message and click it
		//driver.findElement(MobileBy.AndroidUIAutomator("description(\"Create New Message\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button\")")).click();
		// Enter the number to send message to
		//String contactBoxLocator = "resourceId(\"com.microsoft.android.smsorganizer:id/auto_complete_contact\")";
		String contactBoxLocator = "resourceId(\"com.google.android.apps.messaging:id/recipient_text_view\")";
		
		// Enter your own phone number
		driver.findElement(MobileBy.AndroidUIAutomator(contactBoxLocator)).sendKeys("8335069626");

		// Focus on the message text box
		String messageBoxLocator = "resourceId(\"com.microsoft.android.smsorganizer:id/hint_message\")";
		driver.findElement(MobileBy.AndroidUIAutomator(messageBoxLocator)).click();

		// Type in a message
		messageBoxLocator = "resourceId(\"com.google.android.apps.messaging:id/compose_message_text\")";
		MobileElement composeMessageInput = driver.findElement(MobileBy.AndroidUIAutomator(messageBoxLocator));
		composeMessageInput.sendKeys("Hello from Appium");

		// Send the message
		//driver.findElement(MobileBy.AndroidUIAutomator("description(\"Send SMS\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")")).click();
		// Wait for message to show
		//wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("message_text_view")));
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.apps.messaging:id/message_text")));
		// Assertion
		//String messageLocator = "resourceId(\"com.microsoft.android.smsorganizer:id/message_text_view\")";
		String messageLocator = "resourceId(\"com.google.android.apps.messaging:id/message_text\")";
		String sentMessageText = driver.findElement(MobileBy.AndroidUIAutomator(messageLocator)).getText();
		Assert.assertEquals(sentMessageText, "Hello from Appium");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
