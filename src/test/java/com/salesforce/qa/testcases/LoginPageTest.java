package com.salesforce.qa.testcases;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.pages.LoginPage;
import com.salesforce.qa.utilities.CommonUtilities;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	String sheetName="Valid Login Credentials";
	String sheetName1="Invalid Login Credentials";
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	//logger=reports.startTest("LoginPageTest");

	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		log.info("launching chrome broswer");
		initialization();
		loginPage = new LoginPage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		System.out.println(title);
		log.info(title);
		AssertJUnit.assertEquals(title, "Login | Salesforce");
	}
	
	@DataProvider
	public Object[][] getValidLoginTestData() throws Exception{
		Object data[][] = CommonUtilities.readDataFromExcelSheet(sheetName);
		return data;
	}
	
	@Test(priority=2, dataProvider="getValidLoginTestData")
	public void loginTest(String sUsername, String sPassword){
		loginPage.login_validCredential(sUsername,sPassword);
		log.info("logged in successfully!");
	}
		
	@DataProvider
	public Object[][] getInvalidLoginTestData() throws Exception{
		Object data[][] = CommonUtilities.readDataFromExcelSheet(sheetName1);
		return data;
	}
	
	@Test(priority=3, dataProvider="getInvalidLoginTestData")
	public void logintest_invalidCredentials(String sUsername, String sPassword) {
		loginPage.login_invalidCredential(sUsername,sPassword);
		WebElement loginError=driver.findElement(By.id("error"));
		log.error(loginError.getText());
	}
	
	@Test(priority=4, dataProvider="getValidLoginTestData")
	public void logintest_rememberMe(String sUsername, String sPassword) throws InterruptedException {
		loginPage.login_rememberMe(sUsername,sPassword);
		log.info("Logged in");
		log.info("on home page");
		loginPage.logout();
		log.info("logged out");
		Thread.sleep(3000);
		WebElement rememberID=driver.findElement(By.id("idcard-identity"));
		log.info(rememberID.getText());
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
		log.info("Quit the browser.");
	}

}
