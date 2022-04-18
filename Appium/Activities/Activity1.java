package activities;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;


public class Activity1 {

    AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "UiAutomator2");
        //caps.setCapability("deviceName", "<PixelTestEmulator>");
        caps.setCapability("deviceId","4c5edd97");
        caps.setCapability("appPackage", "com.coloros.calculator");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        caps.setCapability("noReset", true);

        URL ServerURL = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<>(ServerURL, caps);
    }

    @Test
    public void multiplicationTest()
    {
        MobileElement digit5 = driver.findElementById("digit_5");
        digit5.click();
        driver.findElementByAccessibilityId("Multiply").click();
        digit5.click();
        driver.findElementById("eq").click();
        String result = driver.findElementById("result").getText();
        System.out.println(result);
        Assert.assertEquals(result, "25");
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
