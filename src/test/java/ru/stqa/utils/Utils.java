package ru.stqa.utils;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

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

    public static ExpectedCondition<String> anyWindowOtherThan(Set<String> oldElements) {
        return new ExpectedCondition<String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver driver) {
                Set<String> handles = Objects.requireNonNull(driver).getWindowHandles();
                handles.removeAll(oldElements);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
}
