package activities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class Activity2
{
    WebDriverWait wait;
    AndroidDriver<MobileElement> driver = null;

    @BeforeClass
    public void setup() throws MalformedURLException
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId","4c5edd97");
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        caps.setCapability("noReset", true);
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
    }

    @Test
    public void testSearchAppium()
    {
        driver.get("https://www.training-support.net/");
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("About Us")));
        String pageTitle = driver.findElementByXPath(" //android.view.View/android.view.View/android.view.View[2][@text='Training Support']").getText();
        System.out.println("Title on Homepage: " + pageTitle);
        MobileElement aboutButton = driver.findElementByXPath("//android.view.View[@content-desc='About Us']");
        aboutButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("//android.view.View/android.view.View/android.view.View[2][@text='About Us']")));
        String newPageTitle = driver.findElementByXPath("//android.view.View/android.view.View/android.view.View[2][@text='About Us']").getText();
        System.out.println("Title on new page: " + newPageTitle);
        Assert.assertEquals(pageTitle, "Training Support");
        Assert.assertEquals(newPageTitle, "About Us");
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
