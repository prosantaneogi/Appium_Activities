package project.activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ApplicationGoogleTasks {

	AppiumDriver<MobileElement> driver = null;
	//WebDriver wait = new ;
	String GTask1;
	String GTask2;
	String GTask3;


	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Pixel 3 Emulator");
		caps.setCapability("platformName", "Android");
		//caps.setCapability("avd", "Pixel 3 Emulator");
		caps.setCapability("appPackage", "com.google.android.apps.tasks");
		caps.setCapability("appActivity", ".ui.TaskListsActivity");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	}
	@Test
	public void GoogleTask() throws InterruptedException {
		GTask1 = "Complete Activity with Google Tasks";
		GTask2 = "Complete Activity with Google Keep";
		GTask3 = "Complete the second Activity Google Keep";
		Thread.sleep(3000);
		//Adding Task 1
		driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
		driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys(GTask1);
		driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
		//Adding Task 2
		driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
		driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys(GTask2);
		driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
		//Adding Task 3
		driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
		driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys(GTask3);
		driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();

		String GTask1text = driver.findElementByXPath("//android.widget.FrameLayout[@content-desc="+"\""+""+GTask1+"\"]/android.widget.LinearLayout/android.widget.TextView").getText();
		System.out.println("The First GTask is :" + GTask1text  );
		Assert.assertEquals(GTask1text, GTask1);
		String GTask2text = driver.findElementByXPath("//android.widget.FrameLayout[@content-desc="+"\""+GTask2+"\"]/android.widget.LinearLayout/android.widget.TextView").getText();
		System.out.println("The Second GTask is :" + GTask2text  );
		Assert.assertEquals(GTask2text, GTask2);
		String GTask3text = driver.findElementByXPath("//android.widget.FrameLayout[@content-desc="+"\""+GTask3+"\"]/android.widget.LinearLayout/android.widget.TextView").getText();
		System.out.println("The Third GTask is :" + GTask3text  );
		Assert.assertEquals(GTask3text, GTask3);

		driver.findElementByXPath("(//android.view.View[@content-desc=\"Mark as complete\"])[1]").click();
		System.out.println("The First GTask is Completed");
		driver.findElementByXPath("(//android.view.View[@content-desc=\"Mark as complete\"])[2]").click();
		System.out.println("The Second GTask is Completed");
		driver.findElementByXPath("(//android.view.View[@content-desc=\"Mark as complete\"])[3]").click();
		System.out.println("The Third GTask is Completed");

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
