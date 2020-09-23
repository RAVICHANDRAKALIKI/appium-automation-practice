import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Runner {


     public static void main(String[] args) {

          AppiumDriverLocalService appium = new AppiumServiceBuilder().withArgument(GeneralServerFlag.LOG_NO_COLORS).build();
          try {
               appium.addOutPutStream(new FileOutputStream("logs/appium.log"));
          } catch (FileNotFoundException e) {
               e.printStackTrace();
          }
          appium.start();
          try {
               SetupDriver setupDriver = SetupDriver.initialize();
               AndroidDriver driver = setupDriver.getDriver(appium);

               // Running Tests using driver
               SampleAppTest.run(driver);
               // End of Tests.

          } catch (Exception e) {

          }
          finally {
               appium.stop();
          }


     }
}
