package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class Login_test {

      WebDriver driver;
      @BeforeClass(groups = "smoke")


      @Parameters({"browser","url"})
      public void browser_setup(String browser,String url) throws InterruptedException {

          if(browser.equalsIgnoreCase("chrome")) {
              driver = new ChromeDriver();
              driver.manage().window().maximize();
              driver.get(url);
              Thread.sleep(5000);
          }
          else if(browser.equalsIgnoreCase("edge")) {
              driver = new EdgeDriver();
              driver.manage().window().maximize();
              driver.get(url);
              Thread.sleep(5000);
          }

      }


     @Test(groups = "smoke", priority = 1)
      public void enter_login_credentials() throws InterruptedException {
          Thread.sleep(4000);
          driver.findElement(By.name("username")).sendKeys("Admin");
         driver.findElement(By.name("password")).sendKeys("admin123");
      }


     @Test(groups="functional",priority = 2)
      public void click_on_login_button() throws InterruptedException {
        String expected=driver.getCurrentUrl();
       driver.findElement(By.tagName("button")).click()
       ;

        Thread.sleep(4000);
        String actual=driver.getCurrentUrl();
        //WebElement p=driver.findElement(By.xpath("//img[@alt=\"profile picture\"]"));
         //Assert.assertFalse(p.isDisplayed());

        assertEquals(expected,actual);

          SoftAssert so=new SoftAssert();

         // so.assertEquals(expected,actual);

         System.out.println("Login  verified successfully!!!");


         //so.assertAll();
      }

      @AfterClass(groups="regression")
      public void user_logout() throws InterruptedException {

     // driver.findElement(By.cssSelector("[alt=\"profile picture\"]")).click();

       Thread.sleep(2000);

     //  driver.findElement(By.xpath("//a[text()='Logout']")).click();

    }
    @AfterSuite(groups = "regression")
      public void quit_browser(){

          if(driver!=null){

              driver.quit();
          }

      }
}
