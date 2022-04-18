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
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Activity5
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
        caps.setCapability("appPackage", "com.android.mms");
        caps.setCapability("appActivity", ".ui.ConversationList");
        caps.setCapability("noReset", true);
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<>(appServer, caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void smsTest()
    {
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("description(\"Start new conversation\")")));
        driver.findElement(MobileBy.AndroidUIAutomator("description(\"Start new conversation\")")).click();
        String contactBoxLocator = "resourceId(\"com.android.mms:id/recipient_text_view\")";
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(contactBoxLocator)));
        driver.findElement(MobileBy.AndroidUIAutomator(contactBoxLocator)).sendKeys("0000000000");
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("resourceId(\"com.android.mms:id/compose_message_text\")")));
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.android.mms:id/compose_message_text\")")).sendKeys("Hello from Appium");
        driver.findElement(MobileBy.AndroidUIAutomator("description(\"Send message\")")).click();
        String messageLocator = "resourceId(\"com.android.mms:id/message_text_content\")";
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AndroidUIAutomator(messageLocator)));
        String sentMessageText = driver.findElement(MobileBy.AndroidUIAutomator(messageLocator)).getText();
        Assert.assertEquals(sentMessageText, "Hello from Appium");
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
