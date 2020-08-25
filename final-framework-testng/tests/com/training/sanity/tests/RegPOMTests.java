//Add Implicit/explicit wait...
//Create extends report for storing the Test case run results.
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
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RegPOMTests {

	private WebDriver driver;
	private String baseUrl;
	private RegisterPOM registerPOM;
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
		registerPOM = new RegisterPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);   ;
		driver.quit();
	}
	
	@Test(priority=1)
	public void validRegisterTest() throws InterruptedException {
		registerPOM.clickSignUpBtn();//performs Step-1
		String expURL="http://elearningm1.upskills.in/main/auth/inscription.php";
		String actURL=driver.getCurrentUrl();
		Assert.assertEquals(actURL,expURL);  //validates Step-1
		Thread.sleep(2000);
		registerPOM.sendFirstName("manzoor"); //performs Step-2
		String expFN="manzoor";
		String actFN=registerPOM.firstName.getAttribute("value");
		Assert.assertEquals(actFN,expFN); //validates Step-2
		registerPOM.sendLastName("mehadi");//performs Step-3
		String expLN="mehadi";
		String actLN=registerPOM.lastName.getAttribute("value");
		Assert.assertEquals(actLN,expLN); //validates Step-3
		registerPOM.sendEMail("manzoor@gmail.com");//performs Step-4
		String expEmail="manzoor@gmail.com";
		String actEmail=registerPOM.eMail.getAttribute("value");
		Assert.assertEquals(actEmail,expEmail); //validates Step-4		
		registerPOM.sendUserName("manzoorqq");;//performs Step-5
		String expUN="manzoorqq";
		String actUN=registerPOM.userName.getAttribute("value");
		Assert.assertEquals(actUN,expUN); //validates Step-5
		registerPOM.sendPassWord("manzoor"); //performs Step-6
		String expP1="manzoor";
		String actP1=registerPOM.passWord.getAttribute("value");
		Assert.assertEquals(actP1,expP1); //validates Step-6		
		registerPOM.sendPassConfirm("manzoor"); //performs Step-7
		String expP2="manzoor";
		String actP2=registerPOM.passConfirm.getAttribute("value");
		Assert.assertEquals(actP2,expP2); //validates Step-7	
		registerPOM.sendPhone("9876543215");//performs Step-8
		String expPh="9876543215";
		String actPh=registerPOM.phone.getAttribute("value");
		Assert.assertEquals(actPh,expPh); //validates Step-8
		registerPOM.selectDropDown("English"); //performs Step-9
		String expLang="english";
		String actLang=registerPOM.dropDown.getAttribute("value").toString();
		Assert.assertEquals(actLang,expLang); //validates Step-9		
		
		/*the below segment of code is for printing all the drop-down options
		List<WebElement> opt=dropdown.getOptions();
		for(int i=0;i<opt.size();i++)
		{
			String optVal=opt.get(i).getText();
			System.out.println(optVal);
		}*/
		
		registerPOM.clickParticipantBtn();//performs Step-10
		Assert.assertTrue(registerPOM.participantBtn.isSelected());//validates Step-10
		screenShot.captureScreenShot();
		registerPOM.clickRegisterBtn();;//performs Step-11
		String Reg=registerPOM.navToCompletionPage();
		System.out.println();
		String expMsg="Dear manzoor mehadi,\n" + 
				"\n" + 
				"Your personal settings have been registered.\n" + 
				"An e-mail has been sent to remind you of your login and password.\n" + 
				"  Next";
		Assert.assertEquals(Reg,expMsg);   //validates Step-11 
		screenShot.captureScreenShot();
	}
	
}
