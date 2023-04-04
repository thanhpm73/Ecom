package com.ecom.testCases;

import java.io.IOException;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecom.pageObjects.LoginPage;
import com.ecom.utilities.XLUtils;

public class TC_Login_Excel_001 extends BaseClass {
	
	@Test(dataProvider = "LoginData")
	public void LoginExcel(String user, String pwd) throws InterruptedException
	{
		LoginPage lp= new LoginPage(driver);
	
		lp.setUserName(user);
		logger.info("User name provided");
		
		lp.setPassWord(pwd);
		logger.info("password provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		
		
		
		if(isAlertPresent()==true)
		{
			
			driver.switchTo().alert().accept(); // close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.warn("Login Passed");
			//lp.clickLogout();
			
			
			Thread.sleep(3000);
			driver.switchTo().alert().accept();// close logout alert
			driver.switchTo().defaultContent();
		}
		
	}
	
	public boolean isAlertPresent()// user defined method created to check alert is present or not 
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}catch (NoAlertPresentException e)
		{
			return false;
		}
	}
	
	

	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path= System.getProperty("user.dir")+"/src/test/java/com/ecom/testData/LoginData.xlsx";
		int rownum= XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1",1);
		String logindata[][]= new String[rownum][colcount];
		
		for(int i=1;i<=rownum; i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
			}
		}
	return logindata;
				
	}
	

}
