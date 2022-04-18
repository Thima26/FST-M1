package liveproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Activity1
{
    WebDriver driver;
    WebDriverWait wait;
    boolean checkCondition;

    @BeforeClass
    public void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://alchemy.hguy.co/jobs");
    }

    @Test
    public void getPageTitle()
    {
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        Reporter.log("Page title is: " + title);
        Assert.assertEquals(title,"Alchemy Jobs – Job Board Application");
        checkCondition=driver.getTitle().contentEquals("Alchemy Jobs – Job Board Application");
    }

    @AfterClass
    public void tearDown()
    {
        if(checkCondition==true)
        driver.close();
    }
}
