package liveproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Activity4
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
    public void getSecondHeaderPageTitle()
    {
        String heading = driver.findElement(By.xpath("//h2")).getText();
        System.out.println("Heading of the Page is: " + heading);
        Reporter.log("Heading of the Page is: " + heading);
        Assert.assertEquals(heading,"Quia quis non");
        checkCondition=driver.findElement(By.xpath("//h2")).getText().contentEquals("Quia quis non");
    }

    @AfterClass
    public void tearDown()
    {
        if(checkCondition==true)
            driver.close();
    }
}