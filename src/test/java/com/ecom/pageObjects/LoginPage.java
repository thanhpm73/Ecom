package com.ecom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		
		ldriver=rdriver;
		
		// dùng để khởi tạo các webelement
		PageFactory.initElements(rdriver, this);
		
	}
	
	
	@FindBy(name="email")
	WebElement txtname;
	
	@FindBy(name="password")
	WebElement txtpass;
	
	@FindBy(name = "commit")
	WebElement btnclicklogin;
	
	public void setUserName(String uname)
	{
		
		txtname.sendKeys(uname);
	}
	public void setPassWord(String pwd)
	{
		txtpass.sendKeys(pwd);
	}
	public void clickSubmit()
	{
		btnclicklogin.click();
	}
	
}
