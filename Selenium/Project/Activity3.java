package liveproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Activity3
{
    WebDriver driver;
    WebDriverWait wait;
    WebElement url;

    @BeforeClass
    public void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://alchemy.hguy.co/jobs");
    }

    @Test
    public void getHeaderImageUrl()
    {
        url= driver.findElement(By.tagName("img"));
        System.out.println("Header Image Url is: "+url.getAttribute("src"));
        Reporter.log("Header Image Url is: "+url.getAttribute("src"));
    }

    @AfterClass
    public void tearDown()
    {
            driver.close();
    }
}

