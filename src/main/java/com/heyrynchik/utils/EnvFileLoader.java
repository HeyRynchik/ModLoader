package com.heyrynchik.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvFileLoader {

    private Properties properties;

    public EnvFileLoader() {
        load();
    }

    private void load() {
        properties = new Properties();

        // Get the class loader
        ClassLoader classLoader = getClass().getClassLoader();

        // Try to load the .env file as a resource
        try (InputStream inputStream = classLoader.getResourceAsStream(".env")) {
            if (inputStream != null) {
                // Load the properties from the input stream
                properties.load(inputStream);
            } else {
                System.out.println(".env file not found in resources folder.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
