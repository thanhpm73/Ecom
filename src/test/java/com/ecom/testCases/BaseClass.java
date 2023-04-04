package com.ecom.testCases;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.ecom.utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class BaseClass {
	
	ReadConfig readconfig= new ReadConfig();
	
	public String baseUrl=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		//System.setProperty("webdiver.chrome.driver",readconfig.getChromepath());
		//driver= new ChromeDriver();
		
		logger =Logger.getLogger("eBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdiver.chrome.driver",readconfig.getChromepath());
			driver= new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdiver.gecko.driver",readconfig.getFirefoxpath());
			driver= new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		
	}
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"/Screenshots/"+ tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	public String randomestring()
	{
		String  generatedstring= RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}
	
	public static String randomeNum()
	{
		String generatedString2= RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
