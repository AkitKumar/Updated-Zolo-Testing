package com.testing;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.security.Key;
import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class TestCase1 {
	WebDriver browser;

	@Test
	public void testing() throws InterruptedException {
		Select commonSelect = null;
		AllMethods method = new AllMethods();

		
		browser.get(getData("URL"));
		//method to login
		method.login(browser);
		Thread.sleep(5000);
		//Search the location in search bar
		method.searchKey(browser);
		Thread.sleep(3000);
		//Search the place using Budget,Sharing preference and PG type filter.
		method.searchPlace(browser, commonSelect);
		Thread.sleep(1000);
		//Schedule the Visit:
		//1.If visit is not Scheduled.Program will Schedule for it.
		//2. If Visit is scheduled, program will not procced further.
		method.scheduleVisit(browser, commonSelect);

	}

	@BeforeClass
	public void beforeClass() {
		//setting up system properties and browser
		System.setProperty("webdriver.chrome.driver", "/home/home/Workspace/Drivers/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/home/home/Workspace/Drivers/geckodriver");
		browser = new FirefoxDriver();
		//browser.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(5000);
		browser.close();
	}

	public String getData(String key) {
		//method to get Data from Data properties file
		return GetDataFromFile.getDataFromDataFile(key);
	}

	public String getPath(String key) {
		//method to get Data from getPath properties file
		return GetDataFromFile.getDataFrompathFile(key);
	}
}
