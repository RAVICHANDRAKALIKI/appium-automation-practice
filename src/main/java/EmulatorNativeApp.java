import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

public class EmulatorNativeApp implements SetupDriver {

    private static AndroidDriver driver;
    private static Logger logger = LoggerFactory.getLogger(EmulatorNativeApp.class);


    @Override
    public AndroidDriver getDriver(AppiumDriverLocalService appium) {
        if (driver == null) {
            logger.debug("apk:" + Settings.get(StaticStrings.APK));
            File apkFile = new File(Settings.get(StaticStrings.APK));
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Settings.get(StaticStrings.EMULATOR));
            desiredCapabilities.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, StaticStrings.APPIUM_UIAUTOMATOR2);
            driver = new AndroidDriver(appium, desiredCapabilities);
            driver.unlockDevice();
        }
        return driver;
    }
}
