//Tejas(8/18 eve): This program is being written for teacher registration and the validation steps are yet to be added.
//This program does NOT use a POM file; for the shorter version with a POM, please refer to RegPOMTests.java class.
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RegisterTests {

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
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(priority=1)
	public void validRegisterTest() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'Sign up!')]")).click();//performs Step-1
		String expURL="http://elearningm1.upskills.in/main/auth/inscription.php";
		String actURL=driver.getCurrentUrl();
		Assert.assertEquals(actURL,expURL);  //validates Step-1
		Thread.sleep(2000);
		driver.findElement(By.name("firstname")).sendKeys("manzoor"); //performs Step-2
		String expFN="manzoor";
		Thread.sleep(1000);
		String actFN=driver.findElement(By.name("firstname")).getAttribute("value");
		Assert.assertEquals(actFN,expFN); //validates Step-2
		driver.findElement(By.name("lastname")).sendKeys("mehadi");//performs Step-3
		String expLN="mehadi";
		String actLN=driver.findElement(By.name("lastname")).getAttribute("value");
		Assert.assertEquals(actLN,expLN); //validates Step-3
		driver.findElement(By.name("email")).sendKeys("manzoorq@gmail.com");//performs Step-4
		String expEmail="manzoorq@gmail.com";
		String actEmail=driver.findElement(By.name("email")).getAttribute("value");
		Assert.assertEquals(actEmail,expEmail); //validates Step-4		
		driver.findElement(By.id("username")).sendKeys("manzoorqaaaa");//performs Step-5
		String expUN="manzoorqaaaa";
		String actUN=driver.findElement(By.name("username")).getAttribute("value");
		Assert.assertEquals(actUN,expUN); //validates Step-5
		driver.findElement(By.id("pass1")).sendKeys("manzoor"); //performs Step-6
		String expP1="manzoor";
		String actP1=driver.findElement(By.name("pass1")).getAttribute("value");
		Assert.assertEquals(actP1,expP1); //validates Step-6		
		driver.findElement(By.id("pass2")).sendKeys("manzoor"); //performs Step-7
		String expP2="manzoor";
		String actP2=driver.findElement(By.name("pass2")).getAttribute("value");
		Assert.assertEquals(actP2,expP2); //validates Step-7	
		driver.findElement(By.name("phone")).sendKeys("9876543214");//performs Step-8
		String expPh="9876543214";
		String actPh=driver.findElement(By.name("phone")).getAttribute("value");
		Assert.assertEquals(actPh,expPh); //validates Step-8
		Select dropdown=new Select(driver.findElement(By.xpath("//select[@class='selectpicker form-control']")));
		dropdown.selectByVisibleText("English"); //performs Step-9
		String expLang="english";
		String actLang=dropdown.getFirstSelectedOption().getAttribute("value").toString();
		Assert.assertEquals(actLang,expLang); //validates Step-9		
		
		/*the below segment of code is for printing all the drop-down options
		List<WebElement> opt=dropdown.getOptions();
		for(int i=0;i<opt.size();i++)
		{
			String optVal=opt.get(i).getText();
			System.out.println(optVal);
		}*/
		
		driver.findElement(By.xpath("//input[@class='register-profile' and @value='1']")).click(); //performs Step-10
		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='register-profile' and @value='1']")).isSelected());//validates Step-10
		screenShot.captureScreenShot();
		driver.findElement(By.xpath("//button[@id='registration_submit']")).click();//performs Step-11
		String Reg=driver.findElement(By.xpath("//div[@class='col-xs-12 col-md-12']")).getText();
		System.out.println(Reg);
		String expMsg="Dear manzoor mehadi,\n" + 
				"\n" + 
				"Your personal settings have been registered.\n" + 
				"An e-mail has been sent to remind you of your login and password.\n" + 
				"  Next";
		Assert.assertEquals(Reg,expMsg);   //validates Step-11 
		screenShot.captureScreenShot();
	}
	
/*	@Test(priority=2)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("First");
	}*/
}
