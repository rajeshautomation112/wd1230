package stepdefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class Login2 {

    WebDriver driver;
    WebDriverWait wait;

    String user;
    String pass;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @After
    public void teardown() {

        if (driver != null) {

            driver.quit();
        }

    }

    @Given("user got the login page")
    public void userGotTheLoginPage() {

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @When("Enter the login data (.*)and(.*)$")
    public void enterTheLoginDataUsernameAndPassword(String user,String pass) {

        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(user);
        driver.findElement(By.name("password")).sendKeys(pass);
    }


    @And("press the enter button")
    public void pressTheEnterButton() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("login successful with correct data")
    public void loginSuccessfulWithCorrectData() throws InterruptedException {

        Thread.sleep(4000);
        boolean dashboard = driver.findElement(By.xpath("//span[text()='Dashboard']")).isDisplayed();
        SoftAssert soft=new SoftAssert();

        soft.assertTrue(dashboard);



    }

    @And("user get error with incorrect data")
    public void userGetErrorWithIncorrectData() {


        SoftAssert soft=new SoftAssert();
        String error = driver.findElement(By.xpath("//p[text()='Invalid credentials']")).getText();

        String expected="Invalid credentials";
        soft.assertEquals(expected, error);
    }


    public void git_commit_demo(){

        System.out.println("This is git commit demo");
    }
}