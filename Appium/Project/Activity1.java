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
import java.util.List;

public class Activity1
{

    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;
    String tasks[] = {"Complete Activity with Google Tasks", "Complete Activity with Google Keep", "Complete the second Activity Google Keep"};

    @BeforeClass
    public void setUp() throws MalformedURLException
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceId","4c5edd97");
        caps.setCapability("appPackage", "com.google.android.apps.tasks");
        caps.setCapability("appActivity", ".ui.TaskListsActivity");
        caps.setCapability("noReset", true);

        URL ServerURL = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<>(ServerURL, caps);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void googleTaskApp()
    {

        for(int i=0; i<= tasks.length-1; i++)
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("tasks_fab")));
            MobileElement addTask = driver.findElementById("tasks_fab");
            addTask.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("add_task_title")));
            MobileElement taskTitle = driver.findElementById("add_task_title");
            taskTitle.click();
            taskTitle.sendKeys(tasks[i]);
            MobileElement taskSubmit = driver.findElementById("add_task_done");
            taskSubmit.click();
        }

        wait.until(ExpectedConditions.numberOfElementsToBe(MobileBy.id("task_name"),(3)));
        int j=2;
        List<MobileElement> taskItem = driver.findElementsById("task_name");
        for(MobileElement taskItems : taskItem)
        {
            System.out.println(taskItems.getText());
            Assert.assertEquals(taskItems.getText(), tasks[j]);
            j--;
        }

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}