import io.appium.java_client.android.AndroidDriver;

public class Runner {


     public static void main(String[] args) {
          SetupDriver setupDriver = SetupDriver.initialize();
          AndroidDriver driver = setupDriver.getDriver();

     }
}
