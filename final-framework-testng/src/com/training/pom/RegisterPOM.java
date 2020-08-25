package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterPOM {
	private WebDriver driver;
	
	public RegisterPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Sign up!')]")
	private WebElement signUpBtn; 
	
	@FindBy(name="firstname")
	public WebElement firstName;
	
	@FindBy(name="lastname")
	public WebElement lastName;
	
	@FindBy(name="email")
	public WebElement eMail;
	
	@FindBy(name="username")
	public WebElement userName;
	
	@FindBy(id="pass1")
	public WebElement passWord;
	
	@FindBy(id="pass2")
	public WebElement passConfirm;
	
	@FindBy(name="phone")
	public WebElement phone;
	
	@FindBy(xpath="//select[@class='selectpicker form-control']")
	public WebElement dropDown;
	
	@FindBy(xpath="//input[@class='register-profile' and @value='1']")
	public WebElement participantBtn;
		
	@FindBy(id="registration_submit")
	public WebElement registerBtn;
	
	@FindBy(xpath="//div[@class='col-xs-12 col-md-12']")
	public WebElement completionPage;
	
	
	public void clickSignUpBtn() {
		this.signUpBtn.click(); 
	}	
	public void sendFirstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}
	
	public void sendLastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}
	
	public void sendEMail(String eMail) {
		this.eMail.clear();
		this.eMail.sendKeys(eMail);
	}
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassWord(String passWord) {
		this.passWord.clear(); 
		this.passWord.sendKeys(passWord); 
	}
	
	public void sendPassConfirm(String passConfirm) {
		this.passConfirm.clear(); 
		this.passConfirm.sendKeys(passConfirm); 
	}
	
	public void sendPhone(String phone) {
		this.phone.clear(); 
		this.phone.sendKeys(phone); 
	}
	
	public void selectDropDown(String lang) {
		Select drop=new Select(dropDown);
		drop.selectByVisibleText(lang);
	}
	
	public void clickParticipantBtn() {
		this.participantBtn.click(); 
	}
	
	public void clickRegisterBtn() {
		this.registerBtn.click(); 
	}
	
	public String navToCompletionPage() {
		String Msg=this.completionPage.getText();
		return Msg;		
	}
}