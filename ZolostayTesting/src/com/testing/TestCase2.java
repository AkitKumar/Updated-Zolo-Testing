package com.testing;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class TestCase2 {
	WebDriver browser;
	SoftAssert asert = new SoftAssert();

	@Test
	public void testCase2() throws InterruptedException {
		// method class contains methods for login
		AllMethods method = new AllMethods();
		Select commonSelect;

		browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// send the URL to browser
		browser.get(getData("URL"));
		// login method
		method.login(browser);
		Thread.sleep(5000);
		// search for a place using search field
		method.searchKey(browser);
		Thread.sleep(3000);
		// select a particular place
		// enter in budget filter
		commonSelect = new Select(browser.findElement(By.xpath(getPath("budgetdropdown"))));
		commonSelect.selectByVisibleText(getData("budget"));
		// enter in sharing filler
		commonSelect = new Select(browser.findElement(By.xpath(getPath("sharingPreference"))));
		commonSelect.selectByVisibleText(getData("sharing"));
		// enter in PG type filter
		commonSelect = new Select(browser.findElement(By.id(getPath("PGtype"))));
		commonSelect.selectByVisibleText(getData("PGtype"));
		// selecting a particular location
		browser.findElement(By.xpath(getPath("selectPG") + getData("PG") + "']")).click();
		// request a bed
		Thread.sleep(3000);
		browser.findElement(By.xpath(getPath("requestBed"))).click();
		// enter date and selecting sharing type
		browser.findElement(By.xpath(getPath("inputDate"))).clear();
		browser.findElement(By.xpath(getPath("inputDate"))).sendKeys(getData("paymentDate"));
		browser.findElement(By.xpath(getPath(getData("sharingPreference")))).click();
		browser.findElement(By.xpath(getPath("Proccedtopay"))).click();
		// creating SoftAssert object

		// getting Name and Phone number from payment
		System.out.println("tesname" + browser.findElement(By.xpath(getPath("TestName"))).getAttribute("value"));
		asert.assertEquals(browser.findElement(By.xpath(getPath("TestName"))).getAttribute("value").trim(),
				"Test User");
		asert.assertEquals(browser.findElement(By.xpath(getPath("MobileNumber"))).getAttribute("value").trim(),
				"7777777015");

		// Applying promo code
		browser.findElement(By.xpath(getPath("checkBox"))).click();
		browser.findElement(By.xpath(getPath("promoCode"))).sendKeys(getData("promoCode"));
		browser.findElement(By.xpath(getPath("applyCode"))).click();
		// making payment
		browser.findElement(By.xpath(getPath("makePayment"))).click();
		Thread.sleep(5000);
		// selectingPaymentMode
		// Thread.sleep(10000);
		// browser.findElement(By.xpath(getPath("payViaLink"))).click();
		// // selectingPayment
		// browser.findElement(By.xpath(getPath("paymentClick"))).click();
		// browser.findElement(By.xpath(getPath("nextclick"))).click();
		// browser.findElement(By.xpath(getPath("nextclick"))).click();

	}

	@BeforeClass
	public void beforeClass() {
		// setting up system properties and browser type
		System.setProperty("webdriver.chrome.driver", "/home/home/Workspace/Drivers/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/home/home/Workspace/Drivers/geckodriver");
		browser = new FirefoxDriver();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(5000);
		browser.close();
		asert.assertAll();
	}

	// get data from allData file
	public String getData(String key) {
		return GetDataFromFile.getDataFromDataFile(key);
	}

	// get data from allPath file
	public String getPath(String key) {
		return GetDataFromFile.getDataFrompathFile(key);
	}

}
