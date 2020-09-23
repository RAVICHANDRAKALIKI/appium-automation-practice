import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Settings {
    private static Map<String, String> settingsMap;
    private static final Logger logger = LoggerFactory.getLogger(Settings.class);

    public static String get(String key) {
        if (settingsMap == null) {
            loadSettings();
        }

        return settingsMap.get(key);
    }

    private static void loadSettings() {
        Yaml appYaml = new Yaml();
        InputStream stream =null;
        try {
            stream = new FileInputStream("src/main/resources/app.yaml");
        } catch (FileNotFoundException e) {
            logger.error("app.yml file error", e);
            settingsMap = new HashMap<>();
        }

        settingsMap = appYaml.loadAs(stream, Map.class);

    }
}
