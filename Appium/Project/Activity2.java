package AppiumLiveProject;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class Activity2
{
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;
    String noteTitle = "Full Stack Tester Module 1";
    String noteDescription = "This note contains the details about FST Module 1";

    @BeforeClass
    public void setUp() throws MalformedURLException
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceId","4c5edd97");
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity", ".activities.BrowseActivity");
        caps.setCapability("noReset", true);
        URL ServerURL = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<>(ServerURL, caps);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void googleKeepApp()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("new_note_button")));
        driver.findElementById("new_note_button").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("editable_title")));
        driver.findElementById("editable_title").click();
        driver.findElementById("editable_title").sendKeys(noteTitle);
        driver.findElementById("edit_note_text").sendKeys(noteDescription);
        driver.findElementByAccessibilityId("Open navigation drawer").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("notes")));
        MobileElement result = driver.findElementByXPath("//android.widget.TextView[1][@text='Full Stack Tester Module 1']");
        Assert.assertEquals(result.getText(),noteTitle);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}