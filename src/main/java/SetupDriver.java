import io.appium.java_client.android.AndroidDriver;

public interface SetupDriver {


    AndroidDriver getDriver();

    public static SetupDriver initialize() {
        SetupDriver runDriver = null;
        switch (Settings.get("run")) {
            case StaticStrings.REAL_APK_RUN:
                runDriver = new RealDeviceNativeApp();
        }
        return runDriver;
    }


}
