package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BasePage {

  protected WebDriver driver;
  
  public BasePage(){
    try {
      setup();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  private void setup() throws InterruptedException{
    System.setProperty("webdriver.chrome.driver", "C:\\Tamil\\GIT_Repository\\CMECF_Test_Automation_BK\\ui_tests\\Bankruptcy_Operations_and_Maintenance\\browser-drivers\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https://www.google.com");
  } 
}
