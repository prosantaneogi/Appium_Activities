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

public class ApplicationGoogleKeep_AddNote {

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
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
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
		
		//Press the back button and make an assertion to ensure that the note was added.
		driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]").click();
		
		List<MobileElement> Notes = driver.findElementsById("com.google.android.keep:id/browse_text_note");
		int noOfNotes = Notes.size();
		List<MobileElement> Notestitles =driver.findElementsById("com.google.android.keep:id/index_note_title");
		List<MobileElement> Notesdesc =driver.findElementsById("com.google.android.keep:id/index_note_text_description");
		for(int i = 0 ; i<noOfNotes ; i++) 
		{
			//driver
			String notetitle = Notestitles.get(i).getText();
			String notedescription = Notesdesc.get(i).getText();
			System.out.println("The current Note Title is :" + notetitle + " and the Description is :" + notedescription );
			try {
				assertEquals(notetitle, NoteTitle);
				assertEquals(notedescription, NoteDesc);
				System.out.println("The Creted Note is exists with Title as:" + notetitle + " and the Description is :" + notedescription );
				break;
			}
			catch(Exception e) {
				System.out.println("The Created Note is not exists");
			}
		}
		
		
		
		
	}
}
