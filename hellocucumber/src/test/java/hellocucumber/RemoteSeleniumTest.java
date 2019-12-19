package hellocucumber;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteSeleniumTest {

	public static void main(String[] args) throws MalformedURLException {
		ChromeOptions co = new ChromeOptions();
//		co.setCapability("browserVersion", 79);
//		co.setCapability("platformName", Platform.LINUX);
		WebDriver cd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), co);
		cd.get("http://www.google.com");
		cd.quit();
	}

}
