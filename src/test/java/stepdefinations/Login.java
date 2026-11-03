package stepdefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Login {

     WebDriver driver;
     WebDriverWait wait;

    String user;
    String pass;
    @Before
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();

    }

    @After
    public void teardown(){

        if(driver!=null){

             driver.quit();
        }

    }
    @Given("Goto the loginpage")
    public void goto_the_loginpage() {

     driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @When("enter correct credentials")
    public void enter_correct_credentials(DataTable data) {

        List<Map< String, String>> table = data.asMaps(String.class, String.class);



        for(Map<String,String> db:table) {
             user = db.get("username");
             pass = db.get("password");
            System.out.println(user);

        }

          wait=new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(user);
         driver.findElement(By.name("password")).sendKeys(pass);

    }
    @And("click on the login button")
    public void click_on_the_login_button() {

      driver.findElement(By.tagName("button")).click();
    }
    @Then("login successfull")
    public void login_successfull() {

       String  loginpageurl=driver.getCurrentUrl();
        Assert.assertNotEquals(loginpageurl,"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    }
    @And("profile picture display")
    public void profile_picture_display() {

        System.out.println("displayed");
    }


    @Then("it should throw error")
    public void itShouldThrowError() throws InterruptedException {

        Thread.sleep(4000);
        boolean error = driver.findElement(By.xpath("//p[text()='Invalid credentials']")).isDisplayed();

        assertTrue(error);
    }

    @And("error message display")
    public void errorMessageDisplay() {

        String error = driver.findElement(By.xpath("//p[text()='Invalid credentials']")).getText();

        String expected="Invalid credentials";
        assertEquals(expected, error);
    }
}
