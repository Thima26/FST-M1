package activities;

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

public class Activity4
{
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws MalformedURLException
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId","4c5edd97");
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.android.contacts");
        caps.setCapability("appActivity", ".PeopleActivityAlias");
        caps.setCapability("noReset", true);
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<>(appServer, caps);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void addContact()
    {
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("New Contact")));
        driver.findElementByAccessibilityId("New Contact").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("add_field")));
        driver.findElementById("add_field").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.ImageView[@resource-id='com.android.contacts:id/expansion_view']")));
        driver.findElementByXPath("//android.widget.ImageView[@resource-id='com.android.contacts:id/expansion_view']").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.EditText[@text='First name']")));
        driver.hideKeyboard();
        MobileElement firstName = driver.findElementByXPath("//android.widget.EditText[@text='First name']");
        MobileElement lastName = driver.findElementByXPath("//android.widget.EditText[@text='Last name']");
        MobileElement phoneNumber = driver.findElementByXPath("//android.widget.EditText[@text='Number']");
        firstName.sendKeys("Hima");
        lastName.sendKeys("Bindu");
        phoneNumber.sendKeys("0000000000");

        driver.findElementById("menu_done").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("contact_detail_view")));
        MobileElement mobileCard = driver.findElementById("contact_detail_view");
        Assert.assertTrue(mobileCard.isDisplayed());
        String contactName = driver.findElementById("main_title").getText();
        Assert.assertEquals(contactName, "Hima Bindu");
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
