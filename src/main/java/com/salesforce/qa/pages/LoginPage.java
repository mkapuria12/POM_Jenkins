package com.salesforce.qa.pages;

import org.openqa.selenium.WebDriver;
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
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	public HomePage login(String un, String pwd){
		sUsername.sendKeys(un);
		sPassword.sendKeys(pwd);
		sLogin.click();
//		    	JavascriptExecutor js = (JavascriptExecutor)driver;
//		    	js.executeScript("arguments[0].click();", loginBtn);
		    	
		return new HomePage();
	}

}
