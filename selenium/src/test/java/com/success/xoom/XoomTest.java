package com.success.xoom;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class XoomTest {
	String url = "https://www.xoom.com/";
	private static ChromeDriverService service;
	private WebDriver driver;

	@BeforeClass
	public static void setup() throws IOException {
		//chrome driver location
		//https://sites.google.com/a/chromium.org/chromedriver/getting-started
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("F:/downs/softwares/chromedriver_win32/chromedriver.exe"))
				.usingAnyFreePort().build();
		service.start();
	}

	@AfterClass
	public static void shutdown() {
//		service.stop();
	}
	@Before
	public void createDriver() throws InterruptedException {
		driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
	}

	@After
	public void quitDriver() {
//		driver.quit();
	}

	@Test
	public void xoomTest() throws InterruptedException {
		driver.get(url);
//		driver.findElement(By.partialLinkText("signIn")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Log In")).click();
		Thread.sleep(10000);
//		driver.findElement(By.cssSelector("recaptcha-checkbox-checkmark")).click();
		driver.findElement(By.className("recaptcha-checkbox-checkmark")).click();
	}

	public static void main(String[] args) {

	}
}
