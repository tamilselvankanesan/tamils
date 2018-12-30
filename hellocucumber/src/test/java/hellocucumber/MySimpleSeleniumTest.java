package hellocucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MySimpleSeleniumTest {

  public static void main(String[] args) {
    
    System.setProperty("webdriver.chrome.driver", "C:\\Tamil\\GIT_Repository\\CMECF_Test_Automation_BK\\ui_tests\\Bankruptcy_Operations_and_Maintenance\\browser-drivers\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.get("file:///C:/Tamil/desktop/HTML/Selenium_Test.html");
    sleep(2000);    
//    WebElement element = driver.findElement(By.xpath("//a[.='Dropdown']/@href"));
    WebElement link = driver.findElement(By.xpath("//a[contains(@class, 'two')]"));
    link.click();
    
    link = driver.findElement(By.xpath("//a[contains(text(), 'Save & Close')]"));
    link.click();
    sleep(2000);
    driver.close();
  }
  
  private static void sleep(long millis){
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
