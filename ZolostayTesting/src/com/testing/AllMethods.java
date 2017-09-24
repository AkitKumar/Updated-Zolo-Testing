package com.testing;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AllMethods {

	//method to login
	public void login(WebDriver browser) throws InterruptedException {
		browser.findElement(By.xpath(getPath("xpathLogin"))).click();
		Actions action = new Actions(browser);
		browser.findElement(By.xpath(getPath("mobile"))).sendKeys(getData("userName"));
		browser.findElement(By.xpath(getPath("password"))).sendKeys(getData("password"));
		Thread.sleep(1000);
		browser.findElement(By.xpath(getPath("signin"))).click();
	}

	//search for any location in search bar
	public void searchKey(WebDriver browser) throws InterruptedException {
		browser.findElement(By.xpath(getPath("search"))).sendKeys(getData("toSearch"));
		Thread.sleep(2000);
		List<WebElement> allAddress = browser.findElements(By.xpath(getPath("alladdress")));
		for (WebElement webElement : allAddress) {
			System.out.println(webElement.getText());
			if (webElement.getText().trim().equals(getData("location"))) {
				webElement.click();
				break;
			}
		}

	}

	// select any PG using budget,sharing and PG type filter.
	public void searchPlace(WebDriver browser, Select commonSelect) throws InterruptedException {
		commonSelect = new Select(browser.findElement(By.xpath(getPath("budgetdropdown"))));
		commonSelect.selectByVisibleText(getData("budget"));
		commonSelect = new Select(browser.findElement(By.xpath(getPath("sharingPreference"))));
		commonSelect.selectByVisibleText(getData("sharing"));
		commonSelect = new Select(browser.findElement(By.id(getPath("PGtype"))));
		commonSelect.selectByVisibleText(getData("PGtype"));
		Thread.sleep(2000);
		browser.findElement(By.xpath(getPath("selectPG") + getData("PG")+"']")).click();
		Thread.sleep(2000);
		browser.findElement(By.xpath(getPath("scheduleVist"))).click();
	}

	//schedule a visit
	public void scheduleVisit(WebDriver browser, Select commonSelect) {
		browser.findElement(By.xpath(getPath("DateVisit"))).clear();
		browser.findElement(By.xpath(getPath("DateVisit"))).sendKeys(getData("visitData"));
		commonSelect = new Select(browser.findElement(By.xpath(getPath("timeofvisit"))));
		commonSelect.selectByVisibleText(getData("visitTime"));
		browser.findElement(By.xpath(getPath("scheduleVisit"))).click();
	}

	public String getData(String key) {
		return GetDataFromFile.getDataFromDataFile(key);
	}

	public String getPath(String key) {
		return GetDataFromFile.getDataFrompathFile(key);
	}

}
