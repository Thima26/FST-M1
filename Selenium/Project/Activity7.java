package liveproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.time.Duration;

public class Activity7
{
    WebDriver driver;
    WebDriverWait wait;
    String userName="root";
    String password="pa$$w0rd";
    String jobTitle="Full Stack Tester";
    String location="America";
    String message="Applying for a Job";
    String mailID="abhiram@cklabs.com";
    String company="IBM";
    String website="https://www.ibm.com/in-en";
    String tagline="Think";
    String videoURL="https://video.ibm.com/IBMCloudVideo";
    String twitterID="@IBM";
    File file=new File("src/test/resources/IBM_logo.png");
    String verifyjobTitle;
    boolean verifyCondition;

    @BeforeClass
    public void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://alchemy.hguy.co/jobs");
    }

    @Test
    public void postAJob()
    {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Post a Job")));
        driver.findElement(By.linkText("Post a Job")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign in")));
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("user_login")).sendKeys(userName);
        driver.findElement(By.id("user_pass")).sendKeys(password);
        driver.findElement(By.id("wp-submit")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("job_title")));
        driver.findElement(By.id("job_title")).sendKeys(jobTitle);
        driver.findElement(By.id("job_location")).sendKeys(location);
        driver.findElement(By.xpath("//*[@id='job_type']/option[2]")).click();

        driver.switchTo().frame(0);
        driver.switchTo().activeElement();
        driver.findElement(By.xpath("//body")).click();
        driver.findElement(By.xpath("//body")).sendKeys(message);
        driver.switchTo().defaultContent();

        driver.findElement(By.id("application")).clear();
        driver.findElement(By.id("application")).sendKeys(mailID);
        driver.findElement(By.id("company_name")).clear();
        driver.findElement(By.id("company_name")).sendKeys(company);
        driver.findElement(By.id("company_website")).clear();
        driver.findElement(By.id("company_website")).sendKeys(website);
        driver.findElement(By.id("company_tagline")).clear();
        driver.findElement(By.id("company_tagline")).sendKeys(tagline);
        driver.findElement(By.id("company_video")).clear();
        driver.findElement(By.id("company_video")).sendKeys(videoURL);
        driver.findElement(By.id("company_twitter")).clear();
        driver.findElement(By.id("company_twitter")).sendKeys(twitterID);

        WebElement uploadInput = driver.findElement(By.id("company_logo"));
        uploadInput.sendKeys(file.getAbsolutePath());

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='submit_job']")));
        driver.findElement(By.xpath("//input[@name='submit_job']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("job_preview_submit_button")));
        driver.findElement(By.id("job_preview_submit_button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Jobs")));
        driver.findElement(By.linkText("Jobs")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'job_listing type-job_listing status-publish has-post-thumbnail hentry job-type-full-time')]//h3[contains(text(),'Full Stack Tester')]")));
        verifyjobTitle = driver.findElement(By.xpath("//li[contains(@class,'job_listing type-job_listing status-publish has-post-thumbnail hentry job-type-full-time')]//h3[contains(text(),'Full Stack Tester')]")).getText();

        Assert.assertEquals(jobTitle,verifyjobTitle);
        verifyCondition=jobTitle.contentEquals(verifyjobTitle);

        if (verifyCondition == true)
        {
            System.out.println("Job posted successfully and appeared in the Listing");
            Reporter.log("Job posted successfully and appeared in the Listing");
        }
        else {
            System.out.println("Job doesn't appear in the listing");
            Reporter.log("Job doesn't appear in the listing");
        }
    }

    @AfterClass
    public void tearDown()
    {
        driver.close();
    }
}
