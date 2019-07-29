package AutomationFramework;

import org.testng.annotations.Test;

import pageObjects.Looplgn;
import pageObjects.SignIn;
import utility.Constants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.sql.Driver;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class TestNG {
	
	public WebDriver driver;
	
  @Test
  public void f() {
	  
      //Verify Title:
      String Title = "Corridor | Our Innovation Hub";
      String actualTitle = Driver.getTitle();

      Assert.assertEquals(actualTitle, Title );
      System.out.println("Test Pass ");
      System.out.println("The Title of the Page is  : " + actualTitle);

      //Verify Login button:
      driver.findElement(By.linkText("LOGIN")).click();
      System.out.println("Title of page is :" + driver.getTitle());

      //Verify valid login:
      SignIn.Execute(driver, Constants.Username, Constants.Password);
      System.out.println("Title of page is :" + driver.getTitle());
      System.out.println("Login Successful");

      //Launch Loop:
      String handle1 = driver.getWindowHandle();
      System.out.println(handle1);

      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Launch'][@href='https://loop.spotcues.com/']")));
      Looplgn.launchloop(driver).click();

      Set handles = driver.getWindowHandles();
      System.out.println(handles);

      for (String handle2 : driver.getWindowHandles()) {
          System.out.println(handle2);
          driver.switchTo().window(handle2);
          System.out.println("Title of page is :" + driver.getTitle());
      }
      // Logout Loop:
      WebElement logout = driver.findElement(By.xpath("//*[text() = 'Logout']"));
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);
      System.out.println("Title of page is :" + driver.getTitle());


  }
  @BeforeTest
  public void beforeTest() {
	  
	  ChromeOptions options = new ChromeOptions();
      options.addArguments("--disable-notifications");
      System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32 (1)\\chromedriver.exe");
      WebDriver driver = new ChromeDriver(options);
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      String url = "https://corridor.pramati.com/";
      driver.get(url);
      driver.manage().window().maximize();
      String Acturl = driver.getCurrentUrl();

      Assert.assertEquals(Acturl,url);
      System.out.println("Test Pass ");
      System.out.println("The url displayed is  : " + driver.getCurrentUrl());
     
  }

  @AfterTest
  public void afterTest() {
	  
	 driver.quit();
  }

}
