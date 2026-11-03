package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class DataProvider {
    WebDriver driver;
    WebDriverWait wait;
    FluentWait<WebDriver> wait1;
    @BeforeClass
    @Parameters({"browser", "url"})
    public void browser_setup(String browser, String url) throws InterruptedException {
        //WebDriver.Timeouts t;

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(url);
            //explicit wait


          // t = driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.get(url);
            Thread.sleep(5000);
           // t = driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }


    }


    @Test(dataProvider = "logindata")
    public void enter_login_credentials(String username,String password) throws InterruptedException {
        Thread.sleep(4000);

       // wait =new WebDriverWait(driver,Duration.ofSeconds(20));

         wait1 =new FluentWait<>(driver);
         wait1.withTimeout(Duration.ofSeconds(20));
         wait1.pollingEvery(Duration.ofMillis(200));
         wait1.ignoring(NoSuchElementException.class);

        wait1.until(ExpectedConditions.visibilityOfElementLocated((By.name("username")))).sendKeys(username);

        //driver.findElement(By.name("username")).sendKeys(usernme);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.tagName("button")).click();
    }

     @org.testng.annotations.DataProvider(name ="logindata")
      public Object[][] data(){

            return new Object[][]{

                    {"admin123444","admin"},
                    {"admin879844","12355admin"},
            };


      }

}