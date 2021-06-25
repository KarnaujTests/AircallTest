package com.aircall.es.testscucumber.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHandler {
    private static final String PROPERTIES_FILE = "cucumber.properties";
    private static Properties properties;
    public static Properties getProgramProperties(){
        if(properties == null) {
            properties = new Properties();
            try (InputStream stream = new FileInputStream(PROPERTIES_FILE)){
                properties.load(stream);
            } catch (IOException e) {
            }
        }
        return properties;
    }
}
