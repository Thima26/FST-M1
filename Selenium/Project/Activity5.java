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

public class Activity5
{
    WebDriver driver;
    WebDriverWait wait;
    boolean checkCondition;

    @BeforeClass
    public void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait=new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://alchemy.hguy.co/jobs");
    }

    @Test
    public void navigateToJobsPage()
    {
        driver.findElement(By.className("main-header-container"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Jobs")));
        driver.findElement(By.linkText("Jobs")).click();
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        Reporter.log("Page title is: " + title);
        Assert.assertEquals(title,"Jobs – Alchemy Jobs");
        checkCondition = driver.getTitle().contentEquals("Jobs – Alchemy Jobs");
        System.out.println("After clicking on the Jobs, I am on the correct page: "+checkCondition);
        Reporter.log("After clicking on the Jobs, I am on the correct page: "+checkCondition);
    }

    @AfterClass
    public void tearDown()
    {
        if(checkCondition==true)
        {
            driver.close();
        }
    }
}