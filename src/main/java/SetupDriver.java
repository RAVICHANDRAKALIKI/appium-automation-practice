import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public interface SetupDriver {


    AndroidDriver getDriver(AppiumDriverLocalService appium);

    public static SetupDriver initialize() {
        SetupDriver runDriver = null;
        switch (Settings.get("run")) {
            case StaticStrings.REAL_APK_RUN:
                runDriver = new RealDeviceNativeApp();
            case StaticStrings.EMULATOR_APK_RUN:
                runDriver = new EmulatorNativeApp();
        }
        return runDriver;
    }


}
