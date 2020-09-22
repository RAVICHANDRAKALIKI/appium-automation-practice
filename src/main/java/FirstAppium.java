import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static io.appium.java_client.android.nativekey.AndroidKey.*;


public class FirstAppium {

    static Logger log = LoggerFactory.getLogger("FirstAppium");

    public static void main(String[] args) throws MalformedURLException {
        File apk = new File("src/main/resources/ApiDemos-debug.apk");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel_3a_API_30");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, apk.getAbsolutePath());
        AndroidDriver<AndroidElement> driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);

        WebElement textElement = driver.findElement(By.xpath("//android.widget.TextView[@text='Text']"));
        textElement.click();
        driver.pressKey(new KeyEvent(BACK));
        WebElement viewsElement = driver.findElement(new MobileBy.ByAndroidUIAutomator("text(\"Views\")"));
        viewsElement.click();

        // drag the pointer on the clock
        List<AndroidElement> list = driver.findElementsByClassName("android.widget.TextView");
        list.forEach(element -> log.debug((element.getText())));
        Assertions.assertTrue(list.get(7).getText().equals("Date Widgets"));
        list.get(7).click();
        WebElement inlineOption = driver.findElementByAndroidUIAutomator("text(\"2. Inline\")");
        Assertions.assertTrue(inlineOption.getText().contains("Inline"));
        inlineOption.click();
        WebElement hourNine = driver.findElementByXPath("//*[@content-desc=\"9\"]");
        hourNine.click();
        WebElement selectedMinute = driver.findElementByAndroidUIAutomator("selected(true)");
        log.debug(selectedMinute.getAttribute("content-desc"));
        log.debug(selectedMinute.getLocation().toString());
        Assertions.assertTrue(selectedMinute.getAttribute("content-desc").equals("15"));

        WebElement minuteForty = driver.findElementByXPath("//*[@content-desc=\"40\"]");

        TouchAction drag = new TouchAction(driver);
        drag.press(ElementOption.element(selectedMinute)).moveTo(ElementOption.element(minuteForty)).release().perform();

        WebElement setMinute = driver.findElementByAndroidUIAutomator("selected(true)");
        Assertions.assertTrue(setMinute.getAttribute("content-desc").equals("40"));

        int a = 0;
        do {
            driver.pressKey(new KeyEvent(BACK));
            a++;
        } while (a < 2);

        // perform drag and drop











        // scrolling behaviour
        //        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
        //                + ".scrollIntoView(new UiSelector().text(\"TextSwitcher\").instance(0));").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().text(\"TextSwitcher\"));").click();
        driver.pressKey(new KeyEvent(BACK));
        driver.pressKey(new KeyEvent(BACK));


        WebElement AppElement = driver.findElementByXPath("//*[@text=\"App\"]");
        TouchAction t = new TouchAction(driver);
        t.tap(new TapOptions().withElement(ElementOption.element(AppElement))).perform();

        WebElement fragment = driver.findElementByXPath("//*[@content-desc=\"Fragment\"]");
        t.tap(new TapOptions().withElement(ElementOption.element(fragment))).perform();

        WebElement contextMenu = driver.findElementByAndroidUIAutomator("text(\"Context Menu\")");
        contextMenu.click();
        WebElement longPressButton = driver.findElementByClassName("android.widget.Button");
        TouchAction longPressAction = new TouchAction(driver);
        longPressAction.longPress(
                LongPressOptions.longPressOptions()
                        .withElement(ElementOption.element(longPressButton))
                        .withDuration(Duration.ofSeconds(2)))
            .release().perform();
        List<AndroidElement> longPressMenuItems = driver.findElementsById("android:id/title");
        Assertions.assertTrue(longPressMenuItems.size() == 2);
        Assertions.assertTrue(longPressMenuItems.get(1).getText().equals("Menu B"));

        // back to the homepage
        for (int i = 0; i < 4; i++) {
            driver.pressKey(new KeyEvent(BACK));
        }

    }
}
