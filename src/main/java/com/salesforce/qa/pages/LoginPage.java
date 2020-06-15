package com.salesforce.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//creating object repo
	@FindBy(id="username")
	WebElement sUsername;
	
	@FindBy(id="password")
	WebElement sPassword;
	
	@FindBy(id="Login")
	WebElement sLogin;
	
	@FindBy(xpath="//div[@id='error']")
	WebElement errorMessage;
	
	@FindBy(id="rememberUn")
	WebElement rememberMe;
	
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	public void login_validCredential(String un, String pwd){
		sUsername.sendKeys(un);
		sPassword.sendKeys(pwd);
		sLogin.click();
	}

	public void login_invalidCredential(String un, String pwd){
		sUsername.sendKeys(un);
		sPassword.sendKeys(pwd);
		sLogin.click();
		String error=errorMessage.getText();
		System.out.println(error);
	}
	
	public void login_rememberMe(String un, String pwd){
		sUsername.sendKeys(un);
		sPassword.sendKeys(pwd);
		rememberMe.click();
		sLogin.click();
	}
	
	public void logout()
	{
		WebElement UsernameArrow=driver.findElement(By.id("userNav-arrow"));
		UsernameArrow.click();
		System.out.println("Usernmenu dropdown clicked");	
		WebElement logOut=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));	
		logOut.click();
		System.out.println("clicked on logout button");
	}
	
	
}
