package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPassPOM {
	private WebDriver driver;
	
	public ForgotPassPOM(WebDriver driver) {
		this.driver= driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="login")
	public WebElement userName; 
	
	@FindBy(linkText="I lost my password")
	public WebElement lostPassLink; 
	
	@FindBy(id="lost_password_user")
	public WebElement enterMail;
	
	@FindBy(id="lost_password_submit")
	public WebElement submitBtn;
	
	@FindBy(xpath="//div[@class='alert alert-info']")
	public WebElement alertMsg;
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public String clickLostPass() {
		this.lostPassLink.click();
		String actURL=driver.getCurrentUrl();
		return actURL;
	}
	
	public void sendMail(String enterMail) {
		this.enterMail.clear();
		this.enterMail.sendKeys(enterMail);
	}
	
	public String clickSubmitBtn(){
		this.submitBtn.click();
		String postSubmitURL=driver.getCurrentUrl();
		return postSubmitURL;
	}
		
	public String retrieveAlertMsg() {
		String msg=this.alertMsg.getText(); 
		return msg;
	}
}