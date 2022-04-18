package liveproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Activity6
{
    WebDriver driver;
    WebDriverWait wait;
    String searchWord="Banking";
    String mail;

    @BeforeClass
    public void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://alchemy.hguy.co/jobs");
    }

    @Test
    public void searchJob()
    {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Jobs")));
        driver.findElement(By.linkText("Jobs")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_keywords")));
        driver.findElement(By.id("search_keywords")).sendKeys(searchWord);

        driver.findElement(By.xpath("//input[contains(@value, 'Search Jobs')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='post-7']/div/div/ul/li[1]/a")));
        driver.findElement(By.xpath("//*[@id='post-7']/div/div/ul/li[1]/a")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value, 'Apply for job')]")));
        driver.findElement(By.xpath("//input[contains(@value, 'Apply for job')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("abhiram@cklabs.com")));
        driver.findElement(By.linkText("abhiram@cklabs.com")).click();

        mail=driver.findElement(By.linkText("abhiram@cklabs.com")).getText();
        System.out.println("Email is "+mail);
        Reporter.log("Email is "+mail);
    }

    @AfterClass
    public void tearDown()
    {
        driver.close();
    }
}






