package com.flipkart.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtils {

    private static Properties properties = new Properties();
    private static InputStream inputStream;
    public static  String  filePath="./file/product.properties";

    public static String readFromPropertyFile( String propertyName) {
        try
        {
            inputStream = new FileInputStream(filePath);
            properties.load(inputStream);
            return properties.getProperty(propertyName);
        }
        catch ( IOException io) {
            io.printStackTrace(); }
        return null;
    }
}
