package com.cts.SampleProject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 *Sample Project with all Selenium concepts
 *
 */
public class App 
{
private static WebDriver driver;

@BeforeClass
public void browserOpen() throws AWTException
{
    	WebDriverManager.chromedriver().setup();
    	ChromeOptions option=new ChromeOptions();
    	//Open the browser in Incognito mode
    	option.addArguments("--incognito");
    	driver=new ChromeDriver(option);
    	//Other method to open Incognito mode
    	Robot r=new Robot();
    	r.keyPress(KeyEvent.VK_CONTROL);
    	r.keyPress(KeyEvent.VK_SHIFT);
    	r.keyPress(KeyEvent.VK_N);
    	r.keyRelease(KeyEvent.VK_CONTROL);
    	r.keyRelease(KeyEvent.VK_SHIFT);
    	r.keyRelease(KeyEvent.VK_N);
    /**	//To add new extension
    	option.addExtensions(new File("/path/extension.crx"));
    	//To add Binary path
    	option.setBinary(new File("/path/chrome"));
    	//To Accept untrusted certificate
    	option.setAcceptInsecureCerts(false);
    	
    **/
    	driver.get("https://google.com");
}
@Test
public void test()
{
    	System.out.println( "Hello World!" );
        System.out.println( "Hi Johnson" );
}
@AfterClass
public void closeBrowser()
{
		driver.close();
}
}
