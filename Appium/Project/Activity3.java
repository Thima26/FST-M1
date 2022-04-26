package AppiumLiveProject;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Activity3
{
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;
    String tasksCollection[] ={"Add tasks to list", "Get number of tasks", "Clear the list"};

    @BeforeClass
    public void setUp() throws MalformedURLException
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceId","4c5edd97");
        caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        caps.setCapability("noReset", true);

        URL ServerURL = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<>(ServerURL, caps);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void googleChrome()
    {
        driver.get("https://www.training-support.net/selenium");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("android.view.View")));
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
        driver.findElement(MobileBy.AndroidUIAutomator(UiScrollable+".flingToEnd(3)"));
        driver.findElement(MobileBy.xpath("//android.view.View[contains(@text, 'Elements get added at run time')]")).click();
        for(int i=0; i<= tasksCollection.length-1; i++)
        {
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.EditText[@resource-id='taskInput']")));
            MobileElement addTask = driver.findElementByXPath("//android.widget.EditText[@resource-id='taskInput']");
            addTask.sendKeys(tasksCollection[i]);
            MobileElement submitTask = driver.findElementByXPath("//android.widget.Button[contains(@text, 'Add Task')]");
            submitTask.click();
        }

        List<MobileElement> tasks = driver.findElementsByXPath("//android.view.View/android.view.View[3]/android.view.View[2]/android.view.View");
        for (MobileElement i:tasks )
        {
            i.click();
        }
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Clear List')]")).click();
        List<MobileElement> noOfTasksAfterClearing = driver.findElementsByXPath("//android.view.View[@resource-id='tasksList']");
        System.out.println("After clearing, no of Tasks is: "+ noOfTasksAfterClearing.size());
        Assert.assertEquals(noOfTasksAfterClearing.size(),0);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
