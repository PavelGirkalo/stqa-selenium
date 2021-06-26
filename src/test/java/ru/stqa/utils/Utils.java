package ru.stqa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
