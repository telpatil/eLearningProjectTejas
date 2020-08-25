//Add Implicit/explicit wait...
//Create extends report for storing the Test case run results.
package com.training.sanity.tests;

import java.io.File;
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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginPOMTests {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
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
		loginPOM = new LoginPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//driver.quit();
	}
	
	@Test
	public void validLoginTest() throws InterruptedException {
	ExtentReports extent;
	ExtentTest extentTest;
	extent=new ExtentReports(System.getProperty("user.dir")+("/test-output/LoginTestResults1.html"));
	extent.loadConfig(new File(System.getProperty("user.dir")+("\\extent-config1.xml")));
	extentTest=extent.startTest("User Name Input");
	extentTest.log(LogStatus.PASS, "User Name Input Successful!");
	loginPOM.sendUserName("manzoora");
	String expUN="manzoora";
	String actUN=loginPOM.userName.getAttribute("value");
	Assert.assertEquals(actUN,expUN);
	extent.endTest(extentTest);
	extentTest.log(LogStatus.PASS, "Password Input Successful!");
	loginPOM.sendPassword("manzoora");
	String expPW="manzoora";
	String actPW=loginPOM.password.getAttribute("value");
	screenShot.captureScreenShot();
	Assert.assertEquals(actPW, expPW);
	extent.endTest(extentTest);
	extentTest.log(LogStatus.PASS, "Login Successful!");
	loginPOM.clickLoginBtn();
	screenShot.captureScreenShot();
	String actMsg=loginPOM.sendLoginMsg();
	String expMsg="Hello manzoora mehadia and welcome,\n" + 
			"As you can see, your courses list is still empty. That's because you are not registered to any course yet!\n" + 
			"Go ahead and browse our course catalog here to register to any course you like. Once registered, you will see the course appear right here, instead of this message.\n" + 
			"Course catalog";
	Assert.assertEquals(actMsg, expMsg);
	extentTest.log(LogStatus.PASS, "Login Successful!");
	extent.endTest(extentTest);		
	extent.flush();
	extent.close();
	}	
}