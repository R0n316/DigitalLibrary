package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();
    static {
        LoadProperties();
    }

    private static void LoadProperties() {
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private PropertiesUtil(){}
    public static String get(String name){
        return PROPERTIES.getProperty(name);
    }
}
