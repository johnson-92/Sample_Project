package com.cts.SampleProject;

import java.awt.AWTException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import objectRepository.LoginPageFreeCRM;
/**
 *Sample Project with all Selenium concepts
 *
 */
public class App extends LoginPageFreeCRM
{

@BeforeClass
public void browserOpen() throws AWTException
{
//    	WebDriverManager.chromedriver().setup();
//    	ChromeOptions option=new ChromeOptions();
//    	//Open the browser in Incognito mode
//    	option.addArguments("--incognito");
//    	driver=new ChromeDriver(option);
    /**	//To add new extension
    	option.addExtensions(new File("/path/extension.crx"));
    	//To add Binary path
    	option.setBinary(new File("/path/chrome"));
    	//To Accept untrusted certificate
    	option.setAcceptInsecureCerts(false);
    **/
        getDriver();	
    	driver.get("https://google.com");    
}
@Test
public void test() throws InterruptedException
{
	LoginPageFreeCRM login=new LoginPageFreeCRM();
	login.getTxtSearch().sendKeys("Free CRM");
	Thread.sleep(5000);
	btnClick(login.getBtnClk());
	Thread.sleep(5000);
	validLink(login.getAllLink());
}
@AfterClass
public void closeBrowser()
{
		driver.close();
}
}
