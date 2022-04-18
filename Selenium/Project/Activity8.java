package liveproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Activity8
{
    WebDriver driver;
    WebDriverWait wait;
    String userName="root";
    String password="pa$$w0rd";
    String verifyName;

    @BeforeClass
    public void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
    }

    @Test
    public void siteBackend()
    {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("user_login")));
        driver.findElement(By.id("user_login")).sendKeys(userName);
        driver.findElement(By.id("user_pass")).sendKeys(password);
        driver.findElement(By.id("wp-submit")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='root']")));
        verifyName = driver.findElement(By.xpath("//span[text()='root']")).getText();
        System.out.println(verifyName +" has logged in successfully");
        Reporter.log(verifyName +" has logged in successfully");
        Assert.assertEquals(userName, verifyName);
    }

    @AfterClass
    public void tearDown()
    {
        driver.close();
    }

}
