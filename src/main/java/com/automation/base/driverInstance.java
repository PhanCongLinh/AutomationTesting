package com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class driverInstance {
	protected WebDriver driver;

	@BeforeClass
	public void initDriverInstance() {
		System.out.print("Browser opening...");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	@AfterClass
	public void closeDriverInstance() {
		System.out.print("Browser opening...");
		driver.close();
	}
}
