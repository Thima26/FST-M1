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
import java.util.Set;

public class Activity9
{
    WebDriver driver;
    WebDriverWait wait;
    String userName="root";
    String password="pa$$w0rd";
    String position="BackendJob";
    String jobLocation="America";
    String mail="abhiram@cklabs.com";
    String company="IBM";
    String website="https://alchemy.hguy.co/jobs/wp-admin";
    String tagLine="Think";
    String twitterName="TwitterID";
    String videoUrl ="http://alchemy.hguy.co/jobs/wp-content/uploads/2022/04/IBM_logo.png";
    String verifyjoblist;
    boolean verifyCondition;

    @BeforeClass
    public void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
    }

    @Test
    public void siteBackendCreateAJob()
    {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("user_login")));
        driver.findElement(By.id("user_login")).sendKeys(userName);
        driver.findElement(By.id("user_pass")).sendKeys(password);
        driver.findElement(By.id("wp-submit")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menu-posts-job_listing']/a/div[3]")));
        driver.findElement(By.xpath("//*[@id='menu-posts-job_listing']/a/div[3]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add New")));
        driver.findElement(By.linkText("Add New")).click();

        String parentHandle = driver.getWindowHandle();
        driver.findElement(By.xpath("//div[@class='components-modal__frame components-guide edit-post-welcome-guide']")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(1));

        Set<String> handles = driver.getWindowHandles();
        for(String handle : handles) {
            if (handle != parentHandle) {
                driver.switchTo().window(handle);
            }
        }

        wait.until(ExpectedConditions.titleIs("Add Job ‹ Alchemy Jobs — WordPress"));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Close dialog']")));
        driver.findElement(By.xpath("//button[@aria-label='Close dialog']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("post-title-0")));
        driver.findElement(By.id("post-title-0")).sendKeys(position);
        driver.findElement(By.id("_job_location")).sendKeys(jobLocation);
        driver.findElement(By.id("_application")).clear();
        driver.findElement(By.id("_application")).sendKeys(mail);
        driver.findElement(By.id("_company_name")).sendKeys(company);
        driver.findElement(By.id("_company_website")).sendKeys(website);
        driver.findElement(By.id("_company_tagline")).sendKeys(tagLine);
        driver.findElement(By.id("_company_twitter")).sendKeys(twitterName);
        driver.findElement(By.id("_company_video")).sendKeys(videoUrl);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='edit-post-header__settings']/button[text()='Publish…']")));
        driver.findElement(By.xpath("//div[@class='edit-post-header__settings']/button[text()='Publish…']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='editor-post-publish-panel']//button[text()='Publish']")));
        driver.findElement(By.xpath("//div[@class='editor-post-publish-panel']//button[text()='Publish']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View Job")));
        driver.findElement(By.linkText("View Job")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class='entry-title'][contains(text(), 'BackendJob')]")));
        verifyjoblist= driver.findElement(By.xpath("//h1[@class='entry-title'][contains(text(), 'BackendJob')]")).getText();

        Assert.assertEquals(position,verifyjoblist);
        verifyCondition=position.contentEquals(verifyjoblist);

        if (verifyCondition == true)
        {
            System.out.println("Job Listing created");
            Reporter.log("Job Listing created");
        }
        else {
            System.out.println("Job is not created");
            Reporter.log("Job is not created");
        }

    }

    @AfterClass
    public void tearDown()
    {
        driver.close();
    }

}
