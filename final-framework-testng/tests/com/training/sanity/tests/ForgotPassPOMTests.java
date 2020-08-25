//Modified program from the Test Case as no email is generated for pass recovery..
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.training.generics.ScreenShot;
import com.training.pom.ForgotPassPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ForgotPassPOMTests {

	private WebDriver driver;
	private String baseUrl;
	private ForgotPassPOM forgotPassPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		forgotPassPOM = new ForgotPassPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.quit();
	}
	
	@Test
	public void validForgotPassTest() throws InterruptedException {
	forgotPassPOM.sendUserName("manzoora");
	String expUN="manzoora";
	String actUN=forgotPassPOM.userName.getAttribute("value");
	Assert.assertEquals(actUN,expUN);
	String actURL=forgotPassPOM.clickLostPass();
	String expURL="http://elearningm1.upskills.in/main/auth/lostPassword.php";
	Assert.assertEquals(actURL, expURL);
	forgotPassPOM.sendMail("manzoor@gmail.com");
	String expMail="manzoor@gmail.com";
	String actMail=forgotPassPOM.enterMail.getAttribute("value");
	Assert.assertEquals(actMail, expMail);
	String actpostSubmitURL=forgotPassPOM.clickSubmitBtn();
	String expPostSubmitURL="http://elearningm1.upskills.in/";
	Assert.assertEquals(actpostSubmitURL,expPostSubmitURL);
	String actAlertMsg=forgotPassPOM.retrieveAlertMsg();
	String expAlertMsg="Your password has been reset";
	screenShot.captureScreenShot();
	System.out.println("The Actual Alert Msg Received on the page is:" +actAlertMsg);
	Assert.assertEquals(actAlertMsg, expAlertMsg);
	}	
}