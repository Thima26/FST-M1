package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Activity1
{
    WebDriver driver;

    @BeforeMethod
    public void setUp()
    {
        driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.training-support.net/");
    }

    @Test
    public void homepageTest()
    {
        String homepageTitle=driver.getTitle();
        System.out.println("Page title is: " + homepageTitle);
        Assert.assertEquals(homepageTitle,"Training Support");

        driver.findElement(By.id("about-link")).click();
        String aboutUsPageTitle=driver.getTitle();
        System.out.println("New Page title is: " + aboutUsPageTitle);
        Assert.assertEquals(aboutUsPageTitle,"About Training Support");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.close();
    }

}
