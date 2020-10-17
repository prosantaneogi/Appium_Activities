package project.activity;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ApplicationGoogleKeep_AddNotewithReminder {

	AppiumDriver<MobileElement> driver = null;
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
    Date date = new Date();  
  
	String NoteTitle;
	String NoteDesc;
	@BeforeClass
	public void beforeclass() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Pixel 3 Emulator");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appPackage", "com.google.android.keep");
		cap.setCapability("appActivity", ".activities.BrowseActivity");
		cap.setCapability("noReset", true);
		
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer , cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void AddNote() {
		
		NoteTitle ="Note Title New"+formatter.format(date);
		NoteDesc = "Note Description New"+formatter.format(date);
		
		//Click the Create New Note button to add a new Note.
		driver.findElementById("com.google.android.keep:id/new_note_button").click();
		
		//Add a title for the note and add a small description
		driver.findElementById("com.google.android.keep:id/editable_title").sendKeys(NoteTitle);
		driver.findElementById("com.google.android.keep:id/edit_note_text").sendKeys(NoteDesc);
		
		//Click the reminder icon on the toolbar to add a reminder for Afternoon of the same day
		driver.findElementById("com.google.android.keep:id/menu_reminder").click();
		driver.findElementById("com.google.android.keep:id/time_spinner").click();
		driver.findElementById("com.google.android.keep:id/reminder_time_afternoon").click();
		driver.findElementById("com.google.android.keep:id/save").click();
		
		//Press the back button and make an assertion to ensure that the note was added.
		driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]").click();
		
		
		
		List<MobileElement> Notes = driver.findElementsById("com.google.android.keep:id/browse_text_note");
		int noOfNotes = Notes.size();
		List<MobileElement> Notestitles =driver.findElementsById("com.google.android.keep:id/index_note_title");
		List<MobileElement> Notesdesc =driver.findElementsById("com.google.android.keep:id/index_note_text_description");
		List<MobileElement> Notereminder =driver.findElementsById("com.google.android.keep:id/reminder_chip_text");
		
		for(int i = 0 ; i<noOfNotes ; i++) 
		{
			//driver
			String notetitle = Notestitles.get(i).getText();
			String notedescription = Notesdesc.get(i).getText();
			String Noteremindertext = Notereminder.get(i).getText(); 
			System.out.println("The current Note Title is :" + notetitle + " and the Description is :" + notedescription + "With Reminder : " + Noteremindertext);
			try {
				assertEquals(notetitle, NoteTitle);
				assertEquals(notedescription, NoteDesc);
				assertEquals(Noteremindertext,"Today, 1:00 PM");
				System.out.println("The Creted Note is exists with Title as:" + notetitle + " and the Description is :" + notedescription + "With Reminder : " + Noteremindertext);
				break;
			}
			catch(Exception e) {
				System.out.println("The Created Note is not exists");
			}
		}
		
		
		
		
	}
}
